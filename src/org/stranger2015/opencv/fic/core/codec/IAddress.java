package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EDigits;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.EnumSet;

/**
 *
 */
public
interface IAddress<A extends IAddress <A>> extends IAddressMath <A>, IAddressExpr <A> {

    /**
     * @return
     */
    @Override
    int getIndex ();

    /**
     * @param result
     * @return
     */
    A newInstance ( EnumSet <EDigits> result ) throws ValueError;

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
