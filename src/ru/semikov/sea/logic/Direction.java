package ru.semikov.sea.logic;

import java.awt.*;

/**
 * Date: 12.11.11
 * Time: 2:05
 *
 * @author OneHalf
 */
public class Direction {

    public static final Direction DOWN = new Direction(0, 1);
    public static final Direction RIGHT = new Direction(1, 0);
    
    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Direction getOppositeDirection() {
        return new Direction(-dx, -dy);
    }

    public Point nextPoint(Point point) {
        return new Point(point.x + dx, point.y + dy);
    }
}
