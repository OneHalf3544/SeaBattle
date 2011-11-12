package ru.semikov.sea.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship {

    public enum State {
        WELL,
        INJURED,
        KILLED
        }

    private int x;
    private int y;
    private int dx;
    private int dy;
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
		Random rand = new Random();

        if (rand.nextBoolean()) {
            this.dx = 1;
            this.dy = 0;
        } else {
            this.dx = 0;
            this.dy = 1;
        }
        
        this.x = rand.nextInt(field.getWidth() - dx * size);
        this.y = rand.nextInt(field.getHeight() - dy * size);
    }

	private boolean checkPlace() {
        return shipPlacer.checkPlaceForShip(this, getX(), getY(), getDx(), getDy());
	}

	private void setShip() {
		shipPlacer.setShip(this, getX(), getY(), getDx(), getDy());
	}

	public ShootState doShot() {
        if (health == 0) {
            return ShootState.INJURED;
        }

        health--;
        if (health != 0) {
            state = State.INJURED;
            return ShootState.INJURED;
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
        return ShootState.KILLED;
    }
	
	public int getSize() {
		return size;
	}

	public State getState() {
		return state;
	}

	public Field getField() {
		return field;
	}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

}
