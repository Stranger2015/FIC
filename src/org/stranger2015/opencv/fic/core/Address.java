package org.stranger2015.opencv.fic.core;

import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.codec.EAddressKind;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.fic.core.codec.SaUtils;

import java.util.EnumSet;

/**
 *
 */
public
class Address<A extends Address <A/*, E*/>/*, E extends Enum <E>*/> implements IAddress <A/*, E*/> {
    public final static int radix = 10;
    protected int address;
    /**
     * @param address
     */
    public
    Address ( int address ) throws ValueError {
//     return    SaUtils.createAddress(number, 10, EAddressKind.ORDINARY);
        this.address = address;
    }

    /**
     * @throws ValueError
     */
    public
    Address () throws ValueError {
        this(0);
    }

    public
    Address ( EnumSet digits ) {

    }

    /**
     * Returns the value of the specified number as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
//    @Override
    public
    int intValue () {
        return getIndex();
    }

//    /**
//     * @param result
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public
//    A newInstance ( EnumSet result ) throws ValueError {
//        return (A) new Address <>(SaUtils.toNumber(result));
//    }

    /**
     * @param index
     * @return
     */
//    @SuppressWarnings("unchecked")
    @Override
    public
    A newInstance ( int index ) throws ValueError {
        return SaUtils.createAddress(index, 10, EAddressKind.ORDINARY);
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A plus ( A address1, A address2 ) throws ValueError {
        return (A) new Address <>(address1.getIndex() + address2.getIndex());
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A minus ( A address1, A address2 ) throws ValueError {
        return (A) new Address <>(address1.getIndex() - address2.getIndex());
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A mult ( A address1, A address2 ) throws ValueError {
        return (A) new Address <A/*, E*/>(address1.getIndex() * address2.getIndex());
    }

    /**
     * @return
     */
    @Override
    public
    int radix () {
        return Address.radix;
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getAddTable () {
        return new int[0][];
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getMultTable () {
        return new int[0][];
    }

//    /**
//     * @return
//     */
//    @Override
//    public
//    EnumSet <E> getDigits () {
//        return null;//todo
//    }

//    /**
//     * @param number
//     * @param radix
//     * @return
//     */
//    @Override
//    public
//    EnumSet <E> toDigits ( int number, int radix ) {
//        for (EDigits next : getDigits()) {
//            int digit = next.ordinal();
//            BitSet occurrences = next.getOccurrences();
//            if(occurrences.isEmpty()){
//continue;
//            }
//        }

//        return null;
//    }

    /**
     * @param number
     * @return
     */
    @SuppressWarnings("unchecked")
//    @Override
    public
    A carryRule ( int number ) throws ValueError {
        return (A) new Address <>(number);
    }

    /**
     * @return
     */
    @Override
    public
    int getIndex () {
        return address;
    }

    public
Point getCartesianCoords ( int i ) {
    Point v = new Point(0,0);
    switch (i) {
        case 0:
            break;
        case 1:
            v.setX = -1;
            break;
        case 2:
            v[0] = -1;
            v[1] = -1;
            break;
        case 3:
            v[0] = -1;
            break;
        case 4:
            v[1] = 1;
            break;
        case 5:
            v[0] = 1;
            v[1] = 1;
            break;
        case 6:
            v[0] = 1;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + i);
    }

    return v;
}
}
