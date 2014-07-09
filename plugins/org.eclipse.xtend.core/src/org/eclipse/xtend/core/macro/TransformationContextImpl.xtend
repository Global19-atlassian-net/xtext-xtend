/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.macro

import org.eclipse.xtend.core.macro.declaration.CompilationUnitImpl
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.AnnotationReference
import org.eclipse.xtend.lib.macro.declaration.Element
import org.eclipse.xtend.lib.macro.declaration.MutableNamedElement
import org.eclipse.xtend.lib.macro.declaration.NamedElement
import org.eclipse.xtend.lib.macro.declaration.Type
import org.eclipse.xtend.lib.macro.declaration.TypeReference
import org.eclipse.xtend.lib.macro.file.Path
import org.eclipse.xtend.lib.macro.services.AnnotationReferenceBuildContext
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0

class TransformationContextImpl implements TransformationContext {
	@Property CompilationUnitImpl unit

	override addError(Element element, String message) {
		unit.problemSupport.addError(element, message)
	}

	override addWarning(Element element, String message) {
		unit.problemSupport.addWarning(element, message)
	}

	override getProblems(Element element) {
		unit.problemSupport.getProblems(element)
	}
	
	override validateLater(Procedure0 validationCallback) {
		unit.problemSupport.validateLater(validationCallback)
	}

	override getAnyType() {
		unit.typeReferenceProvider.anyType
	}

	override getList(TypeReference param) {
		unit.typeReferenceProvider.getList(param)
	}

	override getObject() {
		unit.typeReferenceProvider.getObject
	}

	override getPrimitiveBoolean() {
		unit.typeReferenceProvider.primitiveBoolean
	}

	override getPrimitiveByte() {
		unit.typeReferenceProvider.primitiveByte
	}

	override getPrimitiveChar() {
		unit.typeReferenceProvider.primitiveChar
	}

	override getPrimitiveDouble() {
		unit.typeReferenceProvider.primitiveDouble
	}

	override getPrimitiveFloat() {
		unit.typeReferenceProvider.primitiveFloat
	}

	override getPrimitiveInt() {
		unit.typeReferenceProvider.primitiveInt
	}

	override getPrimitiveLong() {
		unit.typeReferenceProvider.primitiveLong
	}

	override getPrimitiveShort() {
		unit.typeReferenceProvider.primitiveShort
	}

	override getPrimitiveVoid() {
		unit.typeReferenceProvider.primitiveVoid
	}

	override getSet(TypeReference param) {
		unit.typeReferenceProvider.getSet(param)
	}

	override getString() {
		unit.typeReferenceProvider.string
	}

	override newArrayTypeReference(TypeReference componentType) {
		unit.typeReferenceProvider.newArrayTypeReference(componentType)
	}

	override newTypeReference(String typeName, TypeReference... typeArguments) {
		unit.typeReferenceProvider.newTypeReference(typeName, typeArguments)
	}

	override newTypeReference(Type typeDeclaration, TypeReference... typeArguments) {
		unit.typeReferenceProvider.newTypeReference(typeDeclaration, typeArguments)
	}

	override newTypeReference(Class<?> clazz, TypeReference... typeArguments) {
		unit.typeReferenceProvider.newTypeReference(clazz, typeArguments)
	}

	override newWildcardTypeReference() {
		unit.typeReferenceProvider.newWildcardTypeReference
	}

	override newWildcardTypeReference(TypeReference upperBound) {
		unit.typeReferenceProvider.newWildcardTypeReference(upperBound)
	}

	override newWildcardTypeReferenceWithLowerBound(TypeReference upperBound) {
		unit.typeReferenceProvider.newWildcardTypeReferenceWithLowerBound(upperBound)
	}

	override findInterface(String qualifiedName) {
		unit.typeLookup.findInterface(qualifiedName)
	}

	override findClass(String qualifiedName) {
		unit.typeLookup.findClass(qualifiedName)
	}

	override findAnnotationType(String qualifiedName) {
		unit.typeLookup.findAnnotationType(qualifiedName)
	}

	override findEnumerationType(String qualifiedName) {
		unit.typeLookup.findEnumerationType(qualifiedName)
	}

