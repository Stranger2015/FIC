package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

public
class Square extends Polygon implements IShape {

    private final Rectangle rectangle;

    public
    Rectangle getRectangle () {
        return rectangle;
    }

    /**
     * @param x
     * @param y
     * @param size
     */
    public
    Square ( int x, int y, int size ) {
        super(x, y,4, size);
        rectangle = new Rectangle(x, y, size, size);
    }

    /**
     *
     */
    public
    Square () {
        super(0,0,4, 0);
        rectangle = new Rectangle(0, 0, 0, 0);
    }

    /**
     * @param p1
     * @param p2
     */
//    public
//    Square ( Point p1, Point p2 ) {
//        super(p1, p2);
//    }

//    /**
//     * @param p
//     * @param s
//     */
//    public
//    Square ( Point p, Size s ) {
//        super(p, s);
//    }

//    /**
//     * @param vals
//     */
//    public
//    Square ( double[] vals ) {
//        super(vals);
//    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Image image, Rect rect ) {
        rectangle.draw(image, rect);
    }

    /**
     * @return
     */
    @Override
    public
    double area () {
        return side * side;
    }
}

