package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EDigits;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.EnumSet;
import java.util.List;

/**
 * @param <A>
 */
public
interface IAddressMath<A extends IAddress <A>> {
    /**
     * @param address1
     * @param address2
     * @return
     */
    A plus ( A address1, A address2 ) throws ValueError;

    /**
     * @param address1
     * @param address2
     * @return
     */
    A minus ( A address1, A address2 ) throws ValueError;

    /**
     * @param address1
     * @param address2
     * @return
     */
    A mult ( A address1, A address2 ) throws ValueError;

//    /**
//     * @return
//     */
//    int radix ();

    /**
     * @return
     */
    List <A> getLayers ();

    /**
     * @return
     */
    int[][] getAddTable ();

    /**
     * @return
     */
    int[][] getMultTable ();

    /**
     * @return
     */
    EnumSet <EDigits> getDigits ();

    /**
     * @param number
     * @return
     */
    EnumSet <EDigits> toDigits ( int number, int radix );

    /**
     * @param number
     * @return
     */
    IAddress <A> carryRule ( int number ) throws ValueError;

    /**
     * @return
     */
    int getIndex ();

    /**
     * @return
     */
    int
    intValue ();

    /**
     * @return
     */
    default
    int[] getCartesianCoords () {
        int[] v = new int[]{0, 0};
        switch (intValue()) {
            case 0:
                break;
            case 1:
                v[1] = -1;
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
                throw new IllegalStateException("Unexpected value: " + intValue());
        }

        return new int[0];
    }
}
