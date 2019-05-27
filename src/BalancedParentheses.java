import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BalancedParentheses {

	private static final Set<Character> OPEN_KAKKO_SET = new HashSet <> (Arrays.asList('{','(','['));

	public static boolean isOdd(String string) {

		int strLength = string.length();
		if (strLength % 2 == 0) {
			return false;
		}

		return true;
	}

	public static boolean isFirstOpenKakko(String string) {
		if (OPEN_KAKKO_SET.contains(string.charAt(0))) {
			return true;
		}
		return false;
	}

	public static boolean isLastCloseKakko(String string) {
		return true;
	}

}
