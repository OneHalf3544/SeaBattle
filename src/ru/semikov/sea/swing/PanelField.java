package ru.semikov.sea.swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import ru.semikov.sea.logic.*;

abstract public class PanelField extends JPanel implements ISubscriber {
	private Field field;
	
	public Field getField() {
		return field;
	}

	public PanelField(Field field) {
		this.field = field;
	}

	private int getCellWidth() {
		return getWidth() / getField().getWidth();
	}

	private int getCellHeight() {
		return getHeight() / getField().getHeight();
	}
	
	abstract protected Color getColorByStateElement(CellState cellState);
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw a grid
		for(int i = 0; i < getField().getWidth() + 1; i++) {
			g.drawLine(i * getCellWidth(), 0, i * getCellWidth(), getCellHeight() * getField().getHeight());
		}
		
		for(int i = 0; i < getField().getHeight() + 1; i++) {
			g.drawLine(0, i * getCellHeight(), getCellWidth() * getField().getWidth(), i * getCellHeight());
		}

		
		// Draw the elements
		for(int j = 0; j < getField().getHeight(); j++) {
			for(int i = 0; i < getField().getWidth(); i++) {
				CellState cellState = field.getCell(i, j).getState();
				g.setColor(getColorByStateElement(cellState));
				if (cellState == CellState.MISSED) {
					g.fillRect(i*getCellWidth() + (getCellWidth() / 2) - 1, j*getCellHeight() + (getCellHeight() / 2) - 1, 4, 4);
				} else {
					g.fillRect(i*getCellWidth()+1, j*getCellHeight()+1, getCellWidth() - 1, getCellHeight() - 1);
				}
				
			}
		}
	}

	@Override
	public void update() {
		this.repaint();
	}
	
}
