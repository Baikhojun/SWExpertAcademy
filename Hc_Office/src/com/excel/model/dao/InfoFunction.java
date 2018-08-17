package com.excel.model.dao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class InfoFunction extends JFrame{
	// 도움말 띄우는 클래스
	
	public InfoFunction(){
		// frame 이름
		super("Help");

		// 프레임 크기 
		setBounds(1250, 100, 500, 825);
		
		JTextArea jta = new JTextArea();
		JPanel panHelp1 = new JPanel();
		JPanel panHelp2 = new JPanel();
		JButton btnOk;
		
		setVisible(true);	
		
		// 종료버튼 눌렀을때 New frame 창만 종료
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// frame 사이즈 고정
		setResizable(false);

			
		JLabel lbl = new JLabel("Hc Office 도움말");
		
		jta.setText("함수 좌표 형식 : (행 주소,열 주소)\n\n"
				+ "함수 사용 형식 : =함수(행 주소,열주소)\n"
				+ "범위를 지정 가능한 함수는 =함수(행 주소:열 주소)\n"
				+ "모든 실수의 표현범위는 소숫점 아래 3자리까지 표현 가능\n\n"
				+ "함수 종류\n\n"
				+ "=SUM(A,B) : 두 셀 혹은 범위 내 값의 합을 구한다\n"
				+ "=SUM(A:B)\n\n"
				+ "=SUB(A,B) : 두 셀 혹은 범위 내 값의 차를 구한다\n"
				+ "=SUB(A:B)\n\n"
				+ "=MUT(A,B) : 두 셀 혹은 범위 내 값의 곱을 구한다\n"
				+ "=MUT(A:B)\n\n"
				+ "=DIV(A,B) : 두 셀 혹은 범위 내 값을 나눈 몫을 구한다\n"
				+ "=DIV(A:B)\n\n"
				+ "=AVE(A:B) : 선택한 범위 내 값의 평균을 구한다\n\n"
				+ "=COUNT(A:B) : 선택한 범위 내의 숫자만이 포함된 셀의 갯수를 구한다\n\n"
				+ "=COUNTA(A:B) : 선택한 범위 내의 값이 들어있는 모든 셀의 갯수를 구한다    \n\n"
				+ "=TODAY() : 오늘 날짜를 구한다\n\n"
				+ "=DATE(year,month,date) : 입력한 날짜를 출력한다\n\n"
				+ "=IF(A>B) : 두 셀을 비교해 참과 거짓을 출력한다\n"
				+ "=IF(A<B)\n\n"
				+ "=MAX(A:B) : 범위 내에서 가장 높은 값을 구한다\n\n"
				+ "=MIN(A:B) : 범위 내에서 가장 낮은 값을 구한다\n\n"
				+ "=UPPER(a) : 소문자 문자열 a를 대문자로 바꾼다\n\n"
				+ "=LOWER(A) : 대문자 문자열 A를 소문자로 바꾼다");
		
		
		jta.setEditable(false);
		
		// 글씨 설정
		jta.setFont(jta.getFont().deriveFont(Font.BOLD,13f));

		// 경계선을 만드는 컴포넌트
		EtchedBorder eborder = new EtchedBorder();
		lbl.setBorder(eborder);
		jta.setBorder(eborder);
		
		
		btnOk = new JButton("확인");
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		
		panHelp1.add(lbl);
		panHelp2.add(jta);
		panHelp2.add(btnOk,"Center");
		
				
		// 색 지정
		panHelp1.setBackground(Color.white);
		panHelp2.setBackground(Color.WHITE);
		btnOk.setBackground(Color.WHITE);
				
		getContentPane().add(panHelp1, "North");
		getContentPane().add(panHelp2, "Center");
		
	}
	

}
