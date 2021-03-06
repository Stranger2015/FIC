package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public
class ImageBlock<A extends IAddress <A>>
        extends Image <A>
        implements IImageBlock <A> {

    /**
     *
     */
    public int width;
    public int height;

    public double beta;
    public int meanPixelValue;

    /**
     *
     */
    public IIntSize blockSize;
    private EShape kind;

    /**
     * @param image
     * @param x
     * @param y
     * @param sideSize
     * @param kind
     * @throws ValueError
     */
    public
    ImageBlock ( IImage <A> image, int x, int y, int sideSize, Point[] vertices, EShape kind ) throws ValueError {
        super(image, vertices);
        this.kind = kind;
        blockSize = new IntSize(sideSize, sideSize);
    }


    /**
     * @param inputImage
     * @param i1
     * @param j
     * @param blockSize
     * @param vertices
     */
    public
    ImageBlock ( IImage <A> inputImage, int i1, int j, IIntSize blockSize, Point[] vertices ) {
        super(inputImage, vertices);
    }

    /**
     * @param submat
     */
    public
    ImageBlock ( MatOfInt submat, Point[] vertices ) {
        super(submat, vertices);
    }

    /**
     * @param image
     * @param address
     * @param blockSize
     */
    public
    ImageBlock ( IImage <A> image, IIntSize blockSize, IAddress <A> address, Point[] vertices ) {
        super(image, vertices, blockSize, address);
    }

    /**
     * @param image
     * @param rows
     * @param cols
     * @param pixels
     */
    public
    ImageBlock ( MatOfInt image, Point[] vertices, int rows, int cols, int[] pixels ) throws ValueError {
        super(image, vertices, rows, cols, pixels);
    }

    public
    ImageBlock ( Mat submat ) {
        super(submat);
    }

    /**
     * @param image
     * @param vertices
     * @param blockSize
     * @param address
     */
    public
    ImageBlock ( IImage <A> image, Point[] vertices, IIntSize blockSize, IAddress <A> address ) {
        this(image, blockSize, address, vertices);
    }

    /**
     * @param image
     * @param p
     * @param address
     * @param cols
     * @param pixels
     */
    public
    ImageBlock ( MatOfInt image, Point[] p, IAddress <A> address, int cols, int[] pixels ) {
        super(image, p, address, cols, pixels);
    }

    public
    ImageBlock ( IImage <A> image, IIntSize blockSize, IAddress <A> address ) {
        super(image, blockSize, address);

    }

    public
    ImageBlock ( RegionOfInterest <A> roi, int i, int j, int blockWidth, int blockHeight ) {
        super(roi, i, j, blockWidth, blockHeight);
    }

    /**
     * @return
     */
    @Override
    public
    IShape getShape () {
        return this;
    }

    /**
     * @return
     */
    @Override
    public
    int getWidth () {
        return width;
    }

    /**
     * @return
     */
    @Override
    public
    int getHeight () {
        return height;
    }

    @Override
    public
    void setBeta ( double beta ) {
        this.beta = beta;
    }

    /**
     * @return
     */
    @Override
    public
    double getBeta () {
        return beta;
    }

    /**
     * @return
     * @param threshold
     */
    @Override
    public
    boolean isHomogeneous ( int threshold ) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public
    EShape getShapeKind () {
        return kind;
    }

    /**
     * @return
     */
    @Override
    public
    double area () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    IAddress <A> getAddress () {
        return address;
    }

    /**
     * @return
     */
    @Override
    public
    Vertex getCentroid () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    Point[] getVertices () {
        return new Point[0];
    }

    /**
     * @return
     */
    @Override
    public
    int pixelAmount () {
        return getHeight() * getWidth();
    }

    /**
     * @param address
     */
    public
    void setAddress ( IAddress <A> address ) {
        this.address = address;
    }

    /**
     * @param n
     * @return
     */
    public
    int plus ( int... n ) {
        int result = 0;
        for (int j : n) {
            result += j;
        }

        return result;
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock <A> subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return super.subImage(rowStart, rowEnd, colStart, colEnd);
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getSize () {
        return new IntSize(width, height, originalImageWidth, originalImageHeight);
    }

    /**
     * Sets a pixel in  the DataBuffer using an int array of samples for input.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param address
     * @param iArray  The input samples in an int array.
     * @throws NullPointerException           if iArray or data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates are
     *                                        not in bounds, or if iArray is too small to hold the input.
     */
    @Override
    public
    void setPixel ( IAddress <A> address, int[] iArray ) {
        super.setPixel(address, iArray);
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

    /**
     * @return
     */
    @Override
    public
    MatOfInt getMat () {
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
     * @param imageTransforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <A, ?>> imageTransforms ) throws ValueError {
        this.transforms = new ArrayList <>(imageTransforms);
    }

    /**
     * @return
     */
    @Override
    public
    List <IImage <A>> getComponents () {
        return null;
    }

    @Override
    public
    int get ( IAddress <A> address, int[] data ) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    boolean isSquare () {
        return IImageBlock.super.isSquare();
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
     * @param rectangle
     * @return
     */
    @Override
    public
    IImageBlock <A> getSubImage ( Rectangle rectangle ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock <A> getSubImage () {
        return new ImageBlock <>(actualImage);
    }

    /**
     * @param meanPixelValue
     */
    @Override
    public
    void setMeanPixelValue ( int meanPixelValue ) {
        this.meanPixelValue = meanPixelValue;
    }

    /**
     * @param x
     * @param i
     */
    @Override
    public
    void put ( int x, int i ) {

    }

    /**
     * @return
     */
    @Override
    public
    int getMeanPixelValue () {
        return meanPixelValue;
    }

    @Override
    public
    int compareTo ( IIntSize minRangeSize ) {
        return 0;
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
        return getWidth() * getHeight() - o.getHeight() * o.getWidth();
    }
}
