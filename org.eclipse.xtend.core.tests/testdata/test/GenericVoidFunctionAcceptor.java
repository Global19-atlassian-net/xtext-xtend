/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package test;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class GenericVoidFunctionAcceptor {

	public static <T> T accept(T t, GenericVoidFunction<T> func) {
		func.doStuff(t);
		return t;
	}

}
