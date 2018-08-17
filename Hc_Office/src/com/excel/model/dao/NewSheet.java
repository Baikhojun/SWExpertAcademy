package com.excel.model.dao;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.excel.model.vo.JTableCreate;
import com.excel.view.MainView;

public class NewSheet extends JFrame {


	public NewSheet() {
		
		// frame 이름
		super("New");

		// 프레임 크기 
		setBounds(90, 400, 260, 100);
		
		setVisible(true);
		
		// 종료버튼 눌렀을때 New frame 창만 종료
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// frame 사이즈 고정
		setResizable(false);

		JPanel panNew = new JPanel();
		JLabel lbl, lbl2, col, row;
		JTextField rowFd;
		JTextField colFd;
		JButton btnOk;

		
		// 경계선을 만드는 컴포넌트
		EtchedBorder eborder = new EtchedBorder();

		
		lbl = new JLabel(" 시트에 적용하실 행과 열을 입력해주세요.");
		panNew.add(lbl);

		lbl.setBorder(eborder);

		//첫한칸
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
		
		// 버튼색 지정
		panNew.setBackground(Color.WHITE);
		btnOk.setBackground(Color.WHITE);
		
		getContentPane().add(panNew, "Center");
		
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rowNew = Integer.parseInt(rowFd.getText());
					int colNew = Integer.parseInt(colFd.getText());

					// 입력된 수가 정수일때만 작동
					if (rowNew > 0 && colNew > 0) {
						
						// Mainview의 기본 테이블 삭제
						
						MainView.pan.remove(MainView.jtc.getSpn());
						
						// Mainview의 기본 테이블 재생성
						
						MainView.setJtc(new JTableCreate(rowNew, colNew));
						
						// Mainview의 기본 패널에 생성된 테이블을 추가한다
						
						MainView.pan.add(MainView.jtc.getSpn());
					
						MainView.pan.revalidate();
						MainView.pan.repaint();
						
						MainView.jtc.getTb().addKeyListener(
								new JTableCreate().key(MainView.jtc.getTb(), MainView.jtf, MainView.pan));
						
						setVisible(true);
						// 스크롤패널 색 지정
						MainView.jtc.getSpn().setBackground(new Color(250, 250, 250));
						dispose();
					}
				} catch (NumberFormatException e1) {
					
					JOptionPane.showMessageDialog(null, "숫자를 입력하세요", "Warning", JOptionPane.WARNING_MESSAGE);
					
				} 

			}
		});

		// New 시트에 에서 esc키 입력시 new 창이 종료
		// 아무 시트에서나 Enter누르면 ok 버튼이랑 연결
		KeyListener click = new KeyAdapter() {
	         
			@Override
	         public void keyPressed(KeyEvent arg0) {
	        
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
	               btnOk.doClick();
	            }
	            if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE ){
	            	dispose();
	            }
	         }
	      };
	      rowFd.addKeyListener(click);
	      colFd.addKeyListener(click);

	}
}
