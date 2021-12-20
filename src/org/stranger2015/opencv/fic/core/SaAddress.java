package org.stranger2015.opencv.fic.core;

import java.util.*;

import static org.stranger2015.opencv.fic.core.EDigits.values;

/**
 * sa addresses
 * 0,        1,      2,       3,      4,      5,      6
 * Cartesian coordinates
 * {0, 0}   {0, -1}  {-1, -1}  {-1, 0} {0, 1}  {1, 1}  {1, 0}
 */
public
class SaAddress<A extends SaAddress <A>> extends Address <A> {

    public final EnumSet <EDigits> digits = EnumSet.noneOf(EDigits.class);

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

    /**
     *
     */


    static {
        final SaAddress <? extends SaAddress <?>> SA_NULL;
        try {
            SA_NULL = new SaAddress <>();
        } catch (ValueError e) {
            e.printStackTrace();
        }
    }

    private int number;

    /**
     * @param digits
     */
    public
    SaAddress ( EnumSet <EDigits> digits ) throws ValueError {
        super();
        this.digits.addAll(digits);
    }

    public
    SaAddress ( int number ) throws ValueError {
        super();
        this.number = number;
    }

    /**
     * @param digits
     */
    SaAddress ( EDigits... digits ) throws ValueError {
        super();
        this.digits.addAll(Arrays.asList(digits));
    }

    public
    SaAddress ( IAddressExpr <A> e ) {
        super(e);

    }

    /**
     * @throws ValueError
     */
    public
    SaAddress () throws ValueError {
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
        return getAddressExpr().toNumber();
    }

    /**
     * @return
     */
    @Override
    public
    IAddressExpr <A> getAddressExpr () {
        return addressExpr;
    }

    /**
     * @param result
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A newInstance ( EnumSet <EDigits> result ) throws ValueError {
        return (A) new SaAddress <A>();
    }

    /**
     * @param index
     * @return
     */
    @Override
    public
    A newInstance ( int index ) throws ValueError {
        IAddressExpr <A> e = createExpr(index);

        return newInstance(e);
    }

//    /**
//     * An-1 ... A0 = Sum(i=0, n-1){ Ai X pow(10, i) }
//     *
//     * @param number
//     * @return
//     */
////    @SuppressWarnings("unchecked")
//    protected
//    IAddressExpr <A> createExpr ( int number ) {
//        int radix = radix();
//        EnumSet <EDigits> digits = EnumSet.noneOf(EDigits.class);
//        BitSet occurrences = new BitSet();
//        boolean loop = true;
//        for (int i = 0; loop; i++) {
//            int digit;
//            if (number >= radix) {
//                digit = number % 10;
//                number -= digit;
//                number /= 10;
//            }
//            else {
//                digit = number;
//                loop = false;
//            }
//            occurrences.set(digit);
//            add(digits, values()[digit], i);
//        }
//
//        return new MultiDigitNumber <>(digits/*, occurrences*/);
//    }

    /**
     * @param e
     * @return
     */
    @SuppressWarnings("unchecked")
    private
    A newInstance ( IAddressExpr <A> e ) {
        return (A) new SaAddress <>(e);
    }

    /**
     * Based on <b>add table</b>, the sum of 57 and 8 can be computed as:
     * <p>
     * 1. first 7 and 8 added to obtain 72.
     * 2. The 7 is then carried and added to 5 to produce 6.
     * The result is thus 62.
     * <p>
     * todo test!!!!!!!!!!!!!!!
     *
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    A plus ( A address1, A address2 ) throws ValueError {
        int[][] table = getAddTable();
        EnumSet <EDigits> digits1 = address1.getDigits();
        EnumSet <EDigits> digits2 = address2.getDigits();
        EnumSet <EDigits> result = EnumSet.noneOf(EDigits.class);

        Iterator <EDigits> iterator1 = digits1.iterator();
        Iterator <EDigits> iterator2 = digits2.iterator();
        for (int i = 0; iterator1.hasNext() /*&& iterator2.hasNext()*/; i++) {
            EDigits d1 = iterator1.next();
            EDigits d2 = iterator2.next();

            int r = table[d1.ordinal()][d2.ordinal()];
            add(result, values()[r], i);
        }

        return newInstance(result);
    }

    /**
     * @param result
     * @param value
     * @param i
     */
    public
    void add ( EnumSet <EDigits> result, EDigits value, int i ) {
        value.getOccurrences().set(i, result.add(value));
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    A minus ( A address1, A address2 ) throws ValueError {
        int[][] table = getAddTable();
        EnumSet <EDigits> digits1 = address1.getDigits();
        EnumSet <EDigits> digits2 = address2.getDigits();
        EnumSet <EDigits> result = EnumSet.noneOf(EDigits.class);

        Iterator <EDigits> iterator1 = digits1.iterator();
        Iterator <EDigits> iterator2 = digits2.iterator();
        for (int i = 0; iterator1.hasNext() /*&& iterator2.hasNext()*/; i++) {
            EDigits d1 = iterator1.next();
            EDigits d2 = iterator2.next();
            int r = table[d1.ordinal()][d2.ordinal()];
            add(result, values()[r], i);
        }

        return newInstance(result);
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    A mult ( A address1, A address2 ) throws ValueError {
        int[][] table = getMultTable();
        EnumSet <EDigits> digits1 = address1.getDigits();
        EnumSet <EDigits> digits2 = address2.getDigits();
        EnumSet <EDigits> result = EnumSet.noneOf(EDigits.class);

        Iterator <EDigits> iterator1 = digits1.iterator();
        Iterator <EDigits> iterator2 = digits2.iterator();
        for (int i = 0; iterator1.hasNext() /*&& iterator2.hasNext()*/; i++) {
            EDigits d1 = iterator1.next();
            EDigits d2 = iterator2.next();
            int r = table[d1.ordinal()][d2.ordinal()];
            add(result, values()[r], i);
        }

        return newInstance(result);
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getAddTable () {
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
    EnumSet <EDigits> getDigits () {
        return digits;
    }

    /**
     * @param number
     * @param radix
     * @return
     */
    @Override
    public
    EnumSet <EDigits> toDigits ( int number, int radix ) {
        EnumSet <EDigits> digits = EnumSet.noneOf(EDigits.class);
        for (; number > 0; number /= radix) {
            EDigits d = values()[number % radix];
            digits.add(d);//todo
        }

        return digits;
    }

    @Override
    public
    A carryRule ( int number ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int radix () {
        return 7;
    }

    /**
     * @return
     */
    @Override
    public
    List <A> getLayers () {
        return null;
    }

    public
    int getNumber () {
        return number;
    }
}
