package mobpro.own;

import static org.junit.Assert.*;

import org.junit.Test;

public class BalancedParenthesesTest {

	@Test
	public void test_奇数長の場合はTrueが返却される() {
		boolean actual = BalancedParentheses.isOdd("[");
		assertTrue(actual);
	}
	@Test
	public void test_偶数長の場合はFalseが返却される() {
		boolean actual = BalancedParentheses.isOdd("[[");
		assertFalse(actual);
	}
	@Test
	public void test_最初が閉じカッコの場合はFlaseが返却される() {
		boolean actual = BalancedParentheses.isFirstCharClosed("]");
		assertFalse(actual);
	}
}
