package ru.semikov.sea.swing;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ru.semikov.sea.logic.*;

public class PanelFieldOpponent extends PanelField {

    public PanelFieldOpponent(Field field, final GameModel model) {
		super(field);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                int x = arg0.getX() / (getWidth() / getField().getWidth());
                int y = arg0.getY() / (getHeight() / getField().getHeight());
                
                model.doShotByUser(x, y);
            }
        });
    }

    protected Color getColorByStateElement(CellState cellState) {
        return cellState.getOpponentColor();
    }

}
