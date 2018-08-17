package com.excel.function;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JTable;

public class FxDate {
	// 오늘날짜 출력 메서드
	public void today(JTable tb) {
		// 심플데이트포맷에 맞춰 출력
		tb.setValueAt(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), tb.getSelectedRow(),
				tb.getSelectedColumn());

	}

	// 입력한 날짜 출력 메서드
	public void date(JTable tb, String str) {
		// 캘린더 객체 생성
		Calendar today = Calendar.getInstance();
		str = str.substring(6, str.length() - 1);

		// 연,월,일을 추출해 배열에 저장
		String[] sArr = str.split(",");
		int year = Integer.parseInt(sArr[0]);
		int month = (Integer.parseInt(sArr[1])) - 1;
		int date = Integer.parseInt(sArr[2]);

		// 포맷에 맞춰 출력
		today.set(year, month, date);
		tb.setValueAt(new SimpleDateFormat("yyyy-MM-dd").format(today.getTime()), tb.getSelectedRow(),
				tb.getSelectedColumn());
	}

}
