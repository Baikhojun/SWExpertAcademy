package com.excel.function;

import javax.swing.JTable;

public class FxCount {

	public void findA(JTable tb, String str) {
		if (str.substring(1,8).contains("A")) {
			fxCountA(tb, str);
		} else {
			fxCount(tb, str);
		}
	}

	public void fxCount(JTable tb, String str) {
		String[] stArr = str.substring(7, str.length()).replaceAll("[^\\da-zA-Z]", "\\.").split("\\.");
		stArr = getCdnt(stArr);

		int rMax = Math.max((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
		int rMin = Math.min((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
		int cMax = Math.max((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
		int cMin = Math.min((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
		int count = 0;

		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				count += isRealNum((String) tb.getValueAt(i, j)) ? 1 : 0;
			}
		}
		new FxCalc().setResult(tb, count);
	}

	public void fxCountA(JTable tb, String str) {
		String[] stArr = str.substring(8, str.length()).replaceAll("[^\\da-zA-Z]", "\\.").split("\\.");
		stArr = getCdnt(stArr);

		int rMax = Math.max((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
		int rMin = Math.min((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
		int cMax = Math.max((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
		int cMin = Math.min((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
		int count = 0;

		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				count += (String) tb.getValueAt(i, j) != "" ? 1 : 0;
			}
		}
		new FxCalc().setResult(tb, count);

	}

	public boolean isRealNum(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public String[] getCdnt(String[] stArr) {
		for (int i = 0; i < stArr.length; i++) {
			stArr[i] = i % 2 == 0 ? String.valueOf(((Integer.parseInt(stArr[i])) - 1))
					: String.valueOf((stArr[i].charAt(0) - 65));
		}

		return stArr;
	}

}
