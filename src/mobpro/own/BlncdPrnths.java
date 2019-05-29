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

	/**
	 * カッコが同じ種類のペアかどうか判定する.
	 * <p>第一引数に閉じカッコを指定した場合、使用条件に合わないので{@code IllegalArgumentException}をスローする
	 * @param stackElement
	 * 	スタックされたカッコ要素
	 * @param target
	 * 	判定したいカッコ要素
	 * @return 同じ種類の開きカッコに対応した閉じカッコであればtrue
	 */
	public static boolean isPair(String stackElement, String target) {
		if (CLOSE_LIST.contains(stackElement)) {
			throw new IllegalArgumentException("スタックされた要素に閉じカッコが入っていて不正です");
		}
		return PAIR.get(stackElement).equals(target);
	}

	public static PARENTHESES judge(String string) {
		if (isFirstClose(string) || isLastOpen(string)) {
			// 最初と最後の要素が規定外の場合
			return PARENTHESES.UNBALANCED;
		}
		if (isOdd(string)) {
			// 奇数の場合は規定外
			return PARENTHESES.UNBALANCED;
		}

		// スタックに詰めつつ判定を行う。1回でも入れ子になったら終了
		Stack<String> stk = new Stack<>();
		for (int i = 0; i < string.length(); i++) {
			String elm = string.substring(i, i + 1);
			boolean isContinue = isPairWhenEditingStack(stk, elm);
			if (!isContinue) {
				return PARENTHESES.UNBALANCED;
			}
		}

		// すべての要素が入れ子でなかった
		return PARENTHESES.BALANCED;
	}
}
