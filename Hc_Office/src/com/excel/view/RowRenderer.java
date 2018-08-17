package com.excel.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

//테이블의 RowTable 설정 예쁘게 만드는 거
public class RowRenderer extends JLabel implements ListCellRenderer {
	public RowRenderer(JTable table) {
		
		JTableHeader header = table.getTableHeader();
		
		// 불투명하게 만들기
		setOpaque(true);
		
		// 기존 Table 처럼 칸막이 만들어서 설정하기
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		
		// 가로정렬 한다 
		setHorizontalAlignment(CENTER);
		
		// 글자색 & 배경색 & Font 원래 헤더랑 똑같이한다.
		setForeground(header.getForeground());
		setBackground(header.getBackground());
		setFont(header.getFont());
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		setText((value == null) ? "" : value.toString());
		return this;
	}
}
