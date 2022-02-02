package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.ValueError;

public
class DecAddress<A extends Address<A>> extends Address<A> {

    public
    DecAddress (int  number) throws ValueError {
        super(number);
    }

    public
    DecAddress ( int index, int i, EAddressKind ordinary ) throws ValueError {
        super();

    }
}
