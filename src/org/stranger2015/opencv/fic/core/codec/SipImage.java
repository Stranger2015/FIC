package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.*;

import static org.stranger2015.opencv.fic.core.IAddress.valueOf;

/**
 *
 */
public
class SipImage<A extends IAddress <A>> extends Image <A> {

    protected int[] pixels;

    /**
     * @param input
     * @param pixels
     */
    public
    SipImage ( Image <A> input, int[] pixels ) {
        super(input, pixels);

        this.pixels = pixels;
    }

    /**
     * @param row
     * @return
     */
    @Override
    public
    int[] get ( int row ) {
        return pixels;//fixme
    }

    /**
     * @param address
     * @return
     */
    @Override
    public
    int[] getPixels ( IAddress <A> address ) {
        return new int[0];
    }

    /**
     * @param addr
     * @param i
     * @return
     */
    @Override
    public
    int pixelValues ( int addr, int i ) {
        return 0;
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    SipImage <A> createInputImage ( IImage <A> image ) throws ValueError {
        return new SipImage <>(image.getMat());
    }

    /**
     * @param image
     * @return
     */
//    @Override
    public
    CompressedSipImage <A> createOutputImage ( IImage <A> image ) {
        return new CompressedSipImage <>(image);
    }

    /**
     * @param data
     * @return
     */
    @Override
    public
    int put ( int addr, int... data ) throws ValueError {
//        int address = row + width() * col;//??? fixme

        return super.putPixels((int)valueOf(addr), data);
    }

    /**
     * @param imread
     */
    public
    SipImage ( Mat imread ) throws ValueError {
        super(new SipImage <>(imread), imread.rows(), imread.cols());
    }

    /**
     * @param imread
     * @param size
     */
    public
    SipImage ( Mat imread, IntSize size ) {
        super((MatOfInt) imread, size);
    }

    public
    int[] getPixelValue ( int address ) {
        return pixels;
    }

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
    @Override
    public
    void setSample ( IAddress <A> address, int b, int s ) {

    }

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
    @Override
    public
    int getSample ( IAddress <A> address, int b ) {
        return 0;
    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    List <ImageBlock <A>> getRangeBlocks () {
//        return null;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    List <ImageBlock <A>> getDomainBlocks () {
//        return null;
//    }
//
//    /**
//     * @return
//     */
//    @Override
//    public
//    List <ImageBlock <A>> getCodebookBlocks () {
//        return null;
//    }
//

    /**
     * @return
     */
    @Override
    public
    int pixelAmount () {
        return getWidth() * getHeight();
    }

    /**
     * @return
     */
    @Override
    public
    MatOfInt getMat () {
        return null;
    }

    /**
     * @param i
     * @param i1
     * @param sideSize
     * @param img1pixels
     * @param i2
     * @param i3
     */
    @Override
    public
    void getRGB ( int i, int i1, int sideSize, int[] img1pixels, int i2, int i3 ) {

    }

    /**
     * @return
     */
    @Override
    public
    int[] getPixels () {
        return pixels;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public
    int compareTo ( @NotNull IImage <A> o ) {
        return 0;
    }
}
