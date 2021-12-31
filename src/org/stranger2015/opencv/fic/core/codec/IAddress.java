package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.ValueError;

import java.util.EnumSet;

/**
 *
 */
public
interface IAddress<A extends IAddress <A/*, E*/>/*, E extends Enum<E>*/> extends IAddressMath <A/*, E*/> {

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
