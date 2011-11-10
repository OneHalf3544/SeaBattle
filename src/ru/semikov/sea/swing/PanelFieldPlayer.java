package ru.semikov.sea.swing;

import java.awt.Color;

import ru.semikov.sea.logic.*;

public class PanelFieldPlayer extends PanelField {

	public PanelFieldPlayer(Field field) {
		super(field);
	}

	@Override
	protected Color getColorByStateElement(Cell.State state) {
        return state.getPlayerColor();
	}
	
}
