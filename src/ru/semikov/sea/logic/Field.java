package ru.semikov.sea.logic;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private Cell[][] cells;
	private List<Ship> ships;

	private int width;
	private int height;
	private int maxShipSize;
	private int numLiveShips;

	public Field(int x, int y, int ship) {
		setDimension(x, y, ship);
        setShip();
    }

    public final void setShip() {
        numLiveShips = 0;

        fillWithWater();

        // Fill the field by the ships
        ships = new ArrayList<Ship>();
        for(int i = maxShipSize; i > 0; i--) {
            int shipNum = maxShipSize - i + 1;
            for(int j = 0; j < shipNum; j++) {
                ships.add(new Ship(this, i));
            }
        }

        replaceBorderElementsOnWater();
    }

    /**
     * Remove the ship surrounded borders
     */
    private void replaceBorderElementsOnWater() {
        for(int j = 0; j < height; j++) {
            for(int i = 0; i < width; i++) {
                Cell cell = cells[i][j];
                if (cell.getState() == CellState.BORDER) {
                    cell.setState(CellState.WATER);
                }
            }
        }
    }

    /**
     * Fill the fields by water elements
     */
    private void fillWithWater() {
        cells = new Cell[width][height];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    private void setDimension(int x, int y, int ship) {
        width = x;
        height = y;
        maxShipSize = ship;
	}

    public ShootState doShot(int x, int y) {
        return getCell(x, y).doShot();
	}
	
	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
	
	public List<Ship> getShips() {
		return ships;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMaxShipSize() {
		return maxShipSize;
	}

	public void setMaxShipSize(int maxShipSize) {
		this.maxShipSize = maxShipSize;
	}

	public int getNumLiveShips() {
		return numLiveShips;
	}

	public void setNumLiveShips(int numLiveShips) {
		this.numLiveShips = numLiveShips;
	}

	public void incrementNumLiveShips() {
		this.numLiveShips++;
	}

    public boolean isBound(int x, int y) {
        return (x >= 0 && x < getWidth()) && (y >= 0 && y < getHeight());
    }
}
