package ru.semikov.sea.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Date: 10.11.11
 * Time: 1:03
 *
 * @author OneHalf
 */
public class SetDimensionAction extends AbstractAction {

    private final int width;
    private final int height;
    private final int shipNum;
    private final GameModel model;

    public SetDimensionAction(int width, int height, int shipNum, GameModel model) {
        this.width = width;
        this.height = height;
        this.shipNum = shipNum;
        this.model = model;

        putValue(NAME, String.format("%d x %d", width, height));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.setDimension(width, height, shipNum);
    }
}
