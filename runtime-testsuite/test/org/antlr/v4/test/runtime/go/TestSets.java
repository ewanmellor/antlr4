/* This file is generated by TestGenerator, any edits will be overwritten by the next generation. */
package org.antlr.v4.test.runtime.go;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSets extends BaseTest {

	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testCharSetLiteral() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(84);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (A {fmt.Println($A.text)})+ ;\n");
		grammarBuilder.append("A : [AaBb] ;\n");
		grammarBuilder.append("WS : (' '|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="A a B b";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals(
			"A\n" +
			"a\n" +
			"B\n" +
			"b\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testComplementSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(51);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("parse : ~NEW_LINE;\n");
		grammarBuilder.append("NEW_LINE: '\\r'? '\\n';");
		String grammar = grammarBuilder.toString();
		String input ="a";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "parse", input, false);
		assertEquals("", found);

		assertEquals(
			"line 1:0 token recognition error at: 'a'\n" +
			"line 1:1 missing {} at '<EOF>'\n", this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testLexerOptionalSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(86);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("A : ('a'|'b')? 'c' ;");
		String grammar = grammarBuilder.toString();
		String input ="ac";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("ac\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testLexerPlusSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(86);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("A : ('a'|'b')+ 'c' ;");
		String grammar = grammarBuilder.toString();
		String input ="abaac";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("abaac\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testLexerStarSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(86);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("A : ('a'|'b')* 'c' ;");
		String grammar = grammarBuilder.toString();
		String input ="abaac";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("abaac\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testNotChar() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(52);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println($A.text)} ;\n");
		grammarBuilder.append("A : ~'b' ;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testNotCharSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(58);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println($A.text)} ;\n");
		grammarBuilder.append("A : ~('b'|'c') ;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testNotCharSetWithLabel() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(60);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println($A.text)} ;\n");
		grammarBuilder.append("A : h=~('b'|'c') ;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testNotCharSetWithRuleRef3() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(124);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println($A.text)} ;\n");
		grammarBuilder.append("A : ('a'|B) ;  // this doesn't collapse to set but works\n");
		grammarBuilder.append("fragment\n");
		grammarBuilder.append("B : ~('a'|'c') ;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testOptionalLexerSingleElement() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(80);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("A : 'b'? 'c' ;");
		String grammar = grammarBuilder.toString();
		String input ="bc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("bc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testOptionalSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(78);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ('a'|'b')? 'c' {fmt.Println(p.GetTokenStream().GetAllText())} ;");
		String grammar = grammarBuilder.toString();
		String input ="ac";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("ac\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testOptionalSingleElement() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(80);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A? 'c' {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("A : 'b' ;");
		String grammar = grammarBuilder.toString();
		String input ="bc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("bc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testParserNotSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(56);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : t=~('x'|'y') 'z' {fmt.Println($t.text)} ;");
		String grammar = grammarBuilder.toString();
		String input ="zz";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("z\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testParserNotToken() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(72);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ~'x' 'z' {fmt.Println(p.GetTokenStream().GetAllText())} ;");
		String grammar = grammarBuilder.toString();
		String input ="zz";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("zz\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testParserNotTokenWithLabel() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(50);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : t=~'x' 'z' {fmt.Println($t.text)} ;");
		String grammar = grammarBuilder.toString();
		String input ="zz";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("z\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testParserSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(51);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : t=('x'|'y') {fmt.Println($t.text)} ;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testPlusLexerSingleElement() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(80);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("A : 'b'+ 'c' ;");
		String grammar = grammarBuilder.toString();
		String input ="bbbbc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("bbbbc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testPlusSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(78);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ('a'|'b')+ 'c' {fmt.Println(p.GetTokenStream().GetAllText())} ;");
		String grammar = grammarBuilder.toString();
		String input ="abaac";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("abaac\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testRuleAsSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(85);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a @after {fmt.Println(p.GetTokenStream().GetAllText())} : 'a' | 'b' |'c' ;");
		String grammar = grammarBuilder.toString();
		String input ="b";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("b\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testSeqDoesNotBecomeSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(122);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : C {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("fragment A : '1' | '2';\n");
		grammarBuilder.append("fragment B : '3' '4';\n");
		grammarBuilder.append("C : A | B;");
		String grammar = grammarBuilder.toString();
		String input ="34";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("34\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testStarLexerSingleElement_1() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(80);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("A : 'b'* 'c' ;");
		String grammar = grammarBuilder.toString();
		String input ="bbbbc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("bbbbc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testStarLexerSingleElement_2() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(80);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : A {fmt.Println(p.GetTokenStream().GetAllText())} ;\n");
		grammarBuilder.append("A : 'b'* 'c' ;");
		String grammar = grammarBuilder.toString();
		String input ="c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("c\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* This file and method are generated by TestGenerator, any edits will be overwritten by the next generation. */
	@Test
	public void testStarSet() throws Exception {
		mkdir(parserpkgdir);
		StringBuilder grammarBuilder = new StringBuilder(78);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ('a'|'b')* 'c' {fmt.Println(p.GetTokenStream().GetAllText())} ;");
		String grammar = grammarBuilder.toString();
		String input ="abaac";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
			"TListener", "TVisitor", "a", input, false);
		assertEquals("abaac\n", found);
		assertNull(this.stderrDuringParse);

	}

}
