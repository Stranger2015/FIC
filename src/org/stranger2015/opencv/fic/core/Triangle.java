package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
class Triangle extends Polygon {
//    protected final Point p;

    /**
     * @param x
     * @param y
     * @param size
     */
    public
    Triangle ( int x, int y, int size ) {
        super(x, y,3, size);
    }

    /**
     * @return
     */
    @Override
    public
    double area () {
        return 0;
    }
}
