package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.SaAddress;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.EnumSet;

/**
 *
 */
public
class SaUtils {
    /**
     * None can instantiate
     */
    private
    SaUtils () {
    }

    /**
     * An-1 ... A0 = Sum(i=0, n-1){ Ai X pow(10, i) }
     *
     * @param number
     * @return
     */
    @SuppressWarnings("unchecked")
    public static
    <A extends IAddress <A>> A createAddress ( int number, int radix, EAddressKind addressKind )
            throws ValueError {
        boolean loop = true;
        for (int i = 0; loop; i++) {
            int digit;
            if (number >= radix) { //modulo |number| >= radix
                digit = number % 10                                                                                     ;
                number -= digit;//add table??
                number /= 10;//--------------->
            }
            else {
                digit = number;
                loop = false;
            }
        }
        switch (addressKind) {
            case ORDINARY:
                return (A) new DecAddress <A>(number);
            case SPIRAL:
                return (A) new SaAddress <A>(number);
            case SQUIRAL:
                return (A) new SipAddress <A>(number);
            default:
                throw new IllegalStateException("Unexpected value: " + addressKind);
        }
    }
}
