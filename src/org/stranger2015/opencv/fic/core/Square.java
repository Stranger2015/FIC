package org.stranger2015.opencv.fic.core;

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
//    Square ( AddressedPoint p1, AddressedPoint p2 ) {
//        super(p1, p2);
//    }

//    /**
//     * @param p
//     * @param s
//     */
//    public
//    Square ( AddressedPoint p, Size s ) {
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
     * @return
     */
    @Override
    public
    double area () {
        return side * side;
    }
}

