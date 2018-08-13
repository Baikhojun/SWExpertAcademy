package com.excel.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

//테이블의 RowTable 설정 예쁘게 만드는 거
class RowRenderer extends JLabel implements ListCellRenderer {
	public RowRenderer(JTable table) {
		JTableHeader header = table.getTableHeader();
		setOpaque(true);
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		setHorizontalAlignment(CENTER);
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
