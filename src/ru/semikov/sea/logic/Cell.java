package ru.semikov.sea.logic;

import java.awt.*;

public class Cell {

    public enum State {
        WATER(new Color(225, 225, 255), new Color(225, 225, 255)),
        BORDER(new Color(215, 215, 255), new Color(225, 225, 255)),
        WELL(Color.green, new Color(225, 225, 255)),
        INJURED(Color.red, Color.red),
        KILLED(Color.gray, Color.gray),
        MISSED(Color.black, Color.black);

        private final Color playerColor;
        private final Color opponentColor;

        private State(Color playerColor, Color opponentColor) {
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

	/**
	 * @uml.property  name="x"
	 */
	public int x;
	/**
	 * @uml.property  name="y"
	 */
	public int y;
	/**
	 * @uml.property  name="state"
	 */
	private State state;
	/**
	 * @uml.property  name="ship"
	 * @uml.associationEnd  inverse="listCells:ru.semikov.sea.logic.Ship"
	 */
	private Ship ship;
	/**
	 * @uml.property  name="mark"
	 */
	private boolean mark;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.state = State.WATER;
		this.mark = false;
	}
	
	/**
	 * @param state
	 * @uml.property  name="state"
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * @return
	 * @uml.property  name="state"
	 */
	public State getState() {
		return state;
	}

	/**
	 * @return
	 * @uml.property  name="mark"
	 */
	public boolean isMark() {
		return mark;
	}
	
	/**
	 * @param mark
	 * @uml.property  name="mark"
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}

	/**
	 * @return
	 * @uml.property  name="ship"
	 */
	public Ship getShip() {
		return ship;
	}

	/**
	 * @param ship
	 * @uml.property  name="ship"
	 */
	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public int doShot() {
		setMark(true);
		if (state == State.WELL) {
			setState(State.INJURED);
			return getShip().doShot();
		} else {
			if ( (state == State.BORDER) || (state == State.WATER)) {
				setState(State.MISSED);
			}
		}
		return Field.SHUT_MISSED;
	}
	
}
