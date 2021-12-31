package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.SaAddress;
import org.stranger2015.opencv.fic.core.ValueError;

/**
 * ...
 * 2.2 SIP Conversion
 * HIP conversion relies on use of a re-sampling scheme
 * [3, 5] to match the location of points in the square and
 * hexagonal images. As SIP is based on square images,
 * no re-sampling scheme is needed. We need only to
 * convert the lattice of a square image to the new SIP
 * format based on the spiral addressing scheme. The
 * steps are as follow.
 * For a given image with size M × N , the num-
 * ber of SIP layers λ can be found by λ = (logM +
 * logN )/log(9); then the length of the SIP image is 9λ.
 * Because the SIP address scheme is base 9, the conver-
 * sion between the SIP address and a decimal number
 * can be found by (an an−1...a1) = an × 9n−1 + an−1 ×
 * 9n−2 + ... + a1, where the values ai of a SIP address are
 * 0 ≤ ai < 9. We can adapt the spiral addressing scheme
 * for HIP [9] and the SIP address can be represented as:
 * an an−1...a1 =
 * n∑
 * i=1
 * ai × 10i−1 (1)
 * where ∑ denotes Spiral Addition and × indicates Spi-
 * ral Multiplication [5]. For example, the point at SIP
 * address 867 can be located by finding the addresses of
 * 800, 60 and 7. Next, we explain how to locate these
 * SIP addresses in a standard 2D square image.
 * For a point represented by Cartesian coordinate (x,y ),
 * we define the centre point as L(0) = (0, 0).
 * Based on the SIP addressing scheme, we can find the points
 * in layer-1:
 * L( 1 ) = ( -1, 0 ),
 * L( 2 ) = ( -1, 1 ),
 * L( 3 ) = ( 0, 1 ),
 * L( 4 ) = ( 1, 1 ),
 * L( 5 ) = ( 1, 0 ),
 * L( 6 ) = ( 1, -1 ),
 * L( 7 ) = ( 0, -1 ),
 * L( 8 ) = ( -1, -1 )
 */
public
class SipAddress<A extends Address <A/*, E*/>/*, E extends Enum<E>*/> extends SaAddress <A/*, E*/> {

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

//    /**
//     *
//     * @param digits
//     */
//    public
//    SipAddress ( EnumSet <EDigits7> digits ) throws ValueError {
//        super(digits);
//    }

    /**
     * @param address
     */
//    @SuppressWarnings("unchecked")
    public
    SipAddress ( int address ) throws ValueError {
        super(address);
        //this(EnumSet.of(EDigits7.values()[i]));
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

//    /**
//     *
//     * @return
//     * @param result
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public
//    A newInstance ( EnumSet result ) throws ValueError {
//        return (A) new SipAddress <>(0);
//    }

    /**
     * @return
     */
    @Override
    public
    int radix () {
        return 9;
    }
}
