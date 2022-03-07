package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.ValueError;

/**
 * @param <A>
 */
public
class DecAddress<A extends Address <A>> extends Address <A> {

    /**
     * @param number
     * @throws ValueError
     */
    public
    DecAddress ( int number ) throws ValueError {
        this(number, EAddressKind.ORDINARY);
    }

    /**
     * @param index
     * @param i
     * @param ordinary
     * @throws ValueError
     */
    public
    DecAddress ( int index, int i, EAddressKind ordinary ) throws ValueError {
        super(index, i, ordinary);
    }

    /**
     * @param address
     * @param addressKind
     * @throws ValueError
     */
    public
    DecAddress ( int address, EAddressKind addressKind) throws ValueError {
        this(address, 0, addressKind);
    }
}
