package mobpro.own;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BlncdPrnths {

	private static final List<String> CLOSE_LIST = Arrays.asList("]", ")", "}");
	private static final List<String> OPEN_LIST = Arrays.asList("[", "(", "{");
	private static final Map<String, String> PAIR = new HashMap<>();
	static {
		for (int i = 0; i < 3; i++) {
			String op = OPEN_LIST.get(i);
			String cl = CLOSE_LIST.get(i);
			PAIR.put(op, cl);
		}
	}

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

	public static boolean editStack(Stack<String> stck, String prnths) {
		if (CLOSE_LIST.contains(prnths)) {
			String stackedPair = PAIR.get(stck.lastElement());
			if (prnths.equals(stackedPair)) {
				stck.pop();
			}
			return false;
		}
		stck.push(prnths);
		return true;
	}
}
