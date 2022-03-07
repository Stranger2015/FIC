package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.EAddressKind;

/**
 * sa addresses
 * 0,        1,      2,       3,      4,      5,      6
 * <p>
 * Cartesian coordinates
 * {  0,  0 } 0
 * {  0, -1 } 1
 * { -1, -1 } 2
 * { -1,  0 } 3
 * {  0,  1 } 4
 * {  1,  1 } 5
 * {  1,  0 } 6
 */
public
class SaAddress<A extends Address <A>> extends Address <A> {

    public final static int radix = 7;

    static final int[][] addTable = new int[][]{
            {0, +1, +2, +3, +4, +5, +6},
            {1, 63, 15, +2, +0, +6, 64},
            {2, 15, 14, 26, +3, +0, +1},
            {3, +2, 26, 25, 31, +4, +0},
            {4, +0, +3, 31, 36, 42, +5},
            {5, +6, +0, +4, 42, 41, 53},
            {6, 64, +1, +0, +5, 53, 52},
            };

    static final int[][] multTable = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6},
            {0, 2, 3, 4, 5, 6, 1},
            {0, 3, 4, 5, 6, 1, 2},
            {0, 4, 5, 6, 1, 2, 3},
            {0, 5, 6, 1, 2, 3, 4},
            {0, 6, 1, 2, 3, 4, 5},
            };

    private int number;

    /**
     * @param number
     * @throws ValueError
     */
    public
    SaAddress ( int number , EAddressKind addressKind ) throws ValueError {
        super(number);
//        this.number = number;
    }

    public
    SaAddress () throws ValueError {
        super();
    }

    public
    SaAddress ( int x, int y, EAddressKind addressKind ) throws ValueError {
        this(x * y,  addressKind);
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
     * @return
     */
    @Override
    public
    int getIndex () {
        return address;//todo
    }

//    /**
//     * @param index
//     * @return
//     */
//    @Override
//    public
//    A newInstance ( int index ) throws ValueError {
//        return null;
//    }

    /**
     * Based on <b>add table</b>, the sum of 57 and 8 can be computed as:
     * <p>
     * 1. first 7 and 8 added to obtain 72.
     * 2. The 7 is then carried and added to 5 to produce 6.
     * The result is thus 62.
     * <p>
     * todo test!!!
     *
     * @param address1
     * @param address2
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A plus ( A address1, A address2 ) throws ValueError {
//        int[][] table = getAddTable();
//        EnumSet <EDigits7> digits1 = (EnumSet <EDigits7>) address1.getDigits();
//        EnumSet <EDigits7> digits2 = (EnumSet <EDigits7>) address2.getDigits();
//        EnumSet <EDigits7> result = noneOf(EDigits7.class);
//
//        Iterator <EDigits7> iterator1 = digits1.iterator();
//        Iterator <EDigits7> iterator2 = digits2.iterator();
//        IntStream.iterate(0, i -> iterator1.hasNext(), i -> i + 1).forEachOrdered(i -> {
//            EDigits7 d1 = iterator1.next();
//            EDigits7 d2 = iterator2.next();
//            int r = table[d1.ordinal()][d2.ordinal()];
//            add(result, values()[r], i);
//        });
//
//        return newInstance(result);3
        return null;
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
//        int[][] table = getAddTable();
//        EnumSet <EDigits7> digits1 = (EnumSet <EDigits7>) address1.getDigits();
//        EnumSet <EDigits7> digits2 = (EnumSet <EDigits7>) address2.getDigits();
//        EnumSet <EDigits7> result = noneOf(EDigits7.class);
//
//        Iterator <EDigits7> iterator1 = digits1.iterator();
//        Iterator <EDigits7> iterator2 = digits2.iterator();
//        IntStream.iterate(0, i -> iterator1.hasNext(), i -> i + 1).forEachOrdered(i -> {
//            EDigits7 d1 = iterator1.next();
//            EDigits7 d2 = iterator2.next();
//            int r = table[d1.ordinal()][d2.ordinal()];
//            add(result, values()[r], i);
//        });
//
//        return newInstance(result);
        return null;
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
//        int[][] table = getMultTable();
//        EnumSet <EDigits7> digits1 = (EnumSet <EDigits7>) address1.getDigits();
//        EnumSet <EDigits7> digits2 = (EnumSet <EDigits7>) address2.getDigits();
//        EnumSet <EDigits7> result = noneOf(EDigits7.class);
//
//        Iterator <EDigits7> iterator1 = digits1.iterator();
//        Iterator <EDigits7> iterator2 = digits2.iterator();
//        IntStream.iterate(0, i -> iterator1.hasNext(), i -> i + 1).forEachOrdered(i -> {
//            EDigits7 d1 = iterator1.next();
//            EDigits7 d2 = iterator2.next();
//            int r = table[d1.ordinal()][d2.ordinal()];
//            add(result, values()[r], i);
//        });
//
//        return newInstance(result);
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getPlusTable () {
        return addTable.clone();
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getMultTable () {
        return multTable.clone();
    }

    /**
     * @return
     */
    @Override
    public
    int radix () {
        return radix;
    }

    /**
     * @return
     */
    public
    int getNumber () {
        return number;
    }

    /*
     * sa addresses
     * 0,        1,      2,       3,      4,      5,      6
     *
     * Cartesian coordinates
     * {  0,  0 }  0
     * {  0, -1 }  1
     * { -1, -1 }  2
     * { -1,  0 }  3
     * {  0,  1 }  4
     * {  1,  1 }  5
     * {  1,  0 }  6
     */

//    @Override
//    public
//    AddressedPoint getCartesianCoords ( int addressBase, int radix, int scale ) {
//        AddressedPoint p = CENTER;
}