/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.parser.antlr

import java.io.Reader
import org.eclipse.xtend.core.parser.ReaderCharStream
import org.eclipse.xtext.parser.antlr.AbstractAntlrParserBasedTokenSourceProvider
import com.google.inject.Singleton

/**
 * @author kosyakov - Initial contribution and API
 */
@Singleton
class XtendTokenSourceProvider extends AbstractAntlrParserBasedTokenSourceProvider {

	protected override getCharStream(Reader reader) {
		new ReaderCharStream(reader)
	}

}
