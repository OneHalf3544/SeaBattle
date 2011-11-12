package ru.semikov.sea.ai;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.semikov.sea.logic.*;


public class RandomShotMode implements Shooter {
    
    private static final Random random = new Random();

	public ShootState doShot(ActionHolder ai) {
        List<Cell> list = searchUnmarkedCells(ai.getField());

		Cell randomCell = list.get(random.nextInt(list.size()));
		ShootState shot = randomCell.doShot();

        if (shot == ShootState.INJURED) {
			ai.setPlaceAction();
			ai.setLocation(randomCell.getPosition());
		}

        return shot;
	}

    private List<Cell> searchUnmarkedCells(Field field) {
        List<Cell> list = new ArrayList<Cell>();
        for (int j = 0; j < field.getWidth(); j++) {
            for (int i = 0; i < field.getHeight(); i++) {
                Cell e = field.getCell(i, j);
                if (! e.isAlreadyUsed() ) {
                    list.add(e);
                }
            }
        }

        if (list.isEmpty()) {
			throw new IllegalStateException("all fields are already used");
		}

        return list;
    }

    @Override
    public void setLocation(Point point) {
        throw new UnsupportedOperationException();
    }

}
