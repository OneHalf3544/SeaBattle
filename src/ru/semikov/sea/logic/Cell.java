package ru.semikov.sea.logic;

import java.awt.*;

public class Cell {

    private final Point point;

	private CellState state;

	private Ship ship;

	private boolean alreadyUsed;

	public Cell(int x, int y) {
		this.point = new Point(x, y);

		this.state = CellState.WATER;
		this.alreadyUsed = false;
	}
	
	public void setState(CellState state) {
		this.state = state;
	}
	
	public CellState getState() {
		return state;
	}

	public boolean isAlreadyUsed() {
		return alreadyUsed;
	}
	
	public void setAlreadyUsed(boolean alreadyUsed) {
		this.alreadyUsed = alreadyUsed;
	}

    public void setShip(Ship ship) {
		this.ship = ship;
	}

	public ShootState doShot() {
        alreadyUsed = true;
		if (state == CellState.WELL) {
            state = CellState.INJURED;
			return ship.doShot();
		}

        if ( state == CellState.BORDER || state == CellState.WATER) {
            state = CellState.MISSED;
        }
        return ShootState.MISSED;
	}

    public Point getPosition() {
        return point;
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }
}
