package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.EAddressKind;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.fic.core.codec.SaUtils;

import java.util.EnumSet;
import java.util.List;

/**
 *
 */
public
class Address<A extends Address <A>> implements IAddress <A> {
    protected final List <A> layers = List.of();
//    protected final EnumSet <EDigits> digits;

    /**
     * @param number
     */
    public
    Address ( int number ) throws ValueError {
//     return    SaUtils.createAddress(number, 10, EAddressKind.ORDINARY);
    }

    /**
     * @throws ValueError
     */
    public
    Address () throws ValueError {
        this(0);
    }

    public
    Address ( EnumSet <EDigits> e ) {
    }

    /**
     * Returns the value of the specified number as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
    @Override
    public
    int intValue () {
        return getIndex();
    }

    /**
     * @param result
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A newInstance ( EnumSet <EDigits> result ) throws ValueError {
        return (A) new Address <A>(SaUtils.toNumber(result));
    }

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
        return (A) new Address <A>(address1.getIndex() + address2.getIndex());
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
        return (A) new Address <A>(address1.getIndex() * address2.getIndex());
    }

    /**
     * @return
     */
    @Override
    public
    int radix () {
        return 10;
    }

    /**
     * @return
     */
    @Override
    public
    List <A> getLayers () {
        return layers;
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

    /**
     * @return
     */
    @Override
    public
    EnumSet <EDigits> getDigits () {
        return null;//todo
    }

    /**
     * @param number
     * @param radix
     * @return
     */
    @Override
    public
    EnumSet <EDigits> toDigits ( int number, int radix ) {
//        for (EDigits next : getDigits()) {
//            int digit = next.ordinal();
//            BitSet occurrences = next.getOccurrences();
//            if(occurrences.isEmpty()){
//continue;
//            }
//        }

        return null;
    }

    /**
     * @param number
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A carryRule ( int number ) throws ValueError {
        return (A) new Address <A>(number);
    }

    /**
     * @return
     */
    @Override
    public
    int getIndex () {
        return toNumber();
    }

    /**
     * @return
     */
    @Override
    public
    int toNumber () {
        return 0;
    }//todo
}
