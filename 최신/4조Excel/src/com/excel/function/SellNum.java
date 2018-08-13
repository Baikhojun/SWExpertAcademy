package com.excel.function;

import java.util.Scanner;

public class SellNum {
	private int length;
	private int height;
	// 표의 사이즈를 정하기 위한 키보드 입력부
		public String[] sellNum() {

			Scanner sc = new Scanner(System.in);

			System.out.println("길이 : ");

			// length=sc.nextInt();
			length = 30;

			System.out.println("높이 : ");

			// this.height = sc.nextInt();
			height = 20;

			String[] str = new String[length];

			for (int i = 0; i < length; i++) {
				// 맨 앞의 셀의 이름을 정하는 방법

				// **우선 A~ AZ 까지 구현
				int ascNum = i + 65;
				if (ascNum > 90) {
					ascNum -= 26;
					str[i] = 'A' + String.valueOf((char) (ascNum));
				} else {
					str[i] = String.valueOf((char) (ascNum));
				}

			}

			return str;

		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		
}
