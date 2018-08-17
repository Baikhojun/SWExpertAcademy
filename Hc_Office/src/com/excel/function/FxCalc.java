package com.excel.function;

import javax.swing.JTable;

public class FxCalc {

	// 사칙연산 계산 메서드
	public void calculate(String function, String str, JTable tb) throws Exception {
		FxModule fm = new FxModule();
		// 혼합좌표를 숫자좌표로 변환해 배열에 저장

		String[] stArr = fm.selCoordinate(str);
		// 최종 계산 값 저장 변수
		double result = 0;
		// 콤마 함수의 경우
		if (str.contains(",")) {
			switch (function) {
			case "SUM":
				result = sumComma(stArr, tb, result);
				break;
			case "SUB":
				result = subComma(stArr, tb, result);
				break;
			case "MUT":
				result = mutComma(stArr, tb, result);
				break;
			case "DIV":
				result = divComma(stArr, tb, result);
				break;
			default:
			}
		} else if (str.contains(":")) { // 콜론 함수의 경우

			// 두 셀의 좌표를 임의의 순서로 변수에 담음
			int cMax, cMin, rMax, rMin;

			rMax = Math.max((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
			rMin = Math.min((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
			cMax = Math.max((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
			cMin = Math.min((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));

			// 작은 좌표에서 큰 좌표까지의 범위 순서대로 계산함
			switch (function) {
			case "SUM":
				result = sumColon(rMin, rMax, cMin, cMax, tb, result);
				break;
			case "SUB":
				result = subColon(rMin, rMax, cMin, cMax, tb, result);
				break;
			case "MUT":
				result = mutColon(rMin, rMax, cMin, cMax, tb, result);
				break;
			case "DIV":
				result = divColon(rMin, rMax, cMin, cMax, tb, result);
				break;
			case "AVE":
				result = countAverage(rMin, rMax, cMin, cMax, tb, result);
			default:
			}
		}
		// 최종값을 셀에 입력

		setResult(tb, result);

	}

	// 덧셈 추가 메서드

	public double sumComma(String[] stArr, JTable tb, double result) {
		for (int i = 0; i < stArr.length; i += 2) {
			result += Double.parseDouble(commaValue(tb, stArr, i));
		}
		return result;
	}

	public double sumColon(int rMin, int rMax, int cMin, int cMax, JTable tb, double result) {
		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				result += Double.parseDouble((String) tb.getValueAt(i, j));
			}
		}
		return result;
	}

	// 곱셈 추가 메서드

	public double mutComma(String[] stArr, JTable tb, double result) {
		result = 1;
		for (int i = 0; i < stArr.length; i += 2) {
			result *= Double.parseDouble(commaValue(tb, stArr, i));
		}
		return result;
	}

	public double mutColon(int rMin, int rMax, int cMin, int cMax, JTable tb, double result) {
		result = 1;
		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				result *= Double.parseDouble((String) tb.getValueAt(i, j));
			}
		}
		return result;
	}

	// 뺼셈 추가 메서드

	public double subComma(String[] stArr, JTable tb, double result) {
		result = Double.parseDouble((String) tb.getValueAt(Integer.parseInt(stArr[0]), Integer.parseInt(stArr[1])));
		for (int i = 2; i < stArr.length; i += 2) {
			result -= Double.parseDouble(commaValue(tb, stArr, i));
		}
		return result;
	}

	public double subColon(int rMin, int rMax, int cMin, int cMax, JTable tb, double result) {
		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				result -= Double.parseDouble((String) tb.getValueAt(i, j));
			}
		}
		return result += 2*Double.parseDouble((String) tb.getValueAt(rMin, cMin));
	}

	// 나눗셈 추가 메서드

	public double divComma(String[] stArr, JTable tb, double result) {
		result = Double.parseDouble((String) tb.getValueAt(Integer.parseInt(stArr[0]), Integer.parseInt(stArr[1])));
		for (int i = 2; i < stArr.length; i += 2) {
			result /= Double.parseDouble(commaValue(tb, stArr, i));
		}
		return result;
	}

	public double divColon(int rMin, int rMax, int cMin, int cMax, JTable tb, double result) {
		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				if (i == rMin && j == cMin) {
					result += Double.parseDouble((String) tb.getValueAt(i, j));
				} else {
					result /= Double.parseDouble((String) tb.getValueAt(i, j));
				}
			}
		}
		return result;
	}

	// 평균 메서드

	public double countAverage(int rMin, int rMax, int cMin, int cMax, JTable tb, double result) {
		int count = 0;
		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				result += Double.parseDouble((String) tb.getValueAt(i, j));
				count++;
			}
		}
		return (Math.round((result / count) * 100)) * 0.01;
	}

	// 콤마 셈
	public String commaValue(JTable tb, String[] stArr, int i) {
		return (String) tb.getValueAt(Integer.parseInt(stArr[i]), Integer.parseInt(stArr[i + 1]));
	}
	
	
	//소수점이 없으면 인트형으로 변환
	public void setResult(JTable tb,double result){
	      
	      if(result%1.0!=0){
	         tb.setValueAt(String.valueOf((Math.round(result*1000))*0.001),tb.getSelectedRow(),tb.getSelectedColumn());
	      }else{
	         tb.setValueAt(String.valueOf((int)result),tb.getSelectedRow(),tb.getSelectedColumn());
	      }      
	      
	      
	   }
}
