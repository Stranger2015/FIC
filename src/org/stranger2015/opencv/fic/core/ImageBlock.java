package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.geom.*;
import org.stranger2015.opencv.fic.core.geom.impl.CoordinateArraySequence;
import org.stranger2015.opencv.fic.transform.ImageTransform;

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
//    public int width;
//    public int height;

    public double beta;
//    public int meanPixelValue;

    /**
     *
     */
    public IIntSize blockSize;
    //    private int x;
    //  private int y;
    //private int originalImageWidth;
    //private int originalImageHeight;
    private Geometry <?> geometry;
    private IImage <A> boundary;

    /**
     * @param kind
     * @param image
     * @param x
     * @param y
     * @param sideSize
     * @param geometry
     * @throws ValueError
     */
    public
    ImageBlock ( IImage <A> image, int x, int y, int sideSize, Geometry <?> geometry ) throws ValueError {
//        super(image.getMat(), x, y);

        blockSize = new IntSize(sideSize, sideSize);
        this.geometry = geometry;
    }

    /**
     * @param i1
     * @param j
     * @param vertices
     * @param inputImage
     * @param blockSize
     * @param geometry
     */
    public
    ImageBlock ( IImage <A> inputImage, int x, int y, IIntSize blockSize, Geometry <?> geometry ) {
        this((MatOfInt) inputImage.getMat(), blockSize, geometry);
    }

    /**
     * @param submat
     * @param blockSize
     * @param geometry
     */
    public
    ImageBlock ( MatOfInt submat, IIntSize blockSize, Geometry <?> geometry ) {
        super(submat, blockSize);
        this.geometry = geometry;
    }

    /**
     * @param image
     * @param rows
     * @param cols
     * @param pixels
     * @param geometry
     */
    public
    ImageBlock ( MatOfInt image, int rows, int cols, double[] pixels, Geometry <?> geometry ) throws ValueError {
        super(image, rows, cols, pixels);
        this.geometry = geometry;
    }

    /**
     * @param submat
     * @param geometry
     */
    public
    ImageBlock ( Mat submat, Geometry <?> geometry ) {
        super(actualImage, submat, (IIntSize) submat.size());
        this.geometry = geometry;
    }

    /**
     * @param image
     * @param blockSize
     * @param address
     * @param geometry
     */
//    public
//    ImageBlock ( IImage <A> image, IIntSize blockSize, IAddress <A> address ) {
//        this(image, blockSize, address);
//    }
//
//    /**
//     * @param image
//     * @param p
//     * @param address
//     * @param cols
//     * @param pixels
//     */
//    public
//    ImageBlock ( MatOfInt image, Point[] p, IAddress <A> address, int cols, int[] pixels ) {
//        super(image, p, address, cols, pixels);
//    }
//    public
//    ImageBlock ( IImage <A> image, IIntSize blockSize, IAddress <A> address ) {
//        super(image, address, blockSize);
//    }
    public
    ImageBlock ( IImageBlock <A> roi, int i, int j, int blockWidth, int blockHeight, Geometry <?> geometry ) throws ValueError {
        super(roi, roi1, i, j, blockWidth, blockHeight);
        this.geometry = geometry;
    }

    public
    ImageBlock ( IImage <A> inputImage, int i, IIntSize size, int i1, int i2, Geometry <?> geometry ) throws ValueError {
        super(inputImage, IAddress.valueOf(i1, inputImage.getWidth(), i2), size);
        this.geometry = geometry;
    }

    /**
     * @param image
     * @param address
     * @param blockSize
     * @param geometry
     */
    public
    ImageBlock ( IImage <A> image, IAddress <A> address, IIntSize blockSize, Geometry <?> geometry ) {
        super(image, address.getX(), address.getY(), blockSize, originalImageWidth, originalImageHeight);
        this.geometry = geometry;
    }
