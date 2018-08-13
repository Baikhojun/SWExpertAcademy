package com.excel.function;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class FxController {
	
	
	public void fxController(KeyEvent e, JTable tb) {
		
		
		Fx fx = new Fx();
		Calculator calu = new Calculator();
		try {
			String str = (String) tb.getValueAt(tb.getSelectedRow(), tb.getSelectedColumn());

		
			String function = (str.length()>3)?str.substring(1, 4):str.substring(1, 2);
			

			int selRow = tb.getSelectedRow();
			int selCol = tb.getSelectedColumn();
			
			
			
			
				

			if (str.charAt(0) == '=') {
				
				new MouseAction(tb, selRow, selCol, str).addPoint();
				
				
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("TOD")) {
				
					fx.today(tb);
					
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("DAT")) {
					fx.date(tb, str);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("IF(")) {
					fx.loop(tb, str);

				}else if(e.getKeyCode() == KeyEvent.VK_ENTER&&((function.charAt(0)>='1'&&function.charAt(0)<='9')|| function.equals("-")||function.equals("("))) {
					str=str.substring(1);
					calu.calculator(str, tb);

				}else if(e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("UPP")) {
					fx.toUpperCase(tb, str);

				}else if(e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("LOW")) {
					fx.toLowerCase(tb, str);

				}else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					fx.calculate(function, str, tb);

				}
				



			}

		} catch (StringIndexOutOfBoundsException e1) {

		} catch (NullPointerException e1) {

		} catch (Exception e1) {

		}

	}
	
	

}
