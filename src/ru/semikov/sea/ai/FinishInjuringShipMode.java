package ru.semikov.sea.ai;

import java.awt.*;
import java.util.*;
import java.util.List;

import ru.semikov.sea.logic.*;

public class FinishInjuringShipMode implements Shooter {
    
    private static final Random random = new Random();

    private Point position = new Point();
    private Direction direction;

    /**
     * Search a not injured part of ship, in specified direction
     *
     * @param list
     * @param field
     * @param direction
     * @return
     */
	public void draw(List<Cell> list, Field field, Direction direction) {
        int x = position.x;
		int y = position.y;

		do {
			x += direction.getDx();
			y += direction.getDy();

            if (!field.isBound(x, y)) {
                return;
            }
		}
        while (field.getCell(x, y).getState() == CellState.INJURED);

        Cell e = field.getCell(x, y);

        if (!e.isAlreadyUsed()) {
            list.add(e);
        }
    }

	@Override
	public ShotState doShot(ActionHolder actionHolder) {
        List<Cell> list = getSiblingNotInjuredCells(actionHolder.getField());

        if (list.isEmpty()) {
            actionHolder.setRandomAction();
            return actionHolder.doShot();
        }
        return list.get(random.nextInt(list.size())).doShot();
    }

    private List<Cell> getSiblingNotInjuredCells(Field field) {
        List<Cell> list = new ArrayList<Cell>();

        draw(list, field, direction);
        draw(list, field, direction.getOppositeDirection());
        
        return list;
    }

    @Override
    public void setLocation(Point point) {
        position.setLocation(point);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
