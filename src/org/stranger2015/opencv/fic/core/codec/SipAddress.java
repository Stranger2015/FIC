package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EDigits;
import org.stranger2015.opencv.fic.core.SaAddress;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.EnumSet;

/**
 *
 */
public
class SipAddress<A extends SipAddress <A>> extends SaAddress <A> {

    /**
     *
     */
    protected static final int[][] addTable = new int[][]{
            {0, +1, +2, +3, +4, +5, +6, +7, +8},
            {1, 15, 14, +2, +3, +0, +7, +8, 16},
            {2, 14, 26, 38, 37, +3, +0, +1, 15},
            {3, +2, 38, 37, 36, +4, +5, +0, +1},
            {4, +3, 37, 36, 48, 52, 51, +5, +0},
            {5, +0, +3, +4, 52, 51, 58, +6, +7},
            {6, +7, +0, +5, 51, 58, 62, 74, 73},
            {7, +8, +1, +0, +5, +6, 74, 73, 72},
            {8, 16, 15, +1, +0, +7, 73, 72, 84},
            };

    /**
     *
     */
    protected static final int[][] multTable = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, 3, 4, 5, 6, 7, 8},
            {0, 2, 3, 4, 5, 6, 7, 8, 1},
            {0, 3, 4, 5, 6, 7, 8, 1, 2},
            {0, 4, 5, 6, 7, 8, 1, 2, 3},
            {0, 5, 6, 7, 8, 1, 2, 3, 4},
            {0, 6, 7, 8, 1, 2, 3, 4, 5},
            {0, 7, 8, 1, 2, 3, 4, 5, 6},
            {0, 8, 1, 2, 3, 4, 5, 6, 7},
            };


    /**
     * @param digits
     */
    public
    SipAddress ( EnumSet <EDigits> digits ) throws ValueError {
        super(digits);
    }

    /**
     * @param i
     */
    public
    SipAddress ( int i ) throws ValueError {
        this(EnumSet.of(EDigits.values()[i]));
    }

    /**
     *
     */
    public
    SipAddress () throws ValueError {
        this(0);
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
     *
     * @return
     * @param result
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A newInstance ( EnumSet <EDigits> result ) throws ValueError {
        return (A) new SipAddress <A>(0);
    }

    /**
     *
     * @return
     */
    @Override
    public
    int radix () {
        return 9;
    }
}
