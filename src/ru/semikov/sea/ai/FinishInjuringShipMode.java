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
     * @param field
     * @param direction
     * @return
     */
	public Set<Cell> draw(Field field, Direction direction) {
        int x = position.x;
		int y = position.y;

		do {
			x += direction.getDx();
			y += direction.getDy();
		}
        while (field.getCell(x, y).getState() == CellState.INJURED);

        Cell e = field.getCell(x, y);

        if (e.isAlreadyUsed()) {
            return Collections.emptySet();
        }
        return Collections.singleton(e);

    }

	@Override
	public ShootState doShot(ActionHolder actionHolder) {
        List<Cell> list = getSiblingNotInjuredCells(actionHolder.getField());

        if (list.isEmpty()) {
            actionHolder.setRandomAction();
            return actionHolder.doShot();
        }
        return list.get(random.nextInt(list.size())).doShot();
    }

    private List<Cell> getSiblingNotInjuredCells(Field field) {
        List<Cell> list = new ArrayList<Cell>();

        list.addAll(draw(field, direction));
        list.addAll(draw(field, direction.getOppositeDirection()));
        
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
