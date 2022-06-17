package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.utils.Point;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.stream.IntStream.range;

/**
 *
 */
public abstract
class Address<A extends IAddress <A>> extends AtomicLong implements IAddress <A> {

    static {
        range(0, cache.length())
                .forEachOrdered(i -> cache.getAndSet(i, i - 128));
    }

    public final static int radix = 10;

    /**
     * @param segment
     * @param offset
     */
    public
    Address ( int segment, int offset) {
        super(segment + offset);
    }

    public
    Address ( long number ) {
        super(number);
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    IAddress <A> plus ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError {
        long addr1 = address1.longValue();
        long addr2 = address2.longValue();
//        long result = address1.getPlusTable()[0];

        return null;
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    IAddress <A> minus ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError {
        return null;//todo
    }

    /**
     * @param address1
     * @param address2
     * @return
     */
    @Override
    public
    IAddress <A> mult ( IAddress <A> address1, IAddress <A> address2 ) throws ValueError {
        return null;//todo
    }


    /**
     * @return
     */
    @Override
    public
    int[][] getPlusTable () {
        return new int[0][];
    }

    /**
     * @return
     */
    @Override
    public
    int[][] getMinusTable () {
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
    long getIndex () {
        return 0;
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
    @Override
    public
    List <Point> getCartesianCoordinates ( int radix ) throws ValueError {
        return null;
    }

    /**
     * @param index
     * @return
     */
    @Override
    public
    IAddress <A> newInstance ( long index ) throws ValueError {
        return null;
    }

    /**
     * @param address
     * @param offset
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress <A> newInstance ( long address, int offset ) throws ValueError {
        return IAddress.super.newInstance(address, offset);
    }

    /**
     * @return
     */
    @Override
    public
    int radix () {
        return 10;
    }
//
//    protected EAddressKind addressKind = ORDINARY;
//    protected long address;
//
//    /**
//     * @param address
//     */
//    @Contract(pure = true)
//    protected
//    Address ( long address ) throws ValueError {
//        this.address = address;
//    }
//
//    /**
//     * @param address
//     */
//    @Contract(pure = true)
//    protected
//    Address ( long address, int i ) throws ValueError {
//        this(address * i); //fixme rows cols
//    }
//
//    /**
//     * @param address
//     */
//    @Contract(pure = true)
//    protected
//    Address ( long address, int i, EAddressKind addressKind ) throws ValueError {
//        this.addressKind = addressKind;
//        this.address = address * i; //fixme rows cols
//    }
//
//    /**
//     * @throws ValueError
//     */
//    protected
//    Address () throws ValueError {
//        this(0);//fixme
//    }
//
//    /**
//     * Returns the value of the specified number as an {@code int}.
//     *
//     * @return the numeric value represented by this object after conversion
//     * to type {@code int}.
//     */
////    @Override
//    public
//    int intValue () {
//        return (int) getIndex();
//    }
//
//    /**
//     * @param index
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public
//    IAddress<A,N> newInstance ( long index ) throws ValueError {
//        if (this instanceof DecAddress){
//            return new DecAddress<A,N>(index);
//        }
//        return new DecAddress <A,N>(index, 10);
//    }
//
//    /**
//     * @param address
//     * @param offset
//     * @return
//     * @throws ValueError
//     */
//    @Override
//    public
//     IAddress<A,N> <A,N> newInstance ( long address, int offset ) throws ValueError {
//        return IAddress.super.newInstance(address, offset);
//    }
//
//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public
//    A plus ( A address1, A address2 ) throws ValueError {
//        return address1.newInstance(address1.getIndex() + address2.getIndex());
//    }
//
//    /**
////     * @param address1
////     * @param address2
//     * @return
//     */
//    @Override
//    public
//     IAddress<A,N> plus (  IAddress<A,N> address1,  IAddress<A,N> address2 ) throws ValueError {
//        return null;
//    }
//
//    /**
//     * @param point1
//     * @param point2
//     * @return
//     */
//    @Override
//    public
//    Point plus ( Point point1, Point point2 ) {
//        return IAddress.super.plus(point1, point2);
//    }
//
//    @Override
//    public
//    Point mult ( Point point1, int number ) {
//        return IAddress.super.mult(point1, number);
//    }
//
//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @Override
//    public
//     IAddress<A,N> mult (  IAddress<A,N> address1,  IAddress<A,N> address2 ) throws ValueError {
//        return null;
//    }
//
//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @Override
//    public
//     IAddress<A,N> minus (  IAddress<A,N> address1,  IAddress<A,N> address2 ) throws ValueError {
//        return null;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    int radix () {
//        return Address.radix;
//    }
//
//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @Override
//    public
//    Address plus ( Address address1, Address address2 ) throws ValueError {
//        return null;
//    }
//
//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @Override
//    public
//    Address minus ( Address address1, Address address2 ) throws ValueError {
//        return null;
//    }
//
//    /**
//     * @param address1
//     * @param address2
//     * @return
//     */
//    @Override
//    public
//    Address mult ( Address address1, Address address2 ) throws ValueError {
//        return null;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    int[][] getPlusTable () {
//        return new int[0][];
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    int[][] getMinusTable () {
//        return new int[0][];
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    int[][] getMultTable () {
//        return new int[0][];
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    long getIndex () {
//        return address;
//    }
//
//    /**
//     * L( 0 ) = (  0,  0 )   0
//     * L( 1 ) = ( -1,  0 ),  1 layer-1:
//     * L( 2 ) = ( -1,  1 ),  2
//     * L( 3 ) = (  0,  1 ),  3
//     * L( 4 ) = (  1,  1 ),  4
//     * L( 5 ) = (  1,  0 ),  5
//     * L( 6 ) = (  1,  -1 ), 6
//     * L( 7 ) = (  0,  -1 ), 7
//     * L( 8 ) = ( -1,  -1 )  8
//     *
//     * @param radix
//     * @return
//     */
//    @Override
//    public
//    List <Point> getCartesianCoordinates ( int radix ) {
//        return null;
//    }
//
//    /**
//     * @param radix
//     * @return
//     */
//    public
//    Point getCartesianCoordinate ( int radix ) {
//        return null;//FIXME
//    }

//    /**
//     * L( 0 ) = (  0,  0 )   0
//     * L( 1 ) = ( -1,  0 ),  1 layer-1:
//     * L( 2 ) = ( -1,  1 ),  2
//     * L( 3 ) = (  0,  1 ),  3
//     * L( 4 ) = (  1,  1 ),  4
//     * L( 5 ) = (  1,  0 ),  5
//     * L( 6 ) = (  1,  -1 ), 6
//     * L( 7 ) = (  0,  -1 ), 7
//     * L( 8 ) = ( -1,  -1 )  8
//     *
//     * @param radix
//     * @param scale
//     * @return
//     */
//
//    /**
//     * L( 0 ) = (  0,  0 )   0
//     * L( 1 ) = ( -1,  0 ),  1 layer-1:
//     * L( 2 ) = ( -1,  1 ),  2
//     * L( 3 ) = (  0,  1 ),  3
//     * L( 4 ) = (  1,  1 ),  4
//     * L( 5 ) = (  1,  0 ),  5
//     * L( 6 ) = (  1,  -1 ), 6
//     * L( 7 ) = (  0,  -1 ), 7
//     * L( 8 ) = ( -1,  -1 )  8
//     *
//     *
//     * @param radix
//     * @param scale
//     * @return
//     */
////        Xscr = result from mapping cartesian to screen
////                Xc = half of screen size
////                X = X cartesian coordinate
////        Pcx = centre of the screen in cartesian coordinates
////        S = Scale Factor
//
////        return new AddressedPoint(xScreen, yScreen );
}
