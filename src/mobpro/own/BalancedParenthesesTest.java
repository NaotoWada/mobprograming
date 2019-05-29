package mobpro.own;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class BalancedParenthesesTest {

	@Test
	public void test_カッコペアが正しく指定された場合BALANCEDが返却される() {
		assertEquals(PARENTHESES.BALANCED, BlncdPrnths.judge("()"));
		assertEquals(PARENTHESES.BALANCED, BlncdPrnths.judge("([])"));
		assertEquals(PARENTHESES.BALANCED, BlncdPrnths.judge("([{}])"));
		assertEquals(PARENTHESES.BALANCED, BlncdPrnths.judge("()[]{}()"));
		
		assertEquals(PARENTHESES.UNBALANCED, BlncdPrnths.judge(")("));
		assertEquals(PARENTHESES.UNBALANCED, BlncdPrnths.judge("("));
		assertEquals(PARENTHESES.UNBALANCED, BlncdPrnths.judge("[["));
		assertEquals(PARENTHESES.UNBALANCED, BlncdPrnths.judge("[[]"));
	}
	@Test
	public void test_カッコペアが入れ子指定された場合UNBALANCEDが返却される() {
		assertEquals(PARENTHESES.UNBALANCED, BlncdPrnths.judge("({)}"));
	}
	
	@Test
	public void test_奇数長の場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isOdd("["));
		assertFalse(BlncdPrnths.isOdd("[["));
	}

	@Test
	public void test_閉じカッコと同じ種類の開きカッコがPOPされる() {
		Stack<String> stck = new Stack<>();
		stck.push("{");
		stck.push("(");
		stck.push("[");

		assertTrue(BlncdPrnths.isPairWhenEditingStack(stck, "]"));
		assertTrue(BlncdPrnths.isPairWhenEditingStack(stck, ")"));
		assertTrue(BlncdPrnths.isPairWhenEditingStack(stck, "}"));
		assertTrue(stck.isEmpty());

		stck.push("[");
		assertFalse(BlncdPrnths.isPairWhenEditingStack(stck, ")"));
		assertEquals(stck.size(), 1);
	}

	@Test
	public void test_入れ子の場合スタックに要素が残る() {
		Stack<String> stck = new Stack<>();
		stck.push("[");
		stck.push("{");

		assertFalse(BlncdPrnths.isPairWhenEditingStack(stck, "]"));
		assertTrue(BlncdPrnths.isPairWhenEditingStack(stck, "}"));
		assertEquals(stck.size(), 1);
	}

	@Test
	public void test_最後が開きカッコの場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isLastOpen("["));
		assertTrue(BlncdPrnths.isLastOpen("("));
		assertTrue(BlncdPrnths.isLastOpen("{"));

		assertTrue(BlncdPrnths.isLastOpen("]["));
		assertTrue(BlncdPrnths.isLastOpen(")("));
		assertTrue(BlncdPrnths.isLastOpen("}{"));

		assertFalse(BlncdPrnths.isLastOpen("]"));
		assertFalse(BlncdPrnths.isLastOpen(")"));
		assertFalse(BlncdPrnths.isLastOpen("}"));
	}

	@Test
	public void test_最初が閉じカッコの場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isFirstClose("]"));
		assertTrue(BlncdPrnths.isFirstClose(")"));
		assertTrue(BlncdPrnths.isFirstClose("}"));

		assertTrue(BlncdPrnths.isFirstClose("]["));
		assertTrue(BlncdPrnths.isFirstClose(")("));
		assertTrue(BlncdPrnths.isFirstClose("}{"));

		assertFalse(BlncdPrnths.isFirstClose("["));
		assertFalse(BlncdPrnths.isFirstClose("("));
		assertFalse(BlncdPrnths.isFirstClose("{"));
	}

	@Test
	public void test_同じ種類のカッコの場合trueを返却する() {
		assertTrue(BlncdPrnths.isPair("[", "]"));
		assertTrue(BlncdPrnths.isPair("(", ")"));
		assertTrue(BlncdPrnths.isPair("{", "}"));

		assertFalse(BlncdPrnths.isPair("{", "]"));
		assertFalse(BlncdPrnths.isPair("[", ")"));
		assertFalse(BlncdPrnths.isPair("(", "}"));
	}

	@Test
	public void test_ペアフィールドのキーに閉じカッコを指定するとエラーが発生する() {
		try {
			BlncdPrnths.isPair("]", "");
			fail();
		} catch (IllegalArgumentException e) {
			String exceptMsg = e.getMessage();
			assertEquals(exceptMsg, "スタックされた要素に閉じカッコが入っていて不正です");
		}
	}
}
