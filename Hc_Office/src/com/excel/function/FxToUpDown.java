package com.excel.function;

import javax.swing.JTable;

public class FxToUpDown {

	// 대문자 변환 메서드
	public void toUpperCase(JTable tb, String str) {
		tb.setValueAt(str.substring(7, str.length() - 1).toUpperCase(), tb.getSelectedRow(), tb.getSelectedColumn());
	}

	// 소문자 변환 메서드
	public void toLowerCase(JTable tb, String str) {
		tb.setValueAt(str.substring(7, str.length() - 1).toLowerCase(), tb.getSelectedRow(), tb.getSelectedColumn());
	}

}
