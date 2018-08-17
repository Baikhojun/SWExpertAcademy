package com.excel.function;

import javax.swing.JTable;

public class FxMaxMin {
	// 최대값, 최소값 출력 메소드

	public void whatIsMax(JTable tb, String str) {

		String[] stArr = new FxModule().selCoordinate(str);

		int rMax = Math.max((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
		int rMin = Math.min((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
		int cMax = Math.max((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
		int cMin = Math.min((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
		double max = 0.0;

		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				max = max < Double.parseDouble((String) tb.getValueAt(i, j))
						? Double.parseDouble((String) tb.getValueAt(i, j)) : max;
			}
		}
		new FxCalc().setResult(tb, max);
	}
	
	
	

	public void whatIsMin(JTable tb, String str) {

		String[] stArr = new FxModule().selCoordinate(str);

		int rMax = Math.max((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
		int rMin = Math.min((Integer.parseInt(stArr[0])), (Integer.parseInt(stArr[2])));
		int cMax = Math.max((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
		int cMin = Math.min((Integer.parseInt(stArr[1])), (Integer.parseInt(stArr[3])));
		double min = Double.parseDouble((String) tb.getValueAt(rMin, cMin));

		for (int i = rMin; i < rMax + 1; i++) {
			for (int j = cMin; j < cMax + 1; j++) {
				min = min > Double.parseDouble((String) tb.getValueAt(i, j))
						? Double.parseDouble((String) tb.getValueAt(i, j)) : min;
			}
		}
		new FxCalc().setResult(tb, min);
	}

}
