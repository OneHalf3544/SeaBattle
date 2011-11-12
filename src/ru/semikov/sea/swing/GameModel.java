package ru.semikov.sea.swing;

import ru.semikov.sea.ai.ActionHolder;
import ru.semikov.sea.logic.Field;
import ru.semikov.sea.logic.ShootState;

import java.util.ArrayList;
import java.util.List;

/**
 * Main window representation
 */
public class GameModel {

    public enum CurrentPlayer {
        OPPONENT,
        USER
    }

	private List<ISubscriber> listeners = new ArrayList<ISubscriber>();

	public Field playerFieldPlayer;
	public Field playerFieldOpponent;
	public ActionHolder actionHolder;

    public CurrentPlayer currentPlayer;
	private boolean enableShot;

	public GameModel(int dx, int dy, int numShip) {
		playerFieldPlayer = new Field(dx, dy, numShip);
		playerFieldOpponent = new Field(dx, dy, numShip);
		actionHolder = new ActionHolder(playerFieldPlayer);
		setDimension(dx, dy, numShip);
	}

	public void setDimension(int dx, int dy, int numShip) {
		playerFieldOpponent.setWidth(dx);
		playerFieldOpponent.setHeight(dy);
		playerFieldOpponent.setMaxShipSize(numShip);
		
		playerFieldPlayer.setWidth(dx);
		playerFieldPlayer.setHeight(dy);
		playerFieldPlayer.setMaxShipSize(numShip);
		enableShot = true;
		newGame();
		updateSubscribers();
	}
	
	/**
	 * Set ships again
	 */
	public void newGame() {
		playerFieldPlayer.setShip();
		playerFieldOpponent.setShip();
		enableShot = true;
		currentPlayer = CurrentPlayer.USER;
		updateSubscribers();
	}

	/**
	 * Shot on the current player
     * @param x coordinate for shot
     * @param y coordinate for shot
     */
	public void doShotByOpponent(int x, int y) {
		if (!enableShot) {
			return;
		}
		if (currentPlayer == CurrentPlayer.USER) {
			if (playerFieldOpponent.getCell(x, y).isAlreadyUsed()) {
				return;
			}
			if (playerFieldOpponent.doShot(x, y) == ShootState.MISSED) {
				// если промахнулись
				currentPlayer = CurrentPlayer.OPPONENT;
			}
		}

		if (currentPlayer == CurrentPlayer.OPPONENT) {
			while (actionHolder.doShot() != ShootState.MISSED) {
            }
			currentPlayer = CurrentPlayer.USER;
		}
		updateSubscribers();

		if ( (playerFieldPlayer.getNumLiveShips() == 0) || (playerFieldOpponent.getNumLiveShips() == 0) ) {
			enableShot = false;
		}
	}
	
	public void register(ISubscriber o) {
		listeners.add(o);
		o.update();
	}

    public void updateSubscribers() {
        for (ISubscriber o : listeners) {
            o.update();
        }
	}

}