//
//    public
//    ImageBlock ( IImage <A> image, int i, int i1, int i2, Geometry <?> geometry ) throws ValueError {
//        super(image, i, i1, i2);
//        this.geometry = geometry;
//    }

    /**
     * @param rangeBlock
     * @param i
     * @param j
     * @param blockWidth
     * @param blockHeight
     * @param geometry
     * @throws ValueError
     */
    public
    ImageBlock ( IImageBlock <A> rangeBlock, int i, int j, int blockWidth, int blockHeight, Geometry <?> geometry )
            throws ValueError {
        super(rangeBlock, i, j, blockWidth);
        this.geometry = geometry;
    }

    /**
     * @param mat
     * @param rectangle
     */
    public
    ImageBlock ( Mat mat, Rectangle rectangle ) {
        super((MatOfInt) mat, rectangle);
        geometry = new Polygon <>(new LinearRing(
                new CoordinateArraySequence(getCoordinates()),
                new LinearRing[]{},
                new GeometryFactory()));
    }

    /**
     * @param image
     * @param x
     * @param y
     * @param width
     * @param height
     * @throws ValueError
     */
    public
    ImageBlock ( IImage <A> image, int x, int y, int width, int height ) throws ValueError {
        super(image, x, y, width,height);
    }

    /**
     * @param key
     * @throws ValueError
     */
    public
    ImageBlock ( Mat key ) throws ValueError {
        super(key,0,0, key.getWidth(), key.getHeight());
    }

    public
    ImageBlock ( MatOfInt mat, IAddress<A> address, IIntSize size ) {
        super(mat, address, size);
    }

    public
    ImageBlock ( int x, int y, int width, int height ) {

    }

    public
    <A extends IAddress <A>>
    ImageBlock ( IImageBlock<A> imageBlock, int rowStart, int rowEnd, int colStart, int colEnd, Geometry<?> geometry ) {


    }

    /**
     * @param bb
     * @param polygonList
     * @return
     */
    @Override
    public
    Mat createMask ( IIntSize bb, List <Geometry <?>> polygonList ) {
        return super.createMask(bb, polygonList);
    }

    /**
     * @return
     */
    @Override
    public
    int getWidth () {
        return getActualImage().width();
    }

    /**
     * @return
     */
    @Override
    public
    int getHeight () {
        return getActualImage().height();
    }

    /**
     * @param beta
     */
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
     * @throws ValueError
     */
    public
    boolean isHomogeneous () throws ValueError {
        return false;
    }

    /**
     * @return
     */
    public
    double area () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    Coordinate getCentroid () {
        return super.getCentroid();
    }

    //    @Override
    public
    boolean equalsExact ( Geometry <?> other, double tolerance ) {
        return false;
    }

    //    @Override
    public
    Point[] tVertices () {
        return new Point[0];
    }

    //    @Override
    public
    double perimeter () {
        return 0;
    }

    /**
     * @return
     */
//    @Override
    public
    Point[] getVertices () {
        return new Point[0];
    }

    @Override
    public
    Geometry <?> getGeometry () {
        return geometry;
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
     * @param n
     * @return
     */
    @Override
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
    public //fixme
    IIntSize getSize () {
        return new IntSize(0, 0, getOriginalImageWidth(), getOriginalImageHeight());
    }

    @Override
    public
    void setSample ( IAddress <A> address, int b, int s ) {

    }

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
    void getRGB ( int i, int i1, int sideSize, double[] img1pixels, int i2, int i3 ) {

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
    void getRGB ( int i, int i1, int sideSize, double[] img1pixels, int i2, int i3 ) {

    }

    @Override
    public
    List <ImageTransform <A, ?>> getTransforms () throws ValueError {
        return null;
    }

    /**
     * @param imageTransforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <A, ?>> imageTransforms ) throws ValueError {
        this.transforms = imageTransforms;
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
    void putPixels ( double[] pixelData ) {
        getMat().put(0, 0, pixelData);
    }

    @Override
    public
    double[] getMeanPixelValue () {
        return super.getMeanPixelValue();
    }

    @Override
    public
    double[] getPixel ( IAddress <A> address ) {
        return get(address.getX(), address.getY());
    }

    public
    double[] put ( IAddress <A> address, double[] data ) {
        put(address.getX(), address.getY(), data);

        return data;
    }

    /**
     * @return
     */
    @Override
    public
    boolean isSquare () {
        return IImageBlock.super.isSquare();
    }

    @Override
    public
    double[] put ( int x, int y, double[] data ) {
        getMat().put(x, y, data);

        return data;
    }

    /**
     * @param address
     * @param j
     * @return
     */
//    @Override
    public
    double[] get ( int x, int y ) {
        return super.getPixel(x, y);
    }

    /**
     * @param rectangle
     * @return
     */
    @Override
    public
    IImageBlock <A> getSubImage ( Rectangle rectangle ) throws ValueError {
        return getSubImage(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock <A> getSubImage () {
        return new ImageBlock <>(getActualImage(), blockSize, geometry);
    }

    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    /**
     * @param meanPixelValue
     */
//    @Override
    public
    void setMeanPixelValue ( double[] meanPixelValue ) {
        this.meanPixelValue = meanPixelValue;
    }

    /**
     * @param x
     * @param i
     */
    //@Override
    public
    double[] put ( int address, double[] data ) {
        int x = getX(address);
        int y = getY(address);

        getActualImage().put(x, y, data);

        return data;
    }

    public
    int getY ( int address ) {
        return 0;
    }

    public
    int getX ( int address ) {
        return 0;
    }

    @Override
    public
    int compareTo ( IIntSize minRangeSize ) {
        return 0;
    }

    @Override
    public
    int getX () {
        return 0;
    }

    @Override
    public
    int getY () {
        return 0;
    }

    /**
     * @param iImageBlocks
     * @param blocksToMerge
     * @return
     */
    @Override
    public
    A merge ( List <IImageBlock <A>> iImageBlocks, List <IImageBlock <A>> blocksToMerge ) {
        return null;
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

    @Override
    public
    void setRegions ( List <IImageBlock <A>> blocks ) {

    }
}
