package org.eclipse.xtend.core.formatting

import java.util.List
import java.util.TreeMap
import org.apache.log4j.Logger
import org.eclipse.xtext.xbase.lib.Pair

class FormattableDocument {
	private static val Logger log = Logger::getLogger(typeof(FormattableDocument))
	@Property val IConfigurationValues cfg
	@Property val String document
	@Property val TreeMap<Integer, FormattingData> formattings
	@Property Throwable rootTrace = null
	@Property boolean conflictOccurred = false
	
	new(IConfigurationValues cfg, String document){
		this._cfg = cfg
		this._document = document
		this._formattings = new TreeMap()
	}
	
	new(FormattableDocument fmt) {
		this._cfg = fmt.cfg
		this._document = fmt.document
		this._formattings = new TreeMap(fmt.formattings)
	}
	
	def boolean isDebugConflicts() {
		rootTrace != null
	}
	
	def protected AbstractFormatterConfigurationKeys getKeys() {
		cfg.keys as AbstractFormatterConfigurationKeys
	}
	
	def protected addFormatting(FormattingData data) {
		if(data != null) {
			if(data.length < 0) {
				val text = getTextAround(data)
				log.error('''
					lenght of text-edit can not be negative:
					--------------------------------- document snippet ------------------------------
					�text.key�[[[!!]]]�text.value�
					---------------------------------------------------------------------------------
				''')
				throw new IllegalStateException("Length of text edit can not be negative")
			}
			if(data.length > 0) {
				val oldText = document.substring(data.offset, data.offset + data.length)
				if(!oldText.whitespace) {
					val text = getTextAround(data)
					log.error('''
						Can not edit non-whitespace:
						------------------------------- document snippet --------------------------------
						�text.key�[[[�oldText�]]]�text.value�
						---------------------------------------------------------------------------------
					''')
					throw new IllegalStateException("Can non format non-whitespace: "+oldText)
				}
			}
			val old = formattings.get(data.offset)
			val newData = if(old == null) data else merge(old, data)
			if(newData != null)
				formattings.put(data.offset, newData)
		}
	}
	
	def protected FormattingData merge(FormattingData data1, FormattingData data2) {
		var FormattingData old = null
		var indentationChange = 0 
		if(data2.empty) {
			indentationChange = data1.indentationChange + data2.indentationChange
			old = data1
		} else if(data1.empty) {
			indentationChange = data2.indentationChange + data1.indentationChange
			old = data2
		}
		if(old != null) {
			switch old {
				NewLineData: new NewLineData(old.offset, old.length, indentationChange, old.trace, old.newLines)
				WhitespaceData: new WhitespaceData(old.offset, old.length, indentationChange, old.trace, old.space)
			}
		} else {
			conflictOccurred = true
			if(debugConflicts)
				reportConflict(data1, data2)
			null
		}
	}
	
	def protected Pair<String, String> getTextAround(FormattingData data1) {
		val back = (0..5).fold(data1.offset, [last, i| if(last > 0) document.lastIndexOf("\n", last - 1) else -1 ])
		val forward = (0..5).fold(data1.offset, [last, i| if(last > 0) document.indexOf("\n", last + 1) else -1 ])
		val fiveLinesBackOffset = if(back >= 0) back else 0
		val fiveLinesForwardOffset = if(forward >= 0) forward else document.length
		val prefix = document.substring(fiveLinesBackOffset, data1.offset)
		val postfix = document.substring(data1.offset + data1.length, fiveLinesForwardOffset)
		prefix -> postfix		
	}
	
	def protected void reportConflict(FormattingData data1, FormattingData data2) {
		val text = getTextAround(data1)
		val traceStart = rootTrace.stackTrace.size - 1
		val fullTrace1 = data1.trace.stackTrace
		val shortTrace1 = fullTrace1.subList(0, fullTrace1.size - traceStart).join("\n") 
		val fullTrace2 = data2.trace.stackTrace
		val shortTrace2 = fullTrace2.subList(0, fullTrace2.size - traceStart).join("\n")
		log.error('''
			Conflicting TextEdits during formatting:
			------------------------------- document snippet --------------------------------
			�text.key�[!!!]�text.value�
			---------------------------------------------------------------------------------
			TextEdit1: �data1.toString.replaceAll("\\n\\s*"," ")�
			TextEdit2: �data2.toString.replaceAll("\\n\\s*"," ")�
			---------------------------------- Trace 1 --------------------------------------
			�shortTrace1�
			---------------------------------- Trace 2 --------------------------------------
			�shortTrace2�
			---------------------------------------------------------------------------------
		''')
	}
	
	def operator_add(FormattingData data) {
		addFormatting(data)
	}
	
	def operator_add(Iterable<FormattingData> data) {
		if(data != null)
			data.forEach[addFormatting]
	}
	
	def operator_add((FormattableDocument) => Iterable<FormattingData> data) {
		if(data != null)
			operator_add(data.apply(this))
	}
	
