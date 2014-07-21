/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend.core.tests.annotations.AnnotationsCompilerTest;
import org.eclipse.xtend.core.tests.compiler.batch.TestBatchCompiler;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@RunWith(Suite.class)
@SuiteClasses({
	AccessorsCompilerTest.class,
	AnnotationCompilerTest.class,
	AnnotationsCompilerTest.class,
	AnnotationValueCompilerTest.class,
	AnonymousClassCompilerTest.class,
	AutocastCompilerTest.class,
	CompilerTest.class,
	CompilerBugTest.class,
	CompilerBug342021Test.class,
	CompilerBug381162Test.class,
	CompilerBug383534Test.class,
	CompilerBug404051Test.class,
	CompilerBug405825Test.class,
	CompilerBug406425Test.class,
	CompilerBug406549Test.class,
	CompilerBug410555Test.class,
	CompilerBug410556Test.class,
	CompilerBug410767Test.class,
	CompilerBug410797Test.class,
	CompilerBug412894Test.class,
	CompilerBug413138Test.class,
	CompilerBug416305Test.class,
	CompilerBug417522Test.class,
	CompilerBug418364Test.class,
	CompilerBug419050Test.class,
	CompilerBug419688Test.class,
	CompilerBug421999Test.class,
	CompilerBug422864Test.class,
	CompilerBug423631Test.class,
	CompilerBug423907Test.class,
	CompilerBug424329Test.class,
	CompilerBug424763Test.class,
	CompilerBug424839Test.class,
	CompilerBug427637Test.class,
	CompilerBug427660Test.class,
	CompilerBug428063Test.class,
	CompilerBug429376Test.class,
	CompilerBug430484Test.class,
	CompilerBug432193Test.class,
	CompilerBug434424Test.class,
	CompilerBug435133Test.class,
	CompilerBug436886Test.class,
	CompilerBug437678Test.class,
	CompilerBug439989Test.class,
	CompilerBugDependentTypeParametersTest.class,
	CompilerBugInheritedDispatchTest.class,
	CompilerBugProtectedVisibilityTest.class,
	CompilerTraceTest.class,
	CompoundAssignmentOperatorCompilerTest.class,
	ConfiguredCompilerTest.class,
	DispatchCompilerTest.class,
	ExtensionsCompilerTest.class,
	OldDataCompilerTest.class,
	NewDataCompilerTest.class,
	AccessorsCompilerTest.class,
	ToStringCompilerTest.class,
	EqualsHashCodeCompilerTest.class,
	DelegateCompilerTest.class,
	FinalFieldsConstructorCompilerTest.class,
	Java8OverloadCompilerTest.class,
	NestedClassCompilerTest.class,
	OverloadedExtensionCompilerTest.class,
	ParameterizedTypeCompilerTest.class,
	PostfixOperatorCompilerTest.class,
	PropertyCompilerTest.class,
	TestBatchCompiler.class,
	XtendCompilerTest.class,
	XtendCompilerErrorHandlingTest.class,
	XbaseIntegrationTest.class
})
public class CompilerSuite {
}
