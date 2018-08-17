package com.excel.model.dao;

import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.security.spec.EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.net.URLEncoder;

public class SaveSheet {

	private ArrayList<Double> list;

	JFileChooser fc = new JFileChooser();

	public void save(ActionEvent arg0, JTable tb) {

		fc.setDialogTitle("Save a File");
		fc.setCurrentDirectory(new File("C:\\Users/user1/Documents"));
		fc.setFileSelectionMode(fc.FILES_AND_DIRECTORIES);
		fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(".txt", "txt"));

		int result = fc.showSaveDialog(null);

		if (result == fc.APPROVE_OPTION) {

			JOptionPane.showMessageDialog(null, "파일을 저장하였습니다.");
			
			Map<String, Object> map = new HashMap<String, Object>();
			// save 버튼으로 hashmap에 key 와 value 값 저장
			for (int i = 0; i < tb.getRowCount(); i++) // 일정한 수치로 바꿔야한다
			{
				for (int j = 0; j < tb.getColumnCount(); j++) {

					String key = i + "." + j;
				
					Object value = tb.getValueAt(i, j);

					map.put(key, value);
				}
			}

			try (FileOutputStream fo = new FileOutputStream(fc.getSelectedFile()+".txt")) {

				Properties prop = new Properties();

				// hashamp 의 값을 txt 파일에 저장하기
				Map<String, Object> saveMap = map;
				System.out.println(saveMap.entrySet());
				for (Map.Entry<String, Object> entry : saveMap.entrySet()) {
					if (entry.getValue() == null) {
						continue;
					}
					
					prop.put(entry.getKey(), (Object)URLEncoder.encode((String)entry.getValue(), "UTF-8"));
				}
				prop.store(new FileWriter(fc.getSelectedFile()+".txt"), null);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (result != fc.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 저장하지 않았습니다.", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}

}
