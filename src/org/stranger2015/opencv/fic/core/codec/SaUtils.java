package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EDigits7;
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
    <A extends Address <A>> A createAddress ( int number, int radix, EAddressKind addressKind )
            throws ValueError {
//        EnumSet <EDigits7> digits = EnumSet.noneOf(EDigits7.class);
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
//            add(EDigits7.values()[digit], i);
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

    /**
     * @param value
     * @param i
     */
    public static
    void add ( EDigits7 value, int i ) {
        value.getOccurrences().set(i);
    }

    /**
     * @param digits
     * @return
     */
    public static
    int toNumber ( EnumSet <EDigits7> digits/*, Class <?> clazz*/ ) {
        int number = 0;
        int i = 0;
        for (EDigits7 next : digits) {
            next.getOccurrences().set(i++);
            number += 10;
        }

        return number;
    }
}
