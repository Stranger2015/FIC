package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.codec.classifiers.IClassifiable;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 * @param
 */
public
interface IImageBlock extends IImage {

    /**
     * @return
     */
    Geometry <?> getGeometry ();

    /**
     * @return
     */
    default
    int pixelAmount () {
        return getWidth() * getHeight();
    }

    /**
     * @param n
     * @return
     */
    @Override
    int plus ( int... n );

    @Override
    default
    Mat createMask ( IIntSize bb, List <Geometry <?>> polygonList ) {
        return IImage.super.createMask(bb, polygonList);
    }

    /**
     * @return
     */
    Mat subImage ( int rowStart, int rowEnd, int colStart, int colEnd );

    /**
     *
     */
    @Override
    default
    void release () {

    }

    /**
     * @return
     */
    @Override
    default
    String dump () {
        return null;
    }

    /**
     *
     */
    @Override
    default
    void initialize () {

    }

    /**
     * @return
     */
    @Override
    default
    EColorType getColorType () {
        return null;
    }

    /**
     * @return
     */
    @Override
    default
    int cols () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    default
    int rows () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    default
    int getWidth () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    default
    int getHeight () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    default
    double getBeta () {
        return 0;
    }

    /**
     * @param beta
     */
    @Override
    default
    void setBeta ( double beta ) {

    }

    IIntSize getOriginalSize ();

    /**
     * @return
     */
    IIntSize getSize ();

    /**
     * @param contractivity
     */
    @Override
    default
    IImage contract ( int contractivity ) {
        return null;
    }

    /**
     * @param scale
     * @return
     */
    @Override
    default
    IImage resize ( int scale ) {
        return null;
    }

    /**
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @return
     */
    @Override
    default
    Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    default
    Coordinate[] getCoordinates () {
        return new Coordinate[0];
    }

    /**
     * @return
     */
    @Override
    default
    Coordinate getCentroid () {
        return null;
    }

    /**
     * @return
     */
    @Override
    default
    int getBoundaryDimension () {
        return 0;
    }

    /**
     * @return
     */
    default
    Mat getMat () {
        return (Mat) this;
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    @Override
    default
    IImageBlock getSubImage ( int x, int y, int width, int height ) throws ValueError {
        return null;
    }

    /**
     * @param i
     * @param i1
     * @param sideSize
     * @param height
     * @param img1pixels
     * @param i2
     * @param i3
     */
    void getRGB ( int i, int i1, int sideSize, int height, double[] img1pixels, int i2, int i3 );

    /**
     * @return
     */
    @Override
    default
    List <ImageTransform> getTransforms () throws ValueError {
        return null;
    }

    /**
     * @param transforms
     */
    @Override
    default
    void setTransforms ( List <ImageTransform> transforms ) throws ValueError {

    }

    /**
     * @return
     */
    @Override
    default
    boolean isGrayScale () {
        return false;
    }

    /**
     * @return
     */
    @Override
    default
    List <IImage> getComponents () {
        return null;
    }

    /**
     * @param pixelData
     */
    @Override
    default
    void putPixels ( double[] pixelData ) {

    }

    /**
     * @return
     */
    @Override
    default
    double[] getPixels () {
        return new double[0];
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
     * @param rectangle
     * @return
     */
    @Override
    default
    IImageBlock getSubImage ( Rectangle rectangle ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    default
    IImageBlock getSubImage () throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    default
    int getOriginalImageHeight () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    default
    int getOriginalImageWidth () {
        return 0;
    }

    /**
     * @param meanPixelValue
     */
    @Override
    default
    void setMeanPixelValue ( double meanPixelValue ) {

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
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    default
    int compareTo ( @NotNull IImage other ) {
        return 0;
    }

    /**
     * @param blocks
     */
    @Override
    default
    void setRegions ( List <IImageBlock> blocks ) {

    }

    /**
     * @return
     */
    @Override
    default
    List <IImageBlock> getRegions () {
        return null;
    }

    /**
     * @param row
     * @param col
     * @return
     * @throws ValueError
     */
    @Override
    default
    IAddress getAddress ( int row, int col ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    default
    EAddressKind getAddressKind () {
        return null;
    }

    /**
     * @return
     */
    @Override
    default
    int getChannelsAmount () {
        return 0;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    @Override
    default
    double pixelValues ( int x, int y ) {
        return 0;
    }

//    /**
//     * @param x
//     * @param y
//     * @param c
//     * @return
//     */
//    @Override
//    default
//    double getPixelValuesLayer ( int x, int y, int c ) {
//        return 0;
//    }

    /**
     * @param originalImageWidth
     */
    @Override
    default
    void setOriginalImageWidth ( int originalImageWidth ) {
    }

    /**
     * @param w
     * @param h
     * @param originalImageWidth
     * @param originalImageHeight
     * @return
     */
    @Override
    default
    IIntSize restoreSize ( int w, int h, int originalImageWidth, int originalImageHeight ) {
        return null;
    }

    /**
     * @param x
     * @param y
     * @param data
     * @return
     */
    @Override
    default
    int get ( int x, int y, double[] data ) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    List <IChannel> getChannels ();

    /**
     * @param v
     */
    @Override
    default
    void setMeanPixelValues ( double v ) {

    }

    /**
     * @param x
     * @param i
     */
    double[] put ( int x, int y, double[] data );

    /**
     * @return
     */
    @Override
    double getMeanPixelValue ();

    double[] get ( int x, int y );

    /**
     * @param minRangeSize
     * @return
     */
    int compareTo ( IIntSize minRangeSize );

    /**
     * @return
     */
    int getX ();

    /**
     * @return
     */
    int getY ();

    /**
     * @return
     */
    IClassifiable getClassifiable ();

    /**
     * @return
     */
    boolean isOverlapping ();

    /**
     * @return
     */
    int getUsageCount();

    /**
     * @return
     */
    int incUsageCount();

   }
