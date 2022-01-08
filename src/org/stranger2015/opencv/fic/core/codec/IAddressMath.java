package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.ValueError;

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

    /**
     * @return
     */
    Point[] getAddTable ();

    /**
     * @return
     */
    Point[] getMultTable ();

    /**
     * @return
     */
    int getIndex ();

    /**
     * @param base
     * @param pow
     * @return
     */
    @Contract(pure = true)
    static
    int pow ( int base, int pow ) {
        int result = 1;
        if (pow > 0) {
            for (int i = 0; i < pow; i++) {
                result *= base;
            }
        }

        return result;
    }

    /**
     * @param base
     * @param num
     * @return
     */
    @Contract(pure = true)
    static
    int log ( int base, int num ) {
        int result = 0;
        for (int i = 0;; i++) {
            if (pow(base, i) == num) {
                result = i;
                break;
            }
        }

        return result;
    }

    /**
     * @param address
     * @param radix
     * @return
     */
    Point getCartesianCoords ( int address, int radix)    {
        Point v = new Point(0, 0);
        switch (address) {
            case 0:
                break;
            case 1:
                v = new Point(0,-1);
                break;
            case 2:
                v = new Point(-1,-1);
                break;
            case 3:
                v = new Point(-1,0);
                break;
            case 4:
                v = new Point(1, 0);
                break;
            case 5:
                v= new Point(1,1);
                break;
            case 6:

                v[0] = 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + address);
        }

        return v;
    }

    }


