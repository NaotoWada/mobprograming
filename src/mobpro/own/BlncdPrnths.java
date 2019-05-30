package mobpro.own;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class BlncdPrnths {

	// 不変リストにしてもいいけど、今回の場合そこまで重要じゃないので可変リストで宣言
	private static final List<String> CLOSE_LIST = new ArrayList<>();
	private static final List<String> OPEN_LIST = new ArrayList<>();
	private static final Map<String, String> PAIR = new HashMap<>();
	static {
		PAIR.put("[", "]");
		PAIR.put("(", ")");
		PAIR.put("{", "}");
		for (Entry<String, String> entry : PAIR.entrySet()) {
			OPEN_LIST.add(entry.getKey());
			CLOSE_LIST.add(entry.getValue());
		}
	}

	/**
	 * 入力文字列のカッコがペアであり入れ子でない事を判定する実行関数.
	 * <p>入力文字は次のダブルクォートで囲まれた6文字のみ許容する{@code "([{)]}" }
	 * <p>許容された文字列以外が入力された場合動作を保証しない
	 * @param brackets
	 * 		入力文字列
	 * @return 正常なペアの場合PARENTHESES.BALANCED、それ以外はPARENTHESES.UNBALANCED
	 */
	public static PARENTHESES judge(String brackets) {

		// スタックに詰めつつ判定を行う。1回でも入れ子になったら終了
		Stack<String> openBracketStack = new Stack<>();
		for (int i = 0; i < brackets.length(); i++) {
			String singleBracket = brackets.substring(i, i + 1);
			if (!isBalancedWhenEditingStack(openBracketStack, singleBracket)) {
				return PARENTHESES.UNBALANCED;
			}
		}

		if (openBracketStack.isEmpty()) {
			// すべての要素が入れ子でなかった
			return PARENTHESES.BALANCED;

		} else {
			// 開きカッコが多い場合はスタックに要素が残ってしまう
			return PARENTHESES.UNBALANCED;
		}

	}

	/**
	 * 入力されたスタックインスタンスを書き換えながら、ペア判定を行う関数
	 * <p>スタックに格納されるのは”開きカッコ”のみ
	 * <p>第二引数に閉じカッコを指定した場合、最後にスタックされたとのペアであればその要素をpopする
	 * 
	 * @param openBracketStack
	 * 		スタックインスタンス。入力の参照は破壊的に変更される
	 * @param bracket
	 * 		スタックまたはpopするためのカッコ要素
	 * @return true(開きカッコまたは閉じカッコで条件に一致した場合）/ false(それ以外)
	 */
	public static boolean isBalancedWhenEditingStack(Stack<String> openBracketStack, String bracket) {
		if (OPEN_LIST.contains(bracket)) {
			// 開きカッコの場合は無条件で追加して閉じカッコと同じ種類か判定する
			openBracketStack.push(bracket);
			return true;
		}

		// 閉じカッコの場合
		if (openBracketStack.isEmpty()) {
			// スタックが空の状態で閉じカッコが入った場合は閉じカッコが多い場合
			return false;
		}

		if (isPair(openBracketStack.lastElement(), bracket)) {
			// スタックにペアが存在するので、ペアがあれば取り除く。
			openBracketStack.pop();
			return true;

		} else {
			// ペアがない場合、入れ子の状態
			return false;
		}
	}

	/**
	 * カッコが同じ種類のペアかどうか判定する.
	 * <p>第一引数に閉じカッコを指定した場合、使用条件に合わないので{@code IllegalArgumentException}をスローする
	 * @param openBracketStackElement
	 * 	スタックされたカッコ要素
	 * @param target
	 * 	判定したいカッコ要素
	 * @return 同じ種類の開きカッコに対応した閉じカッコであればtrue
	 */
	public static boolean isPair(String openBracketStackElement, String target) {
		if (CLOSE_LIST.contains(openBracketStackElement)) {
			throw new IllegalArgumentException("スタックされた要素に閉じカッコが入っていて不正です");
		}
		return PAIR.get(openBracketStackElement).equals(target);
	}
}
