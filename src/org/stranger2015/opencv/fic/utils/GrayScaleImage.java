package org.stranger2015.opencv.fic.utils;

import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.geom.Coordinate;

import java.util.List;

/**
 * @param
 */
@Deprecated public
class GrayScaleImage extends Image {

    /**
     * @param image
     */
    public
    GrayScaleImage ( IImage image ) {
        this(image.getMat());
    }

    /**
     * @param inputImage
     */
    public
    GrayScaleImage ( Mat inputImage ) {
        super(actualImage, inputImage, size);
    }

    /**
     * @param inputImage
     */
    public
    GrayScaleImage ( Mat inputImage, IIntSize size ) throws ValueError {//FIXME
        super(actualImage, inputImage, size);

        IImage dest = new GrayScaleImage <>(inputImage);
        Imgproc.resize(inputImage,dest.getMat(),size.toSize());
    }

    /**
     * @param image
     * @param rows
     * @param cols
     * @param type
     */
    public
    GrayScaleImage ( IImage image, int rows, int cols, int type ) throws ValueError {
        super(image, rows, cols);
    }

    /**
     * @param address
     * @param pixel
     */
//    @Override
    public
    void putPixel ( IAddress  address, int pixel ) {

    }

    @Override
    public
    double getBeta () {
        return 0;
    }

    @Override
    public
    void setBeta ( double beta ) {

    }

    /**
     * @param address
     * @param pixels
     */
    @Override
    public
    void putPixels ( IAddress  address, int[][] pixels ) throws ValueError {
        super.putPixels(address, pixels);
    }

    /**
     * @return
     */
    @Override
    public
    IImage getComponents () {
        return List.of(this);
    }

    @Override
    public
    int get ( IAddress  address, int[] data ) {
        return data[(int) address.getIndex()];
    }

    /**
     * @param address
     * @return
     */
    @Override
    public
    int[] get ( int address ) {
        return new int[0];
    }

    /**
     * @param address
     * @return
     */
    @Override
    public
    int[] getPixels ( IAddress  address ) {
        return new int[0];
    }

    /**
     * @param addr
     * @param i
     * @return
     */
    @Override
    public
    double[] pixelValues ( int addr, int i ) {
        return 0;
    }

    @Override
    public
    void put ( int x, int i ) {

    }

    @Override
    public
    int getMeanPixelValue () {
        return 0;
    }

    @Override
    public
    IImageBlock  getSubImage ( Rectangle rectangle ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock  getSubImage () {
        return null;
    }

    @Override
    public
    void setMeanPixelValue ( int meanPixelValue ) {

    }

    /**
     * Sets a pixel in  the DataBuffer using an int array of samples for input.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param iArray The input samples in an int array.
     * @throws NullPointerException           if iArray or data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates are
     *                                        not in bounds, or if iArray is too small to hold the input.
     */
    @Override
    public
    void setPixel ( IAddress  address, int[] iArray ) {
        super.setPixel(address, iArray);
    }

    /**
     * @return
     */
    @Override
    public
    int getNumBands () {
        return super.getNumBands();
    }

    /**
     * Returns the samples for a specified band for the specified rectangle
     * of pixels in an int array, one sample per array element.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param w      The width of the pixel rectangle.
     * @param h      The height of the pixel rectangle.
     * @param b      The band to return.
     * @param iArray If non-null, returns the samples in this array.
     * @return the samples for the specified band for the specified region of pixels.
     * @throws NullPointerException           if data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates or
     *                                        the band index are not in bounds, or if iArray is too small to
     *                                        hold the output.
     */
//    @Override
    public
    int[] getSamples ( IAddress  address, int w, int h, int b, int[] iArray ) throws ValueError {
        return super.getSamples(address, w, h, b, iArray);
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
    void setSample ( IAddress  address, int b, int s ) {

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
    int getSample ( IAddress  address, int b ) {
        return 0;
    }

    /**
     * @return
     */
//    @Override
    public
    List <IImageBlock > getRangeBlocks () {
        return null;
    }

    /**
     * @return
     */
  //  @Override
    public
    List <IImageBlock > getDomainBlocks () {
        return null;
    }

    /**
     * @return
     */
    //@Override
    public
    List <IImageBlock > getCodebookBlocks () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int pixelAmount () {
        return 0;
    }

    @Override
    public
    void setAddress ( IAddress  address ) {

    }

    @Override
    public
    int plus ( int... n ) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    boolean isGrayScale () {
        return true;
    }

    /**
     * @return
     */
    @Override
    public
    Mat getMat () {
        return actualImage;
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
    int compareTo ( @NotNull IImage o ) {
        return 0;
    }

    @Override
    public
    boolean isHomogeneous () throws ValueError {
        return false;
    }

    @Override
    public
    Coordinate getCentroid () {
        return null;
    }

    @Override
    public
    Point[] tVertices () {
        return new Point[0];
    }

    @Override
    public
    double perimeter () {
        return 0;
    }

    @Override
    public
    EShape getShapeKind () {
        return null;
    }

    @Override
    public
    double area () {
        return 0;
    }

    @Override
    public
    GrayScaleImage  clone () {
        return (GrayScaleImage) super.clone();
    }
}