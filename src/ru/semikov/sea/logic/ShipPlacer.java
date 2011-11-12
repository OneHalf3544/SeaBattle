package ru.semikov.sea.logic;

public class ShipPlacer {

    private final Field field;

    public ShipPlacer(Field field) {
        this.field = field;
    }

    public boolean checkPlaceForShip(Ship ship, int x, int y, int dx, int dy) {
        for(int i = 0; i < ship.getSize(); i++) {
            if (field.getCell(x + i * dx, y + i * dy).getState() != CellState.WATER) {
                return false;
            }
		}
        return true;
    }
    
    public void setShip(Ship ship, int x, int y, int dx, int dy) {
        for(int i = 0; i < ship.getSize(); i++) {
			int n = x + i * dx;
            int m = y + i * dy;
            setShipPart(ship, n, m);

            n = x + i * dx - dy;
            m = y + i * dy - dx;
			setBorder(ship, n, m);

            n = x + i * dx + dy;
            m = y + i * dy + dx;
			setBorder(ship, n, m);
		}

		for(int i = -1; i <= 1; i++) {
            int n = x + i * dy - dx;
            int m = y + i * dx - dy;
			setBorder(ship, n, m);

            n = x + i * dy + dx * ship.getSize();
            m = y + i * dx + dy * ship.getSize();
			setBorder(ship, n, m);
		}
    }

    private void setShipPart(Ship ship, int x, int y) {
        Cell cell = field.getCell(x, y);

        cell.setState(CellState.WELL);
        ship.getListCells().add(cell);
        cell.setShip(ship);
    }

    private void setBorder(Ship ship, int x, int y) {
        if (!field.isBound(x, y)) {
            return;
        }

        Cell cell = field.getCell(x, y);

        cell.setState(CellState.BORDER);
        ship.getListBorders().add(cell);
    }

}
