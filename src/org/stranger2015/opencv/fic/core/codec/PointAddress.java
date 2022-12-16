package org.stranger2015.opencv.fic.core.codec;

import  org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EAddressKind;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.ValueError;

import static org.stranger2015.opencv.fic.core.EAddressKind.POINT;

/**
 *
 *
 * @param 
 */
@Deprecated
public
class PointAddress<A extends IAddress >
        extends Address  {

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
    IAddress  applyTable ( int[][] table ) {
        return null;
    }

    @Override
    public
    IAddress  minus ( IAddress  address1, IAddress  address2 ) throws ValueError {
        return super.minus(address1, address2);
    }
}
