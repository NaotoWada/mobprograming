package mobpro.own;

import static org.junit.Assert.*;

import org.junit.Test;

public class BalancedParenthesesTest {

	@Test
	public void test_奇数長の場合はFlaseが返却される() {
		boolean actual = BalancedParentheses.exec("[");
		assertFalse(actual);
	}
	
}
