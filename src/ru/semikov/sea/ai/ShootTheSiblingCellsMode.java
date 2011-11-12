package ru.semikov.sea.ai;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.semikov.sea.logic.*;

public class ShootTheSiblingCellsMode implements Shooter {

    private final Point point = new Point();

    private static final Random random = new Random();

	@Override
	public ShotState doShot(ActionHolder actionHolder) {
        List<Cell> list = getSiblingCells(actionHolder.getField());

        if (list.isEmpty()) {
            actionHolder.setRandomAction();
            return actionHolder.doShot();
        }

        Cell e = list.get(random.nextInt(list.size()));
        ShotState shot = e.doShot();
        if (shot == ShotState.INJURED) {
            actionHolder.setDirectionAction();
            actionHolder.setLocation(e.getPosition());
            actionHolder.setDirection(e.getX() == point.x ? Direction.DOWN : Direction.RIGHT);
        }
        return shot;
    }

    private List<Cell> getSiblingCells(Field field) {
        List<Cell> list = new ArrayList<Cell>();
        for (int i = -1; i < 1; i += 2) {
            addUnmarkedToList(list, field, point.x + i, point.y);
            addUnmarkedToList(list, field, point.x, point.y + i);
        }
        return list;
    }

    private void addUnmarkedToList(List<Cell> list, Field field, int x, int y) {
        if (field.isBound(x, y)) {
            Cell e = field.getCell(x, y);
            if (!e.isAlreadyUsed()) {
                list.add(e);
            }
        }
    }

    @Override
    public void setLocation(Point point) {
        this.point.setLocation(point);
    }
}
