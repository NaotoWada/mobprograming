package mobpro.own;

import java.util.Arrays;
import java.util.List;

public class BalancedParentheses {

	private static final List<String> CLOSE_LIST = Arrays.asList("]", ")", "}");
	private static final List<String> OPEN_LIST = Arrays.asList("[", "(", "{");

	public static boolean isOdd(String parentheses) {
		if (parentheses.length() % 2 == 0) {
			return false;
		}
		return true;
	}

	public static boolean isFirstCharClosed(String string) {
		if (CLOSE_LIST.contains(string.substring(0, 1))) {
			return true;
		}
		return false;
	}

	public static boolean isLastCharOpen(String string) {
		int len = string.length();
		if (OPEN_LIST.contains(string.substring(len-1, len))) {
			return true;
		}
		return false;
	}
}
