package ru.semikov.sea.logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship {

    private static final Random rand = new Random();

    public enum State {
        WELL,
        INJURED,
        KILLED
    }

    private Point location;
    private Direction direction;

    private final int size;
    private int health;
    private State state;
    private final Field field;
    private final List<Cell> listCells;
    private final List<Cell> listBorders;

    private final ShipPlacer shipPlacer;

    public List<Cell> getListCells() {
        return listCells;
    }

    public List<Cell> getListBorders() {
        return listBorders;
    }

    public Ship(Field field, int size) {
		this.size = size;
		this.health = size;
		this.field = field;
		this.state = State.WELL;

        this.shipPlacer = new ShipPlacer(field);

		do {
			this.getPlace();
		} while (! checkPlace() );

		this.listCells = new ArrayList<Cell>();
		this.listBorders = new ArrayList<Cell>();
		this.setShip();

        this.field.incrementNumLiveShips();
	}

	private void getPlace() {
        this.direction = rand.nextBoolean() ? Direction.RIGHT : Direction.DOWN;

        this.location = new Point(
                rand.nextInt(field.getWidth() - direction.getDx() * size),
                rand.nextInt(field.getHeight() - direction.getDy() * size));
    }

	private boolean checkPlace() {
        return shipPlacer.checkPlaceForShip(this, location, direction);
	}

	private void setShip() {
		shipPlacer.setShip(this, location, direction);
	}

	public ShotState doShot() {
        if (health == 0) {
            return ShotState.INJURED;
        }

        health--;
        if (health != 0) {
            state = State.INJURED;
            return ShotState.INJURED;
        }

        field.setNumLiveShips(field.getNumLiveShips() - 1);
        state = State.KILLED;
        for(Cell cell : listCells) {
            cell.setState(CellState.KILLED);
        }
        for(Cell cell : listBorders) {
            cell.setState(CellState.MISSED);
            cell.setAlreadyUsed(true);
        }
        return ShotState.KILLED;
    }
	
	public int getSize() {
		return size;
	}

	public State getState() {
		return state;
	}
}
