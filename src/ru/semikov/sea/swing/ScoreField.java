package ru.semikov.sea.swing;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import ru.semikov.sea.logic.*;

public class ScoreField extends JPanel implements FieldChangeListener {

	private final GameModel model;

	public ScoreField(GameModel model) {
		this.model = model;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int numShip = model.getPlayerFieldOpponent().getMaxShipSize();

		int[] m = new int[numShip];
		for(int i = 0; i < 0; i++) {
			m[i] = 0;
		}
		
		for(Ship ship : model.getPlayerFieldOpponent().getShips()) {
			if (ship.getState() != Ship.State.KILLED) {
				m[ship.getSize() - 1] ++;
			}
		}
		
		for(int i = 0; i < numShip; i++) {
			for(int j = 0; j <= i; j++) {
				g.setColor(Color.gray);
				g.fillRect(j * 10 + 8, i * 10 + 5, 8, 8);
			}
			g.setColor(Color.black);
			g.drawString(String.valueOf(m[i]), 78, i * 10 + 12);
		}

		int so = model.getPlayerFieldOpponent().getNumLiveShips();
		int sp = model.getPlayerFieldPlayer().getNumLiveShips();
		
		g.drawString("My alive: ".concat(String.valueOf(sp)), 15, 100);
		g.drawString("Op alive: ".concat(String.valueOf(so)), 15, 120);

		if (sp == 0) {
            g.drawString("YOU LOSER!", 20, 140);
        }
		if (so == 0) {
            g.drawString("YOU WON!", 20, 140);
        }
	}

	@Override
	public void fieldChanged() {
		this.repaint();
	}

}
