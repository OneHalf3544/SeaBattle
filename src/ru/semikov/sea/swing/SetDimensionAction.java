package ru.semikov.sea.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Date: 10.11.11
 * Time: 1:03
 *
 * @author OneHalf
 */
public class SetDimensionAction extends AbstractAction {

    private final Dimension dimension;

    private final int shipNum;
    private final GameModel model;

    public SetDimensionAction(int width, int height, int shipNum, GameModel model) {
        this.dimension = new Dimension(width, height);

        this.shipNum = shipNum;
        this.model = model;

        putValue(NAME, String.format("%d x %d", width, height));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setFieldParams(dimension, shipNum);
    }
}
