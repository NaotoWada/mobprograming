package mobpro.own;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class BalancedParenthesesTest {

	@Test
	public void test_奇数長の場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isOdd("["));
	}

	@Test
	public void test_偶数長の場合はFalseが返却される() {
		assertFalse(BlncdPrnths.isOdd("[["));
	}

	@Test
	public void test_入れ子の場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isHang("([)]"));
		assertFalse(BlncdPrnths.isHang("()[]"));
		//TODO:キュー実装が終わったら修正する
		//assertFalse(BlncdPrnths.isHang("[][]"));
	}
	
	@Test
	public void test_開きカッコの場合その文字がスタックに積まれる() {
		Stack<String> stck = new Stack<>();
		
		Stack<String> stackOpen = BlncdPrnths.editStack(stck, "(");
		String poll = stackOpen.pop();
		assertEquals(poll, "(");
		assertTrue(stackOpen.isEmpty());
	}

	@Test
	public void test_閉じカッコの場合スタックに積まれた開きカッコがスタックから削除される() {
		Stack<String> stck = new Stack<>();
		stck.push("(");
		
		Stack<String> stackClose = BlncdPrnths.editStack(stck, ")");
		assertTrue(stackClose.isEmpty());
	}
	
	@Test
	public void test_閉じカッコと同じ種類の開きカッコがPOPされる() {
		Stack<String> stck = new Stack<>();
		stck.push("[");
		stck.push("{");
		stck.push("(");
		stck.push("[");

		stck = BlncdPrnths.editStack(stck, "]");
		stck = BlncdPrnths.editStack(stck, ")");
		stck = BlncdPrnths.editStack(stck, "}");
		stck = BlncdPrnths.editStack(stck, ")");
		assertEquals(stck.size(), 1);
	}

	@Test
	public void test_最後が開きカッコの場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isLastOpen("["));
		assertTrue(BlncdPrnths.isLastOpen("("));
		assertTrue(BlncdPrnths.isLastOpen("{"));

		assertTrue(BlncdPrnths.isLastOpen("]["));
		assertTrue(BlncdPrnths.isLastOpen(")("));
		assertTrue(BlncdPrnths.isLastOpen("}{"));

		assertFalse(BlncdPrnths.isLastOpen("]"));
		assertFalse(BlncdPrnths.isLastOpen(")"));
		assertFalse(BlncdPrnths.isLastOpen("}"));
	}

	@Test
	public void test_最初が閉じカッコの場合はTrueが返却される() {
		assertTrue(BlncdPrnths.isFirstClose("]"));
		assertTrue(BlncdPrnths.isFirstClose(")"));
		assertTrue(BlncdPrnths.isFirstClose("}"));

		assertTrue(BlncdPrnths.isFirstClose("]["));
		assertTrue(BlncdPrnths.isFirstClose(")("));
		assertTrue(BlncdPrnths.isFirstClose("}{"));

		assertFalse(BlncdPrnths.isFirstClose("["));
		assertFalse(BlncdPrnths.isFirstClose("("));
		assertFalse(BlncdPrnths.isFirstClose("{"));
	}
}
