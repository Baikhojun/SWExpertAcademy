package com.excel.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JTable;

public class Calculator {

	private String[] delim = { "+", "-", "*", "/", "(", ")" };

	private Stack<String> tokenStack = new Stack<String>();

	private Stack<String> exprStack = new Stack<String>();

	private List<String> postOrderList = new ArrayList<String>();

	public void calculator(String str,JTable tb) {

		Calculator cal = new Calculator();
System.out.println(tb.getSelectedRow()+","+(tb.getSelectedColumn()));
String result =String.valueOf(cal.calculate(str));
		tb.setValueAt(result, tb.getSelectedRow(), tb.getSelectedColumn());
		
	}

	/**
	 * 토큰분류
	 * 
	 * @param s
	 * @return
	 */
	private void makeTokens(String s) {
		StringBuffer tokenBuf = new StringBuffer();

		int argSize = s.length();
		String token = null;
		for (int i = 0; i < argSize; i++) {
			token = s.substring(i, i + 1);
			if (!isDelim(token)) {
				tokenBuf.append(token);

				if (i == argSize - 1) {
					tokenStack.push(tokenBuf.toString());
				}
			} else {
				if (tokenBuf.length() > 0) {
					tokenStack.push(tokenBuf.toString());
					tokenBuf = new StringBuffer();
				}

				tokenStack.push(token);
			}
		}
	}

	/**
	 * 구분자인지 검사
	 * 
	 * @param s
	 * @return
	 */
	private boolean isDelim(String s) {
		boolean bDelim = false;

		int delimSize = delim.length;
		for (int i = 0; i < delimSize; i++) {
			if (delim[i].equals(s)) {
				bDelim = true;
				break;
			}
		}

		return bDelim;
	}

	/**
	 * 연산자인지 검사
	 * 
	 * @param s
	 * @return
	 */
	private boolean isOpcode(String s) {
		boolean opcode = isDelim(s);

		if ("(".equals(s) || ")".equals(s)) {
			opcode = false;
		}

		return opcode;
	}

	/**
	 * PostOrder로 변환
	 * 
	 * @param expr
	 * @return
	 */
	private void convertPostOrder(String expr) {
		for (String token : tokenStack) {
			
			if (isDelim(token)) {
				exprAppend(token);
			} else {
				postOrderList.add(token);
			}
		}

		// exprStack에 자료가 남아 있으면 출력한다
		String item = null;
		while (!exprStack.isEmpty()) {
			item = exprStack.pop();
			postOrderList.add(item);
		}
	}

	/**
	 * 연산자를 비교해서 postorder방식에 표시 할지 스택에 넣을지 확인
	 * 
	 * @param postOrderBuf
	 */
	private void exprAppend(String token) {
		// '(' 일 경우 스택에 넣는다
		if ("(".equals(token)) {
			exprStack.push(token);
			return;
		}

		// ')' 일 경우 '('가 나올 때 까지 출력한다
		if (")".equals(token)) {
			String opcode = null;
			while (true) {
				opcode = exprStack.pop();
				if ("(".equals(opcode)) {
					break;
				}

				postOrderList.add(opcode);
			}

			return;
		}

		// 연산자일 경우 스택에서 pop하여 낮은 우선순위를 만날때 까지 출력하고 자신을 push
		if (isOpcode(token)) {
			String opcode = null;
			while (true) {
				if (exprStack.isEmpty()) {
					exprStack.push(token);
					break;
				}

				opcode = exprStack.pop();
				if (exprOrder(opcode) > exprOrder(token)) {
					postOrderList.add(opcode);
				} else {
					exprStack.push(opcode);
					exprStack.push(token);
					break;
				}
			}

			return;
		}
	}

	/**
	 * 값을 계산한다
	 * 
	 * @return
	 */
	public double calculate(String expr) {
		// 토근을 만든다
		makeTokens(expr);

		// postOrder로 변환
		convertPostOrder(expr);

		Stack<String> calcStack = new Stack<String>();

		// postOrder 리스트의 값을 calcStack에 저장
		for (String s : postOrderList) {
			calcStack.push(s);
			calcPostOrder(calcStack);
		}

		// 스택의 최종 값을 반환한다
		double result = Double.parseDouble(calcStack.pop());

		return result;
	}

	/**
	 * 스택에서 pop연산을 하여 계산 가능하면 계산 후 값을 스택에 넣는다
	 * 
	 * @param calcStack
	 */
	private void calcPostOrder(Stack<String> calcStack) {
		// 연산자가 아니면 계산을 하지 않는다
		if (!isOpcode(calcStack.lastElement())) {
			return;
		}

		// 3개를 꺼내야 하므로 3개 이상인지 검사
		String op1 = null;
		String op2 = null;
		String opcode = null;
		if (calcStack.size() >= 3) {
			opcode = calcStack.pop();
			op1 = calcStack.pop();
			op2 = calcStack.pop();

			// 값을 계산하여 스택에 넣는다
			double result = calculateByOpCode(op1, op2, opcode);
			calcStack.push(Double.toString(result));
		}
	}

	private double calculateByOpCode(String op1, String op2, String opcode) {
		double d1 = Double.parseDouble(op1);
		double d2 = Double.parseDouble(op2);

		double result = 0.0;
		if ("+".equals(opcode)) {
			result = d2 + d1;
		} else if ("-".equals(opcode)) {
			result = d2 - d1;
		} else if ("*".equals(opcode)) {
			result = d2 * d1;
		} else if ("/".equals(opcode)) {
			result = d2 / d1;
		}

		return result;
	}

	/**
	 * 연산자 순위를 리턴한다
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public int exprOrder(String s) {
		int order = -1;
		if ("-".equals(s) || "+".equals(s)) {
			order = 0;
		} else if ("*".equals(s) || "/".equals(s)) {
			order = 1;
		}

		return order;
	}

}
