package mobpro.own;

import static org.junit.Assert.*;

import org.junit.Test;

public class BalancedParenthesesTest {

	@Test
	public void test_奇数長の場合はTrueが返却される() {
		assertTrue(BalancedParentheses.isOdd("["));
	}

	@Test
	public void test_偶数長の場合はFalseが返却される() {
		assertFalse(BalancedParentheses.isOdd("[["));
	}

	@Test
	public void test_最後が開きカッコの場合はTrueが返却される() {
		assertTrue(BalancedParentheses.isLastCharOpen("]"));
	}

	@Test
	public void test_最初が閉じカッコの場合はTrueが返却される() {
		assertTrue(BalancedParentheses.isFirstCharClosed("]"));
		assertTrue(BalancedParentheses.isFirstCharClosed(")"));
		assertTrue(BalancedParentheses.isFirstCharClosed("}"));

		assertTrue(BalancedParentheses.isFirstCharClosed("]["));
		assertTrue(BalancedParentheses.isFirstCharClosed(")("));
		assertTrue(BalancedParentheses.isFirstCharClosed("}{"));
		
		assertFalse(BalancedParentheses.isFirstCharClosed("["));
		assertFalse(BalancedParentheses.isFirstCharClosed("("));
		assertFalse(BalancedParentheses.isFirstCharClosed("{"));
	}
}
