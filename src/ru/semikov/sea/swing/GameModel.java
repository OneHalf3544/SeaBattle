package ru.semikov.sea.swing;

import ru.semikov.sea.ai.ActionHolder;
import ru.semikov.sea.logic.Field;
import ru.semikov.sea.logic.ShotState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Main window representation
 */
public class GameModel {

	private final Collection<FieldChangeListener> listeners = new ArrayList<FieldChangeListener>();

	private final Field playerFieldPlayer;
	private final Field playerFieldOpponent;
	private final ActionHolder actionHolder;
    
	private boolean enableShot;

	public GameModel(Dimension dimension, int maxShipSize) {
		playerFieldPlayer = new Field(dimension, maxShipSize);
		playerFieldOpponent = new Field(dimension, maxShipSize);
		actionHolder = new ActionHolder(playerFieldPlayer);

        setFieldParams(dimension, maxShipSize);
	}

	public final void setFieldParams(Dimension dimension, int maxShipSize) {
		playerFieldOpponent.setDimension(dimension);
		playerFieldOpponent.setMaxShipSize(maxShipSize);
		
		playerFieldPlayer.setDimension(dimension);
		playerFieldPlayer.setMaxShipSize(maxShipSize);

		enableShot = true;
		newGame();

        fireFieldChange();
	}
	
	/**
	 * Set ships again
	 */
	public void newGame() {
		playerFieldPlayer.setShip();
		playerFieldOpponent.setShip();
		enableShot = true;

		fireFieldChange();
	}

	/**
	 * Shot on specified cell on opponent field. Further make some shots by opponent to the user field
     * @param x coordinate for shot
     * @param y coordinate for shot
     */
	public void doShotByUser(int x, int y) {
		if (!enableShot) {
			return;
		}

        if (playerFieldOpponent.getCell(x, y).isAlreadyUsed()) {
            return;
        }

        if (playerFieldOpponent.doShot(x, y) == ShotState.MISSED) {
            while (actionHolder.doShot() != ShotState.MISSED) {
            }
        }

		fireFieldChange();

		if ( playerFieldPlayer.getNumLiveShips() == 0 || playerFieldOpponent.getNumLiveShips() == 0) {
			enableShot = false;
		}
	}

	public void addListener(FieldChangeListener o) {
		listeners.add(o);
		o.fieldChanged();
	}

    private void fireFieldChange() {
        for (FieldChangeListener o : listeners) {
            o.fieldChanged();
        }
	}

    public Field getPlayerFieldPlayer() {
        return playerFieldPlayer;
    }

    public Field getPlayerFieldOpponent() {
        return playerFieldOpponent;
    }
}
