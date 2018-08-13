package com.excel.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;

import com.excel.function.FxController;
import com.excel.function.SellNum;
import com.excel.model.dao.LoadSheet;
import com.excel.model.dao.NewSheet;
import com.excel.model.dao.SaveSheet;

public class MainView extends JFrame {
	// 밑에 두개 사용용도파악
	private ArrayList<Double> list;
	private double sum;
	private SellNum sell;
	private JPanel contentPane;
	

	/**
	 * @wbp.nonvisual location=151,519
	 */

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public MainView() {
		sell = new SellNum();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(300, 300, 1000, 500);

		// ??
		list = new ArrayList<Double>();

		String[] header = sell.sellNum(); // Column 헤더 = 열헤더

		// 테이블 추가할 2차원 배열
		String[][] content = new String[sell.getHeight()][header.length];
		// 맵

		JTable tb = new JTable(content, header); // 테이블

		// 테이블 셀 다중선택 해제
		tb.setCellSelectionEnabled(true);
		tb.setSelectionBackground(Color.cyan);

		// 테이블의 셀 자동 리사이징 해제
		tb.setAutoResizeMode(tb.AUTO_RESIZE_OFF);
		// 셀정렬 가능
		tb.setAutoCreateRowSorter(true);

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
		JScrollPane spn = new JScrollPane(tb, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		spn.setBounds(12, 41, 960, 402);

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

		// 기본 Panel
		JPanel pan = new JPanel();

		// 새로만들기 버튼
		JButton btnNew = new JButton("new");
		btnNew.setBounds(12, 10, 64, 21);
		pan.add(btnNew);

		// 저장 버튼
		JButton btnSave = new JButton("save");
		btnSave.setBounds(88, 10, 64, 21);

		// 불러오기 버튼
		JButton btnLoad = new JButton("load");
		btnLoad.setBounds(164, 10, 64, 21);
		pan.add(btnLoad);

		// 텍스트필드(화면 상단에 위치)
		JTextField jtf = new JTextField(50);
		jtf.setBounds(240, 10, 808, 21);
		pan.add(jtf);

		pan.setLayout(null);

		pan.add(spn);
		pan.add(btnSave);

		getContentPane().add(pan, "Center");

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tb.requestFocus();
		
		
		tb.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e1) {
				if (e1.getKeyCode() == KeyEvent.VK_DELETE) {
					tb.setValueAt(" ", tb.getSelectedRow(), tb.getSelectedColumn());
				}
				new FxController().fxController(e1, tb);
				

			}

			@Override
			public void keyReleased(KeyEvent e2) {
				jtf.setText((String)tb.getValueAt(tb.getSelectedRow(),tb.getSelectedColumn()));
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
	
		jtf.addKeyListener(new KeyAdapter() {
			
			
			@Override
			public void keyReleased(KeyEvent arg1) {
				tb.setValueAt(jtf.getText(),tb.getSelectedRow(),tb.getSelectedColumn());
				if(arg1.getKeyCode()==KeyEvent.VK_ENTER) {
					new FxController().fxController(arg1,tb);
				}
				
			}
			
		});

		
		btnNew.addActionListener(new ActionListener() {

			// *******************
			// 이거수정하기 new버튼 동적할당 및 처음실행시 패널보이게하기

			@Override
			public void actionPerformed(ActionEvent arg3) {

				new NewSheet().newSheet(arg3);

			}

		});

		btnSave.addActionListener(new ActionListener() {

			// save 버튼 눌렀을 때 저장할 파일명 입력받는 코드
			@Override
			public void actionPerformed(ActionEvent arg4) {
				new SaveSheet().save(arg4, tb);
			}
		});

		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e3) {
				new LoadSheet().load(e3, tb);
			}

		});

	}

	// rowheader 함수
	public String[] rowHeaderList() {

		String str[] = new String[sell.getHeight()];

		for (int i = 0; i < sell.getHeight(); i++) {
			str[i] = (i + 1) + "";
		}

		return str;
	}
	

}
