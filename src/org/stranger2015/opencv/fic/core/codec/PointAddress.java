package org.stranger2015.opencv.fic.core.codec;

import  org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EAddressKind;
import org.stranger2015.opencv.fic.core.IAddress;

import static org.stranger2015.opencv.fic.core.EAddressKind.POINT;

/**
 *
 *
 * @param <A>
 */
public
class PointAddress<A extends IAddress <A>>
        extends Address <A> {

    /**
     *
     *
     * @param x
     * @param y
     */
    public
    PointAddress ( int x, int y ) {
        super(x, y);
    }

    /**
     *
     *
     * @return
     */
    @Override
    public
    EAddressKind getAddressKind () {
        return POINT;
    }

    /**
     *
     *
     * @param table
     * @return
     */
    @Override
    public
    IAddress <A> applyTable ( int[][] table ) {
        return null;
    }
}
