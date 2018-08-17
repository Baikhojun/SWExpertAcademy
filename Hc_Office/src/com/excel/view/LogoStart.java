package com.excel.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LogoStart extends JFrame {

	public LogoStart() {
		getStart();
	}

	public synchronized void getStart() {
		//위치 가운데 지정
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		ImageIcon logoImg = new ImageIcon("images/logo_full.PNG");

		add(new JLabel(logoImg));
		setLocation((sSize.width - logoImg.getIconWidth()) / 2, (sSize.height - logoImg.getIconHeight()) / 2);
		setUndecorated(true);
		setVisible(true);
		pack();
		try {
			wait(1200l);
			setVisible(false);
			new MainView();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
