package ru.semikov.sea.ai;

import ru.semikov.sea.logic.ShootState;

import java.awt.*;

/**
 * Date: 12.11.11
 * Time: 1:28
 *
 * @author OneHalf
 */
public interface Shooter {

    public ShootState doShot(ActionHolder actionHolder);

    public void setLocation(Point point);
}
