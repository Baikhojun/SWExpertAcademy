package com.excel.model.dao;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class LoadSheet {
	public void load(ActionEvent e,JTable tb) {
		// JTable tb = new JTable(content, header);
		// tb.removeAll();
		// DefaultTableModel model = (DefaultTableModel)tb.getModel();
		// model.setNumRows(0);
		// tb.repaint();
		String fileName = JOptionPane.showInputDialog("불러올 파일명을 입력하세요.");
		Properties prop = new Properties();

		// int col = tb.getSelectedColumn();
		// int row = tb.getSelectedRow();

		// String key = row + "," + col;
		// Object value = tb.getValueAt(row, col);

		try (FileInputStream fi = new FileInputStream(fileName + ".txt")) {
			System.out.println("load");
			// Map<String, Object> loadMap = map;
			
			//터치하고있는상태로 값 저장하기
			   tb.getCellEditor().stopCellEditing();
			prop.load(new FileInputStream(fileName + ".txt"));
			System.out.println("0.0의 값 =" + prop.get("0.0"));
			System.out.println("처음뽑아" + prop.stringPropertyNames());
			
			for (int i = 0; i < tb.getRowCount(); i++) // 수정해야한다
			{
				for (int j = 0; j < tb.getColumnCount(); j++) {
					tb.setValueAt(" ", i, j);
				}
			}
			for (String keys : prop.stringPropertyNames()) {

				int selectedRow = Integer.parseInt(keys.split("\\.")[0]);
				int selectedCol = Integer.parseInt(keys.split("\\.")[1]);
				// System.out.println("뽑자"+prop.get(keys));
				// System.out.println(selectedRow + " " +selectedCol);
				tb.setValueAt(prop.get(keys) + "", selectedRow, selectedCol);

				// loadMap.put(key, prop.get(key).toString());

			}

		} catch (Exception e1) {

			System.out.println("불러올 파일이 없습니다.");

		}

	
	}

}
