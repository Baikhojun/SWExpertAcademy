package com.excel.function;

public class FxModule {

	// 혼합 좌표를 숫자 좌표로 바꾸기

	public String[] selCoordinate(String str) {
		String[] stArr = str.substring(5, str.length()).replaceAll("[^\\da-zA-Z]", "\\.").split("\\.");

		for (int i = 0; i < stArr.length; i++) {
			stArr[i] = i % 2 == 0 ? String.valueOf(((Integer.parseInt(stArr[i])) - 1))
					: String.valueOf((stArr[i].charAt(0) - 65));
		}
		return stArr;
	}
	
	
	// if문용
	public String[] ifCoordinate(String str) {
		String[] stArr = str.replaceAll("[^\\da-zA-Z]", "\\.").split("\\.");

		for (int i = 0; i < stArr.length; i++) {
			stArr[i] = i % 2 == 0 ? String.valueOf(((Integer.parseInt(stArr[i])) - 1))
					: String.valueOf((stArr[i].charAt(0) - 65));
		}
		return stArr;
	}

}
