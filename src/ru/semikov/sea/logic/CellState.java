package ru.semikov.sea.logic;

import java.awt.*;

/**
* Date: 11.11.11
* Time: 2:07
*
* @author OneHalf
*/
public enum CellState {
    WATER(new Color(225, 225, 255), new Color(225, 225, 255)),
    BORDER(new Color(215, 215, 255), new Color(225, 225, 255)),
    WELL(Color.green, new Color(225, 225, 255)),
    INJURED(Color.red, Color.red),
    KILLED(Color.gray, Color.gray),
    MISSED(Color.black, Color.black);

    private final Color playerColor;
    private final Color opponentColor;

    CellState(Color playerColor, Color opponentColor) {
        this.playerColor = playerColor;
        this.opponentColor = opponentColor;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public Color getOpponentColor() {
        return opponentColor;
    }
}
