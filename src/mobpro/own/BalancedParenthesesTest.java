package mobpro.own;

import static org.junit.Assert.*;

import org.junit.Test;

public class BalancedParenthesesTest {

	@Test
	public void test_奇数長の場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isOdd("["));
	}

	@Test
	public void test_偶数長の場合はFalseが返却される() {
		assertFalse(BlncdPrnths.isOdd("[["));
	}

	@Test
	public void test_入れ子の場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isHang("([)]"));
		assertFalse(BlncdPrnths.isHang("()[]"));
		assertFalse(BlncdPrnths.isHang("[][]"));
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
