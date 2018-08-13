package com.excel.model.dao;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.excel.function.SellNum;
import com.excel.view.MainView;

public class NewSheet {
	public void newSheet(ActionEvent arg0) {
		// 2개이상의 선택지 만들려면 무조건 새로운 프레임 만들어서 넣기
		//
		// JOptionPane.showInputDialog(parentComponent, message, title,
		// messageType)
		// String userRowNum = JOptionPane.showInputDialog("배열의 행값");
		// String userColNum = JOptionPane.showInputDialog("배열의 열값");
		MainView mv = new MainView();
		SellNum sell = new SellNum();
		
		final Frame newFrm = new Frame("새로 만들기");
		JPanel panNew = new JPanel();

		JLabel lbl, lbl2, col, row;
		JTextField rowFd;
		JTextField colFd;
		JButton btnOk;

		// setLayout(new FlowLayout());

		lbl = new JLabel("엑셀에 적용하실 행과 열을 입력해주세요.");
		panNew.add(lbl);

		// 경계선을 만드는 컴포넌트
		EtchedBorder eborder = new EtchedBorder();
		lbl.setBorder(eborder);

		lbl2 = new JLabel(" ");
		panNew.add(lbl2);

		row = new JLabel("행");
		rowFd = new JTextField(5);
		panNew.add(row);
		panNew.add(rowFd);

		col = new JLabel("열");
		colFd = new JTextField(5);
		panNew.add(col);
		panNew.add(colFd);

		btnOk = new JButton("확인");
		panNew.add(btnOk);

		newFrm.add(panNew);

		newFrm.setVisible(true);

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String rowTxt = rowFd.getText();
				String colTxt = colFd.getText();

				sell.setHeight(Integer.parseInt(rowTxt));
				sell.setLength(Integer.parseInt(colTxt));

				newFrm.setVisible(false);
				mv.dispose();
				if (sell.getHeight() > 0 && sell.getLength() > 0) {
					mv.dispose();
					MainView frame = new MainView();
					frame.setVisible(true);

				}

			}
		});

		newFrm.setBounds(200, 400, 280, 100);

		mv.setVisible(true);
		mv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
