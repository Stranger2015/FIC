package org.stranger2015.opencv.fic.utils;

/**
 *
 */
public
class Point {
    protected int x;
    protected int y;

    /**
     * @param x
     * @param y
     */
    public
    Point ( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     */
    public
    Point () {
    }

    /**
     * @return
     */
    public
    int getX () {
        return x;
    }

    /**
     * @return
     */
    public
    int getY () {
        return y;
    }
}

