package org.stranger2015.opencv.fic.core;

import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Polygon;

/**
 * @param <A>
 */
public
interface IImageBlock<A extends IAddress <A>> extends IImage <A> {
    Polygon <?> getPolygon();

    /**
     * @return
     */
    default
    int pixelAmount () {
        return getWidth() * getHeight();
    }

    /**
     * @param address
     */
    void setAddress ( IAddress <A> address );

    /**
     * @param n
     * @return
     */
    int plus ( int... n );

    /**
     * @return
     */
    IImageBlock <A> subImage ( int rowStart, int rowEnd, int colStart, int colEnd );

    /**
     * @return
     */
    IIntSize getSize ();

    /**
     * Sets a sample in the specified band for the pixel located at (x,y)
     * in the DataBuffer using an int for input.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param address
     * @param b       The band to set.
     * @param s       The input sample as an int.
     * @throws NullPointerException           if data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates or
     *                                        the band index are not in bounds.
     */
    void setSample ( IAddress <A> address, int b, int s );

    /**
     * Returns the sample in a specified band for the pixel located
     * at (x,y) as an int.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param address
     * @param b       The band to return.
     * @return the sample in a specified band for the specified pixel.
     * @throws NullPointerException           if data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates or
     *                                        the band index are not in bounds.
     */
    int getSample ( IAddress <A> address, int b );

    /**
     * @return
     */
    MatOfInt getMat ();

    /**
     * @param i
     * @param i1
     * @param sideSize
     * @param img1pixels
     * @param i2
     * @param i3
     */
    void getRGB ( int i, int i1, int sideSize, int[] img1pixels, int i2, int i3 );

    /**
     * @param address
     * @param data
     * @return
     */
    default
    int get ( IAddress <A> address, int[] data ) {
        return data[(int) address.get()];
    }

    /**
     * @return
     */
  @Override
    default
    boolean isSquare () {
        return getWidth() == getHeight();
    }

    /**
     *
     *
     * @param x
     * @param i
     */
    @Override
    void put ( int x, int y, int[] data );

    /**
     *
     *
     * @return
     */
    @Override
    int getMeanPixelValue ();

    /**
     *
     * @param minRangeSize
     * @return
     */
    int compareTo ( IIntSize minRangeSize );

    /**
     *
     * @return
     */
    int getX ();

    /**
     *
     * @return
     */
    int getY ();

    /**
     * @param p0
     * @param p1
     * @param p2
     * @return
     */
    IImageBlock<A> getTriangleSubImage ( Coordinate p0, Coordinate p1, Coordinate p2 );

    /**
     * @param coords
     * @return
     */
    IImageBlock<A> getPolygonSubImage ( Coordinate ... coords );
}
