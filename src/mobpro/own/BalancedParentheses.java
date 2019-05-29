package mobpro.own;

public class BalancedParentheses {

	public static boolean isOdd(String parentheses) {
		if(parentheses.length() % 2 ==0) {
			return false;
		}
		return true;
	}
}
