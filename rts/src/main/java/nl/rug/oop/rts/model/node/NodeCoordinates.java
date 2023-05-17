package nl.rug.oop.rts.model.node;

import java.awt.*;

/**
 * Class for everything about the coordinates of nodes.
 */
public class NodeCoordinates {
    private int x;
    private int y;
    /** Width of the frame. */
    public static final int WIDTH = 70;
    /** Height of the frame. */
    public static final int HEIGHT = 70;

    /**
     * gets the x position of the top left corner of the node.
     *
     * @return X position
     */
    public int getX() {
        return x;
    }

    /**
     * gets the y position of the top left corner of the node.
     *
     * @return Y position
     */
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the width of the node.
     *
     * @return WIDTH
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Gets the height of the node.
     *
     * @return HEIGHT
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Checks if a point is inside the node.
     * Used for checking if a click is within the node.
     *
     * @param x X position of the point to check.
     * @param y Y position of the point to check.
     * @return True if the point is inside the node
     */
    public boolean isInBounds(int x, int y) {
        return x >= this.x && x <= this.x + getWidth() && y >= this.y && y <= this.y + getHeight();
    }

    /**
     * X position for the center of the node.
     *
     * @return X Center.
     */
    public int getCenterX() {
        return x + WIDTH / 2;
    }

    /**
     * Y position for the center of the node.
     *
     * @return Y Center.
     */
    public int getCenterY() {
        return y + HEIGHT / 2;
    }

    /**
     * Gets the center of the node as a Point.
     *
     * @return Center Point.
     */
    public Point getCenter() {
        return new Point(getCenterX(), getCenterY());
    }
}
