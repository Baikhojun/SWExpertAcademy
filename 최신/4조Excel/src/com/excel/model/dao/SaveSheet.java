package com.excel.model.dao;

import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class SaveSheet {
	private ArrayList<Double> list;
	public void save(ActionEvent arg0,JTable tb) {
		Map<String, Object> map = new HashMap<String, Object>();
		// save 버튼으로 hashmap에 key 와 value 값 저장
		for (int i = 0; i < tb.getRowCount(); i++) // 일정한 수치로 바꿔야한다
		{
			for (int j = 0; j < tb.getColumnCount(); j++) {

				String key = i + "." + j;
				// test print
				// System.out.println(i+","+j+"테의블 값 = "+tb.getValueAt(i, j));
				Object value = tb.getValueAt(i, j);

				map.put(key, value);
			}
		}
		String fileName = JOptionPane.showInputDialog("저장할 파일명을 입력하세요.");

		try (FileOutputStream fo = new FileOutputStream(fileName + ".txt")) {

			Properties prop = new Properties();
			/*
			 * int col = tb.getSelectedColumn(); int row = tb.getSelectedRow();
			 * 
			 * String key = row + "," + col; // Object value = tb.getValueAt(row, col);
			 * 
			 * if (tb.getValueAt(row, col) != null) {
			 */
			   tb.getCellEditor().stopCellEditing();
			// hashamp 의 값을 txt 파일에 저장하기
			Map<String, Object> saveMap = map;
			System.out.println(saveMap.entrySet());
			for (Map.Entry<String, Object> entry : saveMap.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				prop.put(entry.getKey(), entry.getValue());
			}
			prop.store(new FileWriter(fileName + ".txt"), null);
			// System.out.println(prop.getProperty(key));
			// System.out.println(value);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
