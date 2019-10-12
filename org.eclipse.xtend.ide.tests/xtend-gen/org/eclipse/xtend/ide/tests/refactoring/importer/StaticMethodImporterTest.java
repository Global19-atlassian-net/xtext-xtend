/**
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.tests.refactoring.importer;

import com.google.inject.Inject;
import org.eclipse.xtend.ide.tests.AbstractXtendUITestCase;
import org.eclipse.xtend.ide.tests.WorkbenchTestHelper;
import org.eclipse.xtend.ide.tests.refactoring.importer.StaticMethodImporterTestBuilder;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vivien.jovet - Initial contribution and API
 */
@SuppressWarnings("all")
public class StaticMethodImporterTest extends AbstractXtendUITestCase {
  @Inject
  @Extension
  private StaticMethodImporterTestBuilder builder;
  
  @Inject
  @Extension
  private WorkbenchTestHelper testHelper;
  
  @Before
  public void setupTestClass() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package test.other");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Other {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("def static String doSomething(String arg0) {}");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("def static String doSomething(String arg0, String arg1) {}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this.testHelper.createFile("test/other/Other.xtend", _builder.toString());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSingleStaticMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.doSo|mething(\'test\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testMultiStaticMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("val test = Other.doSomething(\'test0\')");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test1() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.doSomething(\'test1\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test2() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.d|oSomething(\'test2\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("val test = doSomething(\'test0\')");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test1() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test1\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test2() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test2\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testSingleExplicitStaticMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other::doSo|mething(\'test\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testMultiExplicitStaticMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("val test = Other::doSomething(\'test0\')");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test1() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other::doSomething(\'test1\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test2() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other::d|oSomething(\'test2\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("val test = doSomething(\'test0\')");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test1() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test1\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test2() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test2\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testMultiMixExplicitStaticMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("val test = Other.doSomething(\'test0\')");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test1() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other::doSomething(\'test1\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test2() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.d|oSomething(\'test2\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("val test = doSomething(\'test0\')");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test1() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test1\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test2() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test2\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testStaticMethodsDifferentArgs() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.doSo|mething(\'test\')");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.doSomething(\'test\', \'test\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import test.other.Other");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("Other.doSomething(\'test\', \'test\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testStaticMethodInLambda() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("#[\'1\', \'2\', \'3\'].forEach[Other.doSo|mething(it)]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("#[\'1\', \'2\', \'3\'].forEach[doSomething(it)]");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testStaticMethodInBrackets() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("(Other.doSo|mething(\'test\'))");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("(doSomething(\'test\'))");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testStaticMethodMultiClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.doSo|mething(\'test\')");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.doSomething(\'test\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test2 {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test2() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.doSomething(\'test\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test2 {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test2() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testStaticMethodNested() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.doSo|mething(");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("Other.doSomething(Other.doSomething(\'test\'))");
    _builder.newLine();
    _builder.append("        ");
    _builder.append(")");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("doSomething(doSomething(\'test\'))");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append(")");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testStaticMethodInSwith() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("switch(Other.doSo|mething(\'test\')) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("switch Other.doSomething(\'test\') {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("switch(doSomething(\'test\')) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("switch doSomething(\'test\') {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testStaticMethodInIf() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("if(Other.doSo|mething(\'test\') !== null) {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("if(doSomething(\'test\') !== null) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testBadFormat() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other .      doSo|mething(\'test\')");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other.");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("doSomething(\'test\')");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other");
    _builder.newLine();
    _builder.append("             ");
    _builder.append(".doSomething(\'test\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("               ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.newLine();
    _builder_1.append("             ");
    _builder_1.append("doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
  
  @Test
  public void testInComments() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import test.other.Other");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("// Other.doSomething(\'test\')");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("/* Other.doSomething(\'test\') */");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other /* test */ .doSo|mething(\'test\')");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("Other. /* test */ doSomething(\'test\')");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StaticMethodImporterTestBuilder _create = this.builder.create("test/Test.xtend", _builder.toString());
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package test");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import static test.other.Other.doSomething");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class Test {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("def test() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("// Other.doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("/* Other.doSomething(\'test\') */");
    _builder_1.newLine();
    _builder_1.append("         ");
    _builder_1.append("/* test */ doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("         ");
    _builder_1.append("/* test */ doSomething(\'test\')");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _create.assertResult(_builder_1.toString());
  }
}