	def List<TextReplacement> renderToEdits() {
		renderToEdits(0, document.length)
	}

	def List<TextReplacement> renderToEdits(int offset, int length) {
		val replacements = <TextReplacement>newArrayList
		var oldOffset = offset
		var indentation = 0
		for (f : formattings.values) {
			indentation = indentation + f.indentationChange
			if (f.offset >= offset && f.offset + f.length <= offset + length) {
				val textlength = f.offset - oldOffset
				switch f {
					WhitespaceData: {
						if (f.space != null) {
							val replacement = f.space
							replacements += new TextReplacement(f.offset, f.length, replacement)
						}
					}
					NewLineData: {
						val replacement = getWrap(f.newLines) + getIndentation(indentation)
						replacements += new TextReplacement(f.offset, f.length, replacement)
					}
				}
				oldOffset = textlength + f.length
			}
		}
		replacements
	}
	
	def String renderToString() {
		renderToString(0, document.length)
	}
	
	def String renderToString(int offset, int length) {
		val edits = renderToEdits(offset, length)
		var lastOffset = offset
		val newDocument = new StringBuilder()
		for(edit:edits.sortBy[offset]) {
			val text = document.substring(lastOffset, edit.offset)
			newDocument.append(text)
			newDocument.append(edit.text)
			lastOffset = edit.offset + edit.length
		}
		val text = document.substring(lastOffset, offset + length)
		newDocument.append(text)
		newDocument.toString
		
	}
	
	def protected boolean isWhitespace(String doc) {
		(0..doc.length-1).forall[Character::isWhitespace(doc.charAt(it))]
	}
	
	def int lineLengthBefore(int offset) {
		var currentIndentation = 0
		var NewLineData lastWrap = null
		var lastIndentation = 0
		
		// find last NewLineData before offset
		for(f:formattings.values) 
			if(f.offset < offset) {
				currentIndentation = currentIndentation + f.indentationChange
				if(f instanceof NewLineData) {
					lastWrap = f as NewLineData
					lastIndentation = currentIndentation
				}
			}
			
		// check if there is a \n between the NewLineData and offset 
		var lastOffset = lastWrap.offset + lastWrap.length
		var lineStart = lastOffset
		for(f:formattings.subMap(lastWrap.offset + 1, offset).values) {
			val text = document.substring(lastOffset, f.offset)
			val index = text.lastIndexOf("\n") 
			if(index >= 0 ) {
				lineStart = index + lastOffset
				currentIndentation = 0
			}
			lastOffset = f.offset + f.length
		}
		
		// calculate length between \n or NewLineData and offset
		var lengthDiff = 0
		for(f:formattings.subMap(lastWrap.offset + 1, offset).values) 
			if(f instanceof WhitespaceData)
				lengthDiff = lengthDiff + (((f as WhitespaceData).space?.length ?: 0) - f.length)
		(offset - lineStart) + getIndentationLenght(currentIndentation) + lengthDiff  
	}
	
	def String lookahead(int offset, int length, (FormattableDocument)=>void format) {
		val lookahead = new FormattableDocument(this)
		format.apply(lookahead)
		lookahead.renderToString(offset, length)
	}
	
	def boolean fitsIntoLine(int offset, int length, (FormattableDocument)=>void format) {
		val lookahead = lookahead(offset, length, format)
		if(lookahead.contains("\n")) {
			return false
		} else {
			val line = lineLengthBefore(offset) + lookahead.length
			return line <= cfg.get(keys.maxLineWidth)
		}
	}
	
	override toString() {
		var lastOffset = 0
		val debugTrace = new StringBuilder()
		for(edit:renderToEdits) {
			val text = document.substring(lastOffset, edit.offset)
			debugTrace.append(text)
			debugTrace.append('''[�document.substring(edit.offset, edit.offset + edit.length)�|�edit.text�]''')
			lastOffset = edit.offset + edit.length
		}
		val text = document.substring(lastOffset, document.length)
		debugTrace.append(text)
		debugTrace.toString
	}
	
	def getIndentation(int levels) {
		if (levels > 0) {
			val indent = cfg.get(keys.indentation)
			(0 .. levels - 1).map[indent].join
		} else
			""
	}

	def getIndentationLenght(int levels) {
		levels * cfg.get(keys.indentationLength)
	}

	def getWrap(int levels) {
		if (levels > 0) {
			val sep = cfg.get(keys.lineSeparator)
			(0 .. levels - 1).map[sep].join
		} else
			""
	}
	
	
}

@Data abstract class FormattingData {
	int offset
	int length
	int indentationChange
	Throwable trace
	def boolean isEmpty() 
}

@Data class WhitespaceData extends FormattingData {
	String space

	override isEmpty() {
		space == null
	}
}

@Data class NewLineData extends FormattingData {
	int newLines
	
	override isEmpty() {
		newLines == 0
	}
}