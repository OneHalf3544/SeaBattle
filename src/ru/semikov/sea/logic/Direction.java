package ru.semikov.sea.logic;

/**
 * Date: 12.11.11
 * Time: 2:05
 *
 * @author OneHalf
 */
public class Direction {
    
    public static final Direction UP = new Direction(0, -1);
    public static final Direction DOWN = new Direction(0, 1);
    public static final Direction LEFT = new Direction(-1, 0);
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
}
