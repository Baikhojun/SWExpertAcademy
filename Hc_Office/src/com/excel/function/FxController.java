package com.excel.function;

import java.awt.event.KeyEvent;

import javax.swing.JTable;

import com.excel.model.vo.MouseAction;

public class FxController {

	public void fxController(KeyEvent e, JTable tb) {

		Calculator calu = new Calculator();
		try {
			String str = (String) tb.getValueAt(tb.getSelectedRow(), tb.getSelectedColumn());

			String function = (str.length() > 3) ? str.substring(1, 4) : str.substring(1, 2);

			int selRow = tb.getSelectedRow();
			int selCol = tb.getSelectedColumn();

			if (str.charAt(0) == '=') {

				new MouseAction(tb, selRow, selCol, str).addPoint();

				if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("TOD")) {

					new FxDate().today(tb);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("DAT")) {
					new FxDate().date(tb, str);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("IF(")) {
					new FxIf().loop(tb, str);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER
						&& ((function.charAt(0) >= '1' && function.charAt(0) <= '9') || function.equals("-")
								|| function.equals("("))) {
					str = str.substring(1);
					calu.calculator(str, tb);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("UPP")) {
					new FxToUpDown().toUpperCase(tb, str);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("LOW")) {
					new FxToUpDown().toLowerCase(tb, str);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("MAX")) {
					new FxMaxMin().whatIsMax(tb, str);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("MIN")) {
					new FxMaxMin().whatIsMin(tb, str);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && function.equals("COU")) {
					new FxCount().findA(tb, str);

				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					new FxCalc().calculate(function, str, tb);

				}

			}

		} catch (StringIndexOutOfBoundsException e1) {

		} catch (NullPointerException e1) {

		} catch (Exception e1) {

		}

	}

}
