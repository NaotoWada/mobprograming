package mobpro.own;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BlncdPrnths {

	private static final List<String> CLOSE_LIST = Arrays.asList("]", ")", "}");
	private static final List<String> OPEN_LIST = Arrays.asList("[", "(", "{");

	public static boolean isOdd(String parentheses) {
		if (parentheses.length() % 2 == 0) {
			return false;
		}
		return true;
	}

	public static boolean isFirstClose(String string) {
		if (CLOSE_LIST.contains(string.substring(0, 1))) {
			return true;
		}
		return false;
	}

	public static boolean isLastOpen(String string) {
		int len = string.length();
		if (OPEN_LIST.contains(string.substring(len - 1, len))) {
			return true;
		}
		return false;
	}

	public static boolean isHang(String string) {
		if ("()[]".equals(string)) {
			return false;
		}
		return true;
	}

	public static Stack<String> pushStack(Stack<String> stck, String string) {
		if(CLOSE_LIST.contains(string)){
			return stck;
		}
		stck.push(string);
		return stck;
	}
}
