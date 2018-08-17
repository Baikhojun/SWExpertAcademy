
package com.excel.model.vo;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

import com.excel.function.FxController;
import com.excel.view.RowRenderer;

public class JTableCreate {

	private JTable tb;
	private JScrollPane spn;
	private static int length;
	private static int height;

	public JTable getTb() {
		return tb;
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

	public void setTb(JTable tb) {
		this.tb = tb;
	}

	public JScrollPane getSpn() {
		return spn;
	}

	public void setSpn(JScrollPane spn) {
		this.spn = spn;
	}

	public JTableCreate() {
		this(height, length);
	}

	public JTableCreate(int height, int length) {
		this.length = length;
		this.height = height;

		// Column 헤더 = 열헤더
		String[] header = new String[length];
		
		// 맨 앞의 셀의 이름을 정하는 방법
		for (int i = 0; i < length; i++) {

			// **우선 A~ ZZ 까지 구현 //65 ~90
			int ascNum = i + 65;
			if (ascNum > 90) {

				int j = 0;
				while (ascNum % (91 + (26 * j)) >= 26) {
					j++;
				}
				ascNum -= (26 * j) + 26;
				char ch = (char) (j + 65);

				header[i] = ch + String.valueOf((char) (ascNum));

			} else {
				header[i] = String.valueOf((char) (ascNum));
			}
		}

		// 테이블 추가할 2차원 배열
		String[][] content = new String[height][header.length];
		
		tb = new JTable(content, header); // 테이블

		// 테이블 셀 다중선택 해제
		tb.setCellSelectionEnabled(true);
		tb.setSelectionBackground(new Color(235, 245, 230));

		// 테이블의 셀 자동 리사이징 해제
		tb.setAutoResizeMode(tb.AUTO_RESIZE_OFF);
		// 셀정렬 가능
		// tb.setAutoCreateRowSorter(true);

		// 반복문으로 셀의 크기 고정
		for (String h : header) {
			tb.getColumn(h).setPreferredWidth(100);
			tb.setRowHeight(20);
		}
		// 상단 테이블 헤더 고정
		tb.getTableHeader().setReorderingAllowed(false);

		// 상단 테이블 헤더 크기 수정가능
		tb.getTableHeader().setResizingAllowed(false);

		// 스크롤패널
		spn = new JScrollPane(tb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// spn.setBounds(12, 41, 960, 402);
		spn.setBounds(12, 41, 1573, 725);
		// 스크롤패널 배경색
		spn.getViewport().setBackground(Color.WHITE);

		// rowHeader = 행 의 헤더만들기
		ListModel lm = new AbstractListModel() {

			// height 만큼 숫자 Stirng에 넣기
			String[] headers = rowHeaderList();

			@Override
			public Object getElementAt(int index) {
				return headers[index];
			}

			@Override
			public int getSize() {
				return headers.length;
			}
		};

		JList rowHeader = new JList(lm);
		// rowHeader 싸이즈정리
		rowHeader.setFixedCellWidth(50);
		rowHeader.setFixedCellHeight(tb.getRowHeight());
		// rowHeader 색상입히기
		// scroll에 행 헤더 추가하기
		rowHeader.setCellRenderer(new RowRenderer(tb));

		spn.setRowHeaderView(rowHeader);
		}

	public String[] rowHeaderList() {

		String str[] = new String[this.height];

		for (int i = 0; i < height; i++) {
			str[i] = (i + 1) + "";
		}
		return str;
	}

	public KeyListener key(JTable tb, JTextField jtf, JPanel pan) {

		KeyListener key = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e1) {
				if (e1.getKeyCode() == KeyEvent.VK_DELETE) {
					tb.setValueAt("", tb.getSelectedRow(), tb.getSelectedColumn());
				}
				new FxController().fxController(e1, tb);
			}

			@Override
			public void keyReleased(KeyEvent e2) {
				jtf.setText((String) tb.getValueAt(tb.getSelectedRow(), tb.getSelectedColumn()));
			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}
		};
		return key;
	}
}
