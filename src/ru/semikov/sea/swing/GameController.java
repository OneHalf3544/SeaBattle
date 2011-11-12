package ru.semikov.sea.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * ���������� �������� ����
 */
public class GameController implements ActionListener {

	private final GameModel model;
	private final GameFrame view;

	public GameController(GameFrame view, GameModel model) {
		this.view = view;
		this.model = model;
	}

	/**
	 * ���������� ������ ����
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "New game") { 	
				model.newGame();
		}

		if (cmd == "About") {
		}

		if (cmd == "Exit") {	
			System.exit(0);
		}
	}

}
