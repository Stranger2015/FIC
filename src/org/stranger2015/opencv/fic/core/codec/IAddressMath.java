package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.utils.IPartible;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.List;

/**
 * @param <A>
 */
public
interface IAddressMath<A extends Address <A>> extends IPartible <A> {
    /**
     * @param address1
     * @param address2
     * @return
     */
    A plus ( A address1, A address2 ) throws ValueError;

    default
    Point plus ( Point point1, Point point2 ) {
        return new Point(point1.getX() + point2.getX(), point1.getY() + point2.getY());
    }

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


    default
    Point mult ( Point point1, int number ) {
        return new Point(point1.getX() * number, point1.getY() * number);
    }

    /**
     * @return
     */
    int[][] getPlusTable ();

    /**
     * @return
     */
    int[][] getMinusTable ();

    /**
     * @return
     */
    int[][] getMultTable ();

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
        for (int i = 0; ; i++) {
            if (pow(base, i) == num) {
                result = i;
                break;
            }
        }

        return result;
    }

    /**
     * L( 0 ) = (  0,  0 )   0
     * L( 1 ) = ( -1,  0 ),  1 layer-1:
     * L( 2 ) = ( -1,  1 ),  2
     * L( 3 ) = (  0,  1 ),  3
     * L( 4 ) = (  1,  1 ),  4
     * L( 5 ) = (  1,  0 ),  5
     * L( 6 ) = (  1,  -1 ), 6
     * L( 7 ) = (  0,  -1 ), 7
     * L( 8 ) = ( -1,  -1 )  8
     *
     * @param radix
     * @return
     */
    List <Point> getCartesianCoordinates ( int radix ) throws ValueError;


    static
    int pow3 ( int pow ) {
        return 0;
    }
}


