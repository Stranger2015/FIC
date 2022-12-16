package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.utils.Point;

import static org.stranger2015.opencv.fic.core.EAddressKind.ORDINARY;

/**
 * @param
 */
public
class DecAddress<A extends IAddress > extends Address  {

    private int addr;

    /**
     * @param index
     * @param i
     * @throws ValueError
     */
    public
    DecAddress ( int row, int stride, int col ) throws ValueError {
        addr=stride*row + col;
    }


    public
    DecAddress ( int segment, int offset ) {
        super(segment, offset);
    }

    /**
     * @param table
     * @return
     */
    @Override
    public
    IAddress  applyTable ( int[][] table ) {
        return null;
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    IAddress  plus ( IAddress  address1, IAddress  address2 ) throws ValueError {
        return null;
    }

    /**
     * @param point1
     * @param point2
     * @return
     */
    @Override
    public
    Point plus ( Point point1, Point point2 ) {
        return super.plus(point1, point2);
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    IAddress  minus ( IAddress  address1, IAddress  address2 ) throws ValueError {
        return null;
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    IAddress  mult ( IAddress  address1, IAddress  address2 ) throws ValueError {
        return null;
    }

    @Override
    public
    Point mult ( Point point1, int number ) {
        return super.mult(point1, number);
    }

    @Override
    public
    int getAddr () {
        return addr;
    }

    /**
     * @return
     */
    @Override
    public
    EAddressKind getAddressKind () {
        return ORDINARY;
    }

    @Override
    public
    int getX () {
        return 0;
    }

    @Override
    public
    int getY () {
        return 0;
    }
}
