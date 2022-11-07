package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.List;

import static java.util.stream.IntStream.iterate;

/**
 * @param <A>
 */
public
interface IAddressMath<A extends IAddress <A>> {

    /**
     * @param table
     * @return
     */
   IAddress<A> applyTable(int[][] table);

    /**
     *
     * @param address1
     * @param address2
     * @return
     */
    default
    IAddress <A> plus ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError {
         address1.getPlusTable();

        return IAddress.valueOf(-1, inputImage.getWidth(), i2);//fixme
    }

    /**
     * @param point1
     * @param point2
     * @return
     */
    default
    Point plus ( Point point1, Point point2 ) {
        return new Point((int) (point1.getX() + point2.getX()), (int) (point1.getY() + point2.getY()));
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    IAddress <A> minus ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError;

    /**
     * @param address1
     * @param address2
     * @return
     */
    IAddress <A> mult ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError;

    default
    Point mult ( Point point1, int number ) {
        return new Point((int) (point1.getX() * number), (int) (point1.getY() * number));
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
    long getIndex ();

    /**
     * @param base
     * @param pow
     * @return
     */
    @Contract(pure = true)
    static
    int pow ( int base, int pow ) {
        int result = 1;
//        if (pow > 0) {
//            for (int i = 0; i < pow; i++) {
//                result *= base;
//            }
//        }

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

        return iterate(0, i -> i + 1).filter(i -> pow(base, i) == num).findFirst().orElse(0);
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


    /**
     * @param pow
     * @return
     */
    @Contract(pure = true)
    static
    int pow3 ( int pow ) {
        return 0;
    }
}
