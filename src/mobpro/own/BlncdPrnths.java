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

	/**
	 * 入力文字列のカッコがペアであり入れ子でない事を判定する実行関数.
	 * <p>入力文字は次のダブルクォートで囲まれた6文字のみ許容する{@code "([{)]}" }
	 * <p>許容された文字列以外が入力された場合動作を保証しない
	 * @param string
	 * 		入力文字列
	 * @return 正常なペアの場合PARENTHESES.BALANCED、それ以外はPARENTHESES.UNBALANCED
	 */
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

	/**
	 * 入力されたスタックインスタンスを書き換えながら、ペア判定を行う関数
	 * <p>スタックに格納されるのは”開きカッコ”のみ
	 * <p>第二引数に閉じカッコを指定した場合、最後にスタックされたとのペアであればその要素をpopする
	 * 
	 * @param stck
	 * 		スタックインスタンス。入力の参照は破壊的に変更される
	 * @param prnths
	 * 		スタックまたはpopするためのカッコ要素
	 * @return true(開きカッコまたは閉じカッコで条件に一致した場合）/ false(それ以外)
	 */
	public static boolean isPairWhenEditingStack(Stack<String> stck, String prnths) {
		if (OPEN_LIST.contains(prnths)) {
			// 開きカッコの場合
			stck.push(prnths);
			return true;
		}

		// 閉じカッコの場合
		if (stck.isEmpty()) {
			// スタックが空の状態で閉じカッコが入った場合は閉じカッコが多い場合
			return false;
		}
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
}
