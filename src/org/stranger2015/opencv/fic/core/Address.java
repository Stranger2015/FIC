package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.codec.DecAddress;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.EnumSet;
import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.EAddressKind.ORDINARY;
import static org.stranger2015.opencv.fic.core.codec.SaUtils.createAddress;

/**
 *
 */
public abstract
class Address<A extends Address <A>> implements IAddress <A> {

    public final static int radix = 10;

    protected int address;

    /**
     * @param address
     */
    @Contract(pure = true)
    protected
    Address ( int address) throws ValueError {
        this.address = address;
    }

    /**
     * @throws ValueError
     */
    protected
    Address () throws ValueError {
        this(0);//fixme
    }

    protected
    Address ( EnumSet digits ) {

    }

    /**
     * Returns the value of the specified number as an {@code int}.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
//    @Override
    public
    int intValue () {
        return getIndex();
    }

    /**
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    A newInstance ( int index ) throws ValueError {
        return (A) new DecAddress<A>(index, 10, ORDINARY);
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
        return (A) new DecAddress <A>(address1.getIndex() + address2.getIndex());
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
        return (A) new DecAddress <>(address1.getIndex() - address2.getIndex());
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
        return null;//(A) new Address <A>(address1.getIndex() * address2.getIndex());
    }

    /**
     * @return
     */
    @Override
    public
    int radix () {
        return Address.radix;
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
    int getIndex () {
        return address;
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
    List <Point> getCartesianCoordinates ( int radix ) {
        return null;
    }

    /**
     * @param radix
     * @return
     */
    public
    Point getCartesianCoordinate ( int radix ) {
        return null;
    }

    @Override
    public
    A divide () throws ValueError {
        return null;
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
     * @param scale
     * @return
     */

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
     *
     * @param radix
     * @param scale
     * @return
     */
//        Xscr = result from mapping cartesian to screen
//                Xc = half of screen size
//                X = X cartesian coordinate
//        Pcx = centre of the screen in cartesian coordinates
//        S = Scale Factor

//        return new AddressedPoint(xScreen, yScreen );
    }
