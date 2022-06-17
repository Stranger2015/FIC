package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.utils.Point;

import static org.stranger2015.opencv.fic.core.EAddressKind.ORDINARY;

/**
 * @param <A>
 */
public
class DecAddress<A extends IAddress <A>> extends Address <A> {

    /**
     * @param index
     * @param i
     * @throws ValueError
     */
    public
    DecAddress ( long index, int i ) throws ValueError {
        super(index + i);
    }

    /**
     * @param address
     * @throws ValueError
     */
    public
    DecAddress ( long address ) throws ValueError {
        this(address, 0);
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
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    IAddress <A> plus ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError {
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
    IAddress <A> minus ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError {
        return null;
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    IAddress <A> mult ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError {
        return null;
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
    EAddressKind getAddressKind () {
        return ORDINARY;
    }
}
