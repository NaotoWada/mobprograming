package mobpro.own;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class BalancedParenthesesTest {

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
}
