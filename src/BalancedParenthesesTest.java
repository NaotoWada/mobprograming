import static org.junit.Assert.*;

import org.junit.Test;

public class BalancedParenthesesTest {

	@Test
	public void test_文字列が奇数長だったらTrueを返却する() {
		assertTrue(BalancedParentheses.isOdd("[[["));

	}

	@Test
	public void test_文字列が偶数長だったらfalseを返却する() {
		assertFalse(BalancedParentheses.isOdd("[[[["));

	}
	
	@Test
	public void test_最初の文字列が開きカッコだったらTrueを返却する() {
		assertTrue(BalancedParentheses.isFirstOpenKakko("["));
		assertTrue(BalancedParentheses.isFirstOpenKakko("[[[["));
		assertTrue(BalancedParentheses.isFirstOpenKakko("{"));
		assertTrue(BalancedParentheses.isFirstOpenKakko("{{{{"));
		assertTrue(BalancedParentheses.isFirstOpenKakko("("));
		assertTrue(BalancedParentheses.isFirstOpenKakko("(((("));
	}
	
	@Test
	public void test_最初の文字列が閉じカッコだったらFalseを返却する() {
		assertFalse(BalancedParentheses.isFirstOpenKakko("]"));
		assertFalse(BalancedParentheses.isFirstOpenKakko("]]]"));
		assertFalse(BalancedParentheses.isFirstOpenKakko("}"));
		assertFalse(BalancedParentheses.isFirstOpenKakko("}}}"));
		assertFalse(BalancedParentheses.isFirstOpenKakko(")"));
		assertFalse(BalancedParentheses.isFirstOpenKakko(")))"));
	}
	
	@Test
	public void test_最後の文字列が閉じカッコだったらTrueを返却する() {
		assertTrue(BalancedParentheses.isLastCloseKakko("]"));
	}

}
