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

	public static boolean isPairWhenEditingStack(Stack<String> stck, String prnths) {
		if (OPEN_LIST.contains(prnths)) {
			// 開きカッコの場合
			stck.push(prnths);
			return true;
		}

		// 閉じカッコの場合
		if (isPair(stck.lastElement(), prnths)) {
			stck.pop();
			return true;
		} else {
			return false;
		}
	}

	public static boolean isPair(String stackElement, String target) {
		if (CLOSE_LIST.contains(stackElement)) {
			throw new IllegalArgumentException("スタックされた要素に閉じカッコが入っていて不正です");
		}
		return PAIR.get(stackElement).equals(target);
	}

	public static PARENTHESES judge(String string) {
		if(isFirstClose(string) || isLastOpen(string)) {
			return PARENTHESES.UNBALANCED;
		}
		if(isOdd(string)) {
			return PARENTHESES.UNBALANCED;
		}
		
		Stack<String> stk = new Stack<>();
		for(int i=0; i<string.length(); i++) {
			String elm = string.substring(i, i+1);
			boolean isContinue = isPairWhenEditingStack(stk, elm);
			if(!isContinue) {
				return PARENTHESES.UNBALANCED;
			}
		}
		return PARENTHESES.BALANCED;
	}
}
