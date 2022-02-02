package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.EnumSet;

/**
 *
 */
public
interface IAddress<A extends Address <A>> extends IAddressMath <A> {

    /**
     * @return
     */
    @Override
    int getIndex ();

    /**
     * @param index
     * @return
     */
    A newInstance ( int index ) throws ValueError;

    /**
     * @return
     */
    int radix();
}