	override findTypeGlobally(Class<? extends Object> clazz) {
		unit.typeLookup.findTypeGlobally(clazz)
	}

	override findTypeGlobally(String typeName) {
		unit.typeLookup.findTypeGlobally(typeName)
	}
	
	override exists(Path path) {
		unit.fileSystemSupport.exists(path)
	}
	
	override getCharset(Path path) {
		unit.fileSystemSupport.getCharset(path)
	}
	
	override getChildren(Path path) {
		unit.fileSystemSupport.getChildren(path)
	}
	
	override getContents(Path path) {
		unit.fileSystemSupport.getContents(path)
	}
	
	override getContentsAsStream(Path path) {
		unit.fileSystemSupport.getContentsAsStream(path)
	}
	
	override getLastModification(Path path) {
		unit.fileSystemSupport.getLastModification(path)
	}
	
	override getProjectFolder(Path path) {
		unit.fileLocations.getProjectFolder(path)
	}
	
	override getSourceFolder(Path path) {
		unit.fileLocations.getSourceFolder(path)
	}
	
	override getTargetFolder(Path sourceFolder) {
		unit.fileLocations.getTargetFolder(sourceFolder)
	}
	
	override isFile(Path path) {
		unit.fileSystemSupport.isFile(path)
	}
	
	override isFolder(Path path) {
		unit.fileSystemSupport.isFolder(path)
	}
	
	override toURI(Path path) {
		unit.fileSystemSupport.toURI(path)
	}
	
	override newAnnotationReference(String annotationTypeName) {
		unit.annotationReferenceProvider.newAnnotationReference(annotationTypeName)
	}
	
	override newAnnotationReference(Type annotationTypeDelcaration) {
		unit.annotationReferenceProvider.newAnnotationReference(annotationTypeDelcaration)
	}
	
	override newAnnotationReference(Class<?> annotationClass) {
		unit.annotationReferenceProvider.newAnnotationReference(annotationClass)
	}
	
	override newAnnotationReference(AnnotationReference annotationReference) {
		unit.annotationReferenceProvider.newAnnotationReference(annotationReference)
	}
	
	override newAnnotationReference(String annotationTypeName, Procedure1<AnnotationReferenceBuildContext> initializer) {
		unit.annotationReferenceProvider.newAnnotationReference(annotationTypeName, initializer)
	}
	
	override newAnnotationReference(Type annotationTypeDelcaration, Procedure1<AnnotationReferenceBuildContext> initializer) {
		unit.annotationReferenceProvider.newAnnotationReference(annotationTypeDelcaration, initializer)
	}
	
	override newAnnotationReference(Class<?> annotationClass, Procedure1<AnnotationReferenceBuildContext> initializer) {
		unit.annotationReferenceProvider.newAnnotationReference(annotationClass, initializer)
	}
	
	override newAnnotationReference(AnnotationReference annotationReference, Procedure1<AnnotationReferenceBuildContext> initializer) {
		unit.annotationReferenceProvider.newAnnotationReference(annotationReference, initializer)
	}
	
	override newSelfTypeReference(Type typeDeclaration) {
		unit.typeReferenceProvider.newSelfTypeReference(typeDeclaration)
	}
	
	override getPrimaryGeneratedJavaElement(NamedElement source) {
		unit.tracability.getPrimaryGeneratedJavaElement(source)
	}
	
	override getPrimarySourceElement(NamedElement target) {
		unit.tracability.getPrimarySourceElement(target)
	}
	
	override isExternal(NamedElement element) {
		unit.tracability.isExternal(element)
	}
	
	override isGenerated(NamedElement element) {
		unit.tracability.isGenerated(element)
	}
	
	override isSource(NamedElement element) {
		unit.tracability.isSource(element)
	}
	
	override isThePrimaryGeneratedJavaElement(NamedElement target) {
		unit.tracability.isThePrimaryGeneratedJavaElement(target)
	}
	
	override setPrimarySourceElement(MutableNamedElement primaryElement, NamedElement secondaryElement) {
		unit.associator.setPrimarySourceElement(primaryElement, secondaryElement)
	}
	
}
