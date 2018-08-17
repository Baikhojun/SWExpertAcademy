package com.excel.function;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JTable;

import com.excel.function.Calculator;

public class Calculator {
	private String[] oper = { "+", "-", "*", "/", "(", ")" };
	private Stack<String> tokenStack = new Stack<String>();
	private Stack<String> exprStack = new Stack<String>();
	private List<String> postOrderList = new ArrayList<String>();

	public void calculator(String str, JTable tb) {

		Calculator cal = new Calculator();
		FxCalc fc = new FxCalc();
		System.out.println(tb.getSelectedRow() + "," + (tb.getSelectedColumn()));
		double result = Double.parseDouble(cal.calculate(str));
		fc.setResult(tb, result);

	}

	public void splitFx(String fx) {
		StringBuffer tokenBuff = new StringBuffer();
		// 한글자씩 조갠 값을 담을버퍼
		for (int i = 0; i < fx.length(); i++) {
			String token = fx.substring(i, i + 1);
			// 한글자씩 쪼갬
			if (!isOper(token)) {
				// 쪼갠 글자를 연사자와 같은제 비교
				tokenBuff.append(token);
				// 연산자가 아닌경우 다시 합쳐 원래의 숫자로 만들어줌
				if (i == fx.length() - 1) {
					// 마지막 값일경우 뒤에 연산자가 없어 끝을 판단해줌
					tokenStack.push(tokenBuff.toString());
				}
			} else {
				if (tokenBuff.length() > 0) {
					tokenStack.push(tokenBuff.toString());
					tokenBuff = new StringBuffer();
					// 스택에 담은 후 버퍼 초기화
				}
				tokenStack.push(token);
			}
		}

	}

	public void convertPostOrder() {

		for (String token : tokenStack) {
			if (isOper(token)) {
				exprAppend(token);

			} else {
				postOrderList.add(token);

			}

		}
		String item = null;
		while (!exprStack.isEmpty()) {
			item = exprStack.pop();
			postOrderList.add(item);
		}

	}

	public void exprAppend(String token) {
		if ("(".equals(token)) {
			exprStack.push(token);
			return;
		}
		if (")".equals(token)) {
			String pstack = null;
			while (true) {
				pstack = exprStack.pop();

				if ("(".equals(pstack)) {
					break;
				}
				postOrderList.add(pstack);
			}
			return;

		}
		if (isOper(token)) {
			while (true) {
				String pstack = null;
				if (exprStack.isEmpty()) {
					exprStack.push(token);

					break;
				}
				pstack = exprStack.pop();
				// 앞에 연산자와 우선순위 비교
				if (orderOfPriority(token) > orderOfPriority(pstack)) {
					exprStack.push(pstack);
					exprStack.push(token);

					break;
				} else {
					postOrderList.add(pstack);
				}
			}
			return;
		}

	}

	public int orderOfPriority(String token) {
		// 연산자 우선순위 반환
		int result = token.equals("*") || token.equals("/") ? 1 : token.equals("+") || token.equals("-") ? 0 : -1;

		return result;

	}

	public boolean isOper(String token) {
		boolean result = false;

		for (String op : oper) {
			if (op.equals(token)) {
				result = true;
				break;
			}
		}

		return result;

	}

	public String calcPostOrder() {
		String result = null;
		Stack<String> calcStack = new Stack<String>();
		try {
			for (int i = 0; i < postOrderList.size(); i++) {
				double num1;
				double num2;
				String token = postOrderList.get(i);

				if (isOper(token)) {

					num2 = Double.parseDouble(calcStack.pop());
					num1 = Double.parseDouble(calcStack.pop());

					calcStack.push(operation(num1, num2, token));

				} else {
					calcStack.push(token);
				}

			}
			result = calcStack.pop();
			System.out.println(result);
		} catch (EmptyStackException e) {
			// TODO: handle exception
		}

		return result;
	}

	public String operation(double num1, double num2, String token) {
		double result = 0;
		switch (token) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			result = num1 / num2;
			break;

		default:
			break;
		}

		return String.valueOf(result);
	}

	public String calculate(String str) {
		Calculator c = new Calculator();
		String result = null;
		// 입력받은 str을 연산기호로 구분하여 자름
		c.splitFx(str);
		// 중위식을 후위식으로 변환
		c.convertPostOrder();
		// 후위식을 계산
		result = c.calcPostOrder();
		return result;

	}

}
