package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EDigits;
import org.stranger2015.opencv.fic.core.SaAddress;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.EnumSet;

import static org.stranger2015.opencv.fic.core.EDigits.values;

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
    <A extends IAddress <A>> A createAddress ( int number, int radix, EAddressKind addressKind ) throws ValueError {
        EnumSet <EDigits> digits = EnumSet.noneOf(EDigits.class);
        boolean loop = true;
        for (int i = 0; loop; i++) {
            int digit;
            if (number >= radix) {
                digit = number % 10;
                number -= digit;//add table????????????????
                number /= 10;//--------------->
            }
            else {
                digit = number;
                loop = false;
            }
            add(values()[digit], i);
        }
        IAddress <?> result;
        switch (addressKind) {
            case ORDINARY:
                result = new Address <>(digits);
                break;
            case SPIRAL:
                result = new SaAddress <>(digits);
                break;
            case SQUIRAL:
                result = new SipAddress <>(digits);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + addressKind);
        }

        return (A) result;
    }

    /**
     * @param value
     * @param i
     */
    public static
    void add ( EDigits value, int i ) {
        value.getOccurrences().set(i);
    }

    /**
     * @param digits
     * @return
     */
    public static
    int toNumber ( EnumSet <EDigits> digits, Class<?> clazz ) {
        int number = 0;
        int i = 0;
        for (EDigits next : digits) {
            next.getOccurrences().set(i++);
//            BitSet o = next.getOccurrences();
//            for (int j = o.nextSetBit(0); j >= 0; j = o.nextSetBit(j + 1)) {
//                 operate on index j here
//                if (j == Integer.MAX_VALUE) {
//                    break; // or (j+1) would overflow
//                }
            number += 10;
        }

        return number;
    }

    public static
    int toNumber ( EnumSet <EDigits> result ) {
        return 0;
    }
}
