package com.excel.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.excel.function.FxController;
import com.excel.model.dao.InfoFunction;
import com.excel.model.dao.LoadSheet;
import com.excel.model.dao.NewSheet;
import com.excel.model.dao.SaveSheet;
import com.excel.model.vo.JTableCreate;

public class MainView extends JFrame {

	// static 으로 다른 Class에서 직접 접근
	public static JTableCreate jtc;
	public static JPanel pan;
	public static JTextField jtf;

	/**
	 * @wbp.nonvisual location=151,519
	 */

	/**
	 * Launch the application.
	 */

	public static void setJtc(JTableCreate jtc) {
		MainView.jtc = jtc;
	}

	/**
	 * Create the frame.
	 */
	public MainView() {

		// 새로만들기 버튼
		JButton btnNew = new JButton("new");
		// 저장 버튼
		JButton btnSave = new JButton("save");
		// 불러오기 버튼
		JButton btnLoad = new JButton("load");
		// 도움말 버튼
		JButton btnHelp = new JButton("help");

		jtc = new JTableCreate(40, 40);
		// 기본 Panel
		pan = new JPanel();
		// 텍스트필드(화면 상단에 위치)
		jtf = new JTextField(50);

		setIconImage(new ImageIcon("images/logo_big.PNG").getImage());
		setTitle("HC OFFICE SPREADSHEET");

		setBounds(150, 100, 1600, 800);
		btnNew.setBounds(12, 10, 64, 21);
		btnSave.setBounds(88, 10, 64, 21);
		btnLoad.setBounds(164, 10, 64, 21);
		btnHelp.setBounds(1520, 10, 64, 21);
		jtf.setBounds(240, 10, 1268, 21);

		pan.setLayout(null);
		pan.add(btnNew);
		pan.add(btnLoad);
		pan.add(btnSave);
		pan.add(btnHelp);
		pan.add(jtf);
		pan.add(jtc.getSpn());

		// 배경색 지정
		pan.setBackground(Color.WHITE);

		// 버튼 색 지정
		btnSave.setBackground(Color.WHITE);
		btnNew.setBackground(Color.WHITE);
		btnLoad.setBackground(Color.WHITE);
		btnHelp.setBackground(Color.WHITE);

		jtc.getSpn().setBackground(new Color(250, 250, 250));

		jtc.getTb().addKeyListener(new JTableCreate().key(jtc.getTb(), jtf, pan));

		setResizable(false);
		getContentPane().add(pan, "Center");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg1) {
				jtc.getTb().setValueAt(jtf.getText(), jtc.getTb().getSelectedRow(), jtc.getTb().getSelectedColumn());
				if (arg1.getKeyCode() == KeyEvent.VK_ENTER) {
					new FxController().fxController(arg1, jtc.getTb());
					jtc.getTb().requestFocus();
					jtf.setText((String) jtc.getTb().getValueAt(jtc.getTb().getSelectedRow(),
							jtc.getTb().getSelectedColumn()));
				}
			}
		});
		btnNew.addActionListener(new ActionListener() {
			// new버튼
			@Override
			public void actionPerformed(ActionEvent arg3) {
				new NewSheet();

			}
		});

		btnSave.addActionListener(new ActionListener() {
			// save버튼
			@Override
			public void actionPerformed(ActionEvent arg4) {
				new SaveSheet().save(arg4, jtc.getTb());
			}
		});

		btnLoad.addActionListener(new ActionListener() {
			// Load버튼
			@Override
			public void actionPerformed(ActionEvent e3) {
				new LoadSheet().load(e3, jtc.getTb());
			}
		});

		btnHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new InfoFunction();

			}
		});

	}

}
