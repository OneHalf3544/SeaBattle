package ru.semikov.sea.swing;


import java.awt.Color;

import ru.semikov.sea.logic.*;

public class PanelFieldOpponent extends PanelField {

	public PanelFieldOpponent(Field field) {
		super(field);
	}

	protected Color getColorByStateElement(CellState cellState) {
		return cellState.getOpponentColor();
	}

}
