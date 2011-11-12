package ru.semikov.sea.swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import ru.semikov.sea.logic.*;

public abstract class PanelField extends JPanel implements FieldChangeListener {

	private final Field field;
	
	public Field getField() {
		return field;
	}

	protected PanelField(Field field) {
		this.field = field;
	}

    protected abstract Color getColorByStateElement(CellState cellState);

    private int getCellWidth() {
        return getWidth() / field.getWidth();
}

    private int getCellHeight() {
        return getHeight() / field.getHeight();

}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

        drawGrid(g);
        drawElements(g);
    }

    private void drawGrid(Graphics g) {
        // Draw a grid
        int cellWidth = getCellWidth();
        int cellHeight = getCellHeight();

        for(int i = 0; i < field.getWidth() + 1; i++) {
			g.drawLine(i * cellWidth, 0, i * cellWidth, cellHeight * field.getHeight());
		}

        for(int i = 0; i < field.getHeight() + 1; i++) {
            g.drawLine(0, i * cellHeight, cellWidth * field.getWidth(), i * cellHeight);
        }
    }

    private void drawElements(Graphics g) {
        int cellWidth = getCellWidth();
        int cellHeight = getCellHeight();

        // Draw the elements
        for(int j = 0; j < field.getHeight(); j++) {
            for(int i = 0; i < field.getWidth(); i++) {
                CellState cellState = field.getCell(i, j).getState();
                g.setColor(getColorByStateElement(cellState));

                if (cellState == CellState.MISSED) {
                    g.fillRect(i* cellWidth + cellWidth / 2 - 1, j* cellHeight + cellHeight / 2 - 1, 4, 4);
                } else {
                    g.fillRect(i* cellWidth +1, j* cellHeight +1, cellWidth - 1, cellHeight - 1);
                }
            }
        }
    }

    @Override
	public void fieldChanged() {
		this.repaint();
	}
	
}
