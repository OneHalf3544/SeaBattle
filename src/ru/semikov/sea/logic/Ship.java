package ru.semikov.sea.logic;

import java.util.ArrayList;
import java.util.Random;

public class Ship {

    public enum State {
        WELL,
        INJURED,
        KILLED;
    }

	public int x;
	public int y;
	public int dx;
	public int dy;
	private int size;
	private int health;
	private State state;
	private Field field;
	private ArrayList<Cell> listCells;
	private ArrayList<Cell> listBorders;
	
	public ArrayList<Cell> getListCells() {
		return listCells;
	}

	public ArrayList<Cell> getListBorders() {
		return listBorders;
	}

	public Ship(Field field, int size) {
		this.size = size;
		this.health = size;
		this.field = field;
		this.state = State.WELL;
		
		do {
			this.getPlace();
		} while (! checkPlace() );

		this.listCells = new ArrayList<Cell>();
		this.listBorders = new ArrayList<Cell>();
		this.setShip();
		
		getField().setNumLiveShips(getField().getNumLiveShips() + 1);
	}

	private void getPlace() {
		Random rand = new Random();
		this.x = rand.nextInt(getField().getWidth());
		this.y = rand.nextInt(getField().getHeight());
		this.dx = 0;
		this.dy = 0;
		if (rand.nextInt(2) == 1) {
			this.dx = 1;
		} else {
			this.dy = 1;
		}
	}
	
	/**
	 * ������� ������ ������� � ��� ���������
	 * 
	 * @return
	 */
	private boolean byPass(PlaceShip tp) {
		int i, m, n;
		
		for(i = 0; i < size; i++) {
			// �������
			m = y + i * dy;
			n = x + i * dx;
			if (! tp.setShip(m, n) ) {
				return false;
			}
			
			// �������� ������ � ����� �������
			m = y + i * dy - dx;
			n = x + i * dx - dy;
			if (! tp.setBorder(m, n) ) {
				return false;
			}
			m = y + i * dy + dx;
			n = x + i * dx + dy;
			if (! tp.setBorder(m, n) ) {
				return false;
			}
		}
		// �������� ����� � ������ �������
		for(i = -1; i < 2; i++) {
			m = y + i * dx - dy;
			n = x + i * dy - dx;
			if (! tp.setBorder(m, n) ) {
				return false;
			}
			m = y + i * dx + (dy * size);
			n = x + i * dy + (dx * size);
			if (! tp.setBorder(m, n) ) {
				return false;
			}
		}
		return true;
	}

	private boolean checkPlace() {
		return byPass(new PlaceShipCheck(this));
	}

	private void setShip() {
		byPass(new PlaceShipSet(this));
	}

	public int doShot() {
        if (health == 0) {
            return Field.SHUT_INJURED;
        }

        health--;
        if (health != 0) {
            state = State.INJURED;
            return Field.SHUT_INJURED;
        }

        getField().setNumLiveShips(getField().getNumLiveShips() - 1);
        state = State.KILLED;
        for(Cell e : listCells) {
            e.setState(Cell.State.KILLED);
        }
        for(Cell e : listBorders) {
            e.setState(Cell.State.MISSED);
            e.setMark(true);
        }
        return Field.SHUT_KILLED;
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

}
