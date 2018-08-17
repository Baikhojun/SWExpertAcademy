package com.excel.model.vo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JTable;

public class MouseAction extends JFrame {

	private JTable tb;
	private int selRow;
	private int selCol;
	private String str;

	public MouseAction(JTable tb, int selRow, int selCol, String str) {
		this.tb = tb;
		this.selRow = selRow;
		this.selCol = selCol;
		this.str = str;
	}

	public void addPoint() {
		MouseListener ml = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 && e.getButton() == 1
						&& (tb.getValueAt(tb.getSelectedRow(), tb.getSelectedColumn()) != null
								|| (String)tb.getValueAt(tb.getSelectedRow(), tb.getSelectedColumn()) != "")) {
					tb.setValueAt(
							(String) (str + (tb.getSelectedRow() + 1) + "." + (char) (tb.getSelectedColumn() + 65)),
							selRow, selCol);
					str = (String) tb.getValueAt(selRow, selCol) + ",";
				}

			}

		};

		tb.addMouseListener(ml);
		tb.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					tb.removeMouseListener(ml);

				}

			}

		});
	}

}
