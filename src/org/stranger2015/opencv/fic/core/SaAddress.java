 package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.EAddressKind;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.utils.Point;

import static org.stranger2015.opencv.fic.core.codec.EAddressKind.*;

/**
 * sa addresses
 * 0,        1,      2,       3,      4,      5,      6
 * <p>
 * Cartesian coordinates
 * {  0,  0 } 0
 * {  0, -1 } 1
 * { -1, -1 } 2
 * { -1,  0 } 3
 * {  0,  1 } 4
 * {  1,  1 } 5
 * {  1,  0 } 6
 */
public
class SaAddress<A extends IAddress <A>> extends Address <A> {

    public final static int radix = 7;

    static final int[][] addTable = new int[][]{
            {0, +1, +2, +3, +4, +5, +6},
            {1, 63, 15, +2, +0, +6, 64},
            {2, 15, 14, 26, +3, +0, +1},
            {3, +2, 26, 25, 31, +4, +0},
            {4, +0, +3, 31, 36, 42, +5},
            {5, +6, +0, +4, 42, 41, 53},
            {6, 64, +1, +0, +5, 53, 52},
            };

    static final int[][] multTable = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6},
            {0, 2, 3, 4, 5, 6, 1},
            {0, 3, 4, 5, 6, 1, 2},
            {0, 4, 5, 6, 1, 2, 3},
            {0, 5, 6, 1, 2, 3, 4},
            {0, 6, 1, 2, 3, 4, 5},
            };

    /**
     * @param number
     */
    public
    SaAddress ( long number ) {
        super(number);
    }

    /**
     *
     */
    public
    SaAddress () {
        super(0);
    }

    /**
     * @param x
     * @param y
     */
    public
    SaAddress ( int x, int y ) {
        this((long) x * y);//x+rowStride
    }

    public
    SaAddress ( int row, int stride, int col ) {
        super(row, stride, col);
    }

    /**
     * Returns the value of the specified number as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
    @Override
    public
    int intValue () {
        return (int) getIndex();
    }

    /**
     * @return
     */
    @Override
    public
     EAddressKind getAddressKind () {
        return SPIRAL;
    }

    /**
     * @return
     */
    @Override
    public
    long getIndex () {
        return longValue();//todo
    }

    /**
     * @param address
     * @param offset
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress <A> newInstance ( long address, int offset ) throws ValueError {
        return super.newInstance(address, offset);
    }

    /**
     * @param table
     * @return
     */
    @Override
    public
    IAddress <A> applyTable ( int[][] table ) {
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

    @Override
    public
    Point mult ( Point point1, int number ) {
        return super.mult(point1, number);
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getPlusTable () {
        return addTable.clone();
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getMultTable () {
        return multTable.clone();
    }

    /**
     * @return
     */
    @Override
    public
    int radix () {
        return radix;
    }
}

/*
 * sa addresses
 * 0,        1,      2,       3,      4,      5,      6
 *
 * Cartesian coordinates
 * {  0,  0 }  0
 * {  0, -1 }  1
 * { -1, -1 }  2
 * { -1,  0 }  3
 * {  0,  1 }  4
 * {  1,  1 }  5
 * {  1,  0 }  6
 */