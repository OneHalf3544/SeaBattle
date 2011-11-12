package ru.semikov.sea.ai;

import ru.semikov.sea.logic.Direction;
import ru.semikov.sea.logic.Field;
import ru.semikov.sea.logic.ShotState;

import java.awt.*;

public class ActionHolder {

	private final Field field;
    
    private Shooter currentAction;

    private final Shooter randomShotMode = new RandomShotMode();
    private final ShootTheSiblingCellsMode shootTheSiblingCellsMode = new ShootTheSiblingCellsMode();
    private final FinishInjuringShipMode finishShipMode = new FinishInjuringShipMode();


    public ActionHolder(Field field) {
		this.field = field;
		this.currentAction = new RandomShotMode();
	}

	public ShotState doShot() {
		return currentAction.doShot(this);
	}

	public Field getField() {
		return field;
	}

    public void setRandomAction() {
        this.currentAction = randomShotMode;
    }

    public void setDirectionAction() {
        this.currentAction = finishShipMode;
    }

    public void setPlaceAction() {
        this.currentAction = shootTheSiblingCellsMode;
    }

    public void setLocation(Point position) {
        currentAction.setLocation(position);
    }

    public void setDirection(Direction direction) {
        finishShipMode.setDirection(direction);
    }
}
