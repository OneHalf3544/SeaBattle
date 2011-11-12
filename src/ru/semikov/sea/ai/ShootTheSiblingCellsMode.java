package ru.semikov.sea.ai;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.semikov.sea.logic.*;

public class ShootTheSiblingCellsMode implements Shooter {

    private Point point = new Point();
    private Direction direction;

    private static final Random random = new Random();

	@Override
	public ShootState doShot(ActionHolder actionHolder) {
        List<Cell> list = getSiblingCells(actionHolder.getField());

        if (list.isEmpty()) {
            actionHolder.setRandomAction();
            return actionHolder.doShot();
        }

        Cell e = list.get(random.nextInt(list.size()));
        ShootState shot = e.doShot();
        if (shot == ShootState.INJURED) {
            actionHolder.setDirectionAction();
            actionHolder.setLocation(e.getPosition());
            actionHolder.setDirection(e.getX() != point.x ? Direction.RIGHT : Direction.UP);
        }
        return shot;
    }

    private List<Cell> getSiblingCells(Field field) {
        List<Cell> list = new ArrayList<Cell>();
        for (int i = -1; i < 1; i += 2) {
            int x = point.x + i;
            int y = point.y;

            Cell e = field.getCell(x, y);
            if (!e.isAlreadyUsed()) {
                list.add(e);
            }

            x = point.x;
            y = point.y + i;

            e = field.getCell(x, y);
            if (!e.isAlreadyUsed()) {
                list.add(e);
            }
        }
        return list;
    }

    @Override
    public void setLocation(Point point) {
        this.point.setLocation(point);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
