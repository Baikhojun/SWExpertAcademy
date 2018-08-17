package com.excel.function;

import javax.swing.JTable;

public class FxIf {
	// 루프문 출력 메서드
	public void loop(JTable tb, String str) {
		FxModule fm = new FxModule();
		// 반환할 결과를 저장할 공간
		boolean result = false;
		str = str.substring(4, str.length() - 1);

		// 특수문자를 판별해 참일경우 트루 출력
		if (str.contains(">")) {
			String[] stArr = fm.ifCoordinate(str);
			for (int i = 0; i < stArr.length; i = i + 4) {
				if (getCompareTo(tb, stArr, i) == true) {
					result = true;
				}
			}
			tb.setValueAt(String.valueOf(result), tb.getSelectedRow(), tb.getSelectedColumn());
		} else if (str.contains("<")) {
			String[] stArr = fm.ifCoordinate(str);
			for (int i = 0; i < stArr.length; i = i + 4) {
				if (getCompareTo(tb, stArr, i) != true) {
					result = true;
				}
			}
			tb.setValueAt(String.valueOf(result), tb.getSelectedRow(), tb.getSelectedColumn());
		}
	}

	// 루프비교
	public boolean getCompareTo(JTable tb, String[] stArr, int i) {
		return (Double.parseDouble((String) tb.getValueAt(Integer.parseInt(stArr[i]),
				Integer.parseInt(stArr[i + 1])))) > (Double.parseDouble(
						(String) tb.getValueAt(Integer.parseInt(stArr[i + 2]), Integer.parseInt(stArr[i + 3]))));
	}

}
