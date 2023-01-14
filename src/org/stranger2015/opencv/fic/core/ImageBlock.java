package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.stranger2015.opencv.fic.core.codec.classifiers.IClassifiable;
import org.stranger2015.opencv.fic.core.codec.tilers.ImageBlocks;
import org.stranger2015.opencv.fic.core.geom.*;
import org.stranger2015.opencv.fic.core.geom.impl.CoordinateArraySequence;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class ImageBlock extends Image implements IImageBlock {

    private final List <IImageBlock> blocks = new ImageBlocks <>();
    private final IImage actualImage = this;
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
    private int originalImageHeight;
    private int originalImageWidth;
    private Geometry <?> geometry;
    private IImage boundary;
    transient protected IClassifiable classifiable;
    transient private int usageCount;

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
    ImageBlock ( IImage image, int x, int y, int sideSize, Point[] geometry ) throws ValueError {
        super((MatOfInt) image, x, y, sideSize, colorType);

        blockSize = new IntSize(sideSize, sideSize);
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
    ImageBlock ( IImage inputImage, int x, int y, IIntSize blockSize, Geometry <?> geometry ) {
        this((MatOfInt) inputImage.getMat(), blockSize, geometry);
    }

    /**
     * @param submat
     * @param blockSize
     * @param geometry
     */
    public
    ImageBlock ( MatOfInt submat, IIntSize blockSize, Geometry <?> geometry ) {
        super(submat, blockSize, colorType);
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
    ImageBlock ( MatOfInt image,
                 int rows,
                 int cols,
                 double[] pixels,
                 Geometry <?> geometry ) throws ValueError {

        super(image, rows, cols, pixels, colorType);
        this.geometry = geometry;
    }

    /**
     * @param submat
     * @param geometry
     */
    public
    ImageBlock ( Mat submat, Geometry <?> geometry ) {
        super(null, (IAddress) submat, (IIntSize) submat.size(), colorType);
        this.geometry = geometry;
    }

    public
    ImageBlock ( IImage inputImage, int i, IIntSize size, int i1, int i2, Geometry <?> geometry ) throws ValueError {
        super((MatOfInt) inputImage, IAddress.valueOf(i1, inputImage.getWidth(), i2), size, colorType);
        this.geometry = geometry;
    }

    /**
     * @param image
     * @param address
     * @param blockSize
     * @param geometry
     */
    public
    ImageBlock ( IImage image, IAddress address, IIntSize blockSize, Geometry <?> geometry ) {
        super(image, address.getX(), address.getY(), blockSize, colorType);
        this.geometry = geometry;
    }

    /**
     * @param mat
     * @param rectangle
     */
    public
    ImageBlock ( Mat mat, Rectangle rectangle ) {
        super((MatOfInt) mat, rectangle, colorType);
        geometry = new Polygon <>(
                new LinearRing(
                        new CoordinateArraySequence(getCoordinates()),
                        new LinearRing[]{},
                        new GeometryFactory())
        );
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
    ImageBlock ( IImage image, int x, int y, int width, int height ) throws ValueError {
        super((MatOfInt) image.getMat(), x, y, width, colorType);
    }

    /**
     * @param key
     * @throws ValueError
     */
    public
    ImageBlock ( IImage key ) throws ValueError {
        super(key, 0, 0, key.getMat().width(), key.getMat().height(), colorType);
    }

    /**
     * @param mat
     * @param address
     * @param size
     */
    public
    ImageBlock ( MatOfInt mat, IAddress address, IIntSize size ) {
        super(mat, address, size, colorType);
    }

    /**
     * @param imageBlock
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @param geometry
     */
    public
    ImageBlock ( IImageBlock imageBlock,
                 int rowStart,
                 int rowEnd,
                 int colStart,
                 int colEnd,
                 Geometry <?> geometry ) {

        super(imageBlock, rowStart, rowEnd, colStart, colEnd, colorType);

        this.geometry = geometry;
    }

    public
    ImageBlock ( Mat dest ) {

        super((MatOfInt) mat, colorType);
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

    /**
     * @return
     */
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
    String toString () {
        return String.format(
                "ImageBlock{actualImage=%s, blockSize=%s, geometry=%s}",
                actualImage,
                blockSize,
                geometry);
    }

    /**
     * @return
     */
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
    Mat subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
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

    /**
     * @param address
     * @param b       The band to set.
     * @param s       The input sample as an int.
     */
    @Override
    public
    void setSample ( IAddress address, int b, int s ) {

    }

    /**
     * @param address
     * @param b       The band to return.
     * @return
     */
    @Override
    public
    int getSample ( IAddress address, int b ) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    Mat getMat () {
        return actualImage.getMat();
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
    @Override
    public
    void getRGB ( int i, int i1, int sideSize, int height, double[] img1pixels, int i2, int i3 ) {

    }

    @Override
    public
    List <ImageTransform> getTransforms () throws ValueError {
        return transforms;
    }

    /**
     * @param imageTransforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform> imageTransforms ) throws ValueError {
        this.transforms = imageTransforms;
    }

    /**
     * @return
     */
    @Override
    public
    List <IImage> getComponents () {
        return null;
    }

    /**
     * @param pixelData
     */
    @Override
    public
    void putPixels ( double[] pixelData ) {
        getMat().put(0, 0, pixelData);
    }

    /**
     * @return
     */
    @Override
    public
    double getMeanPixelValue () {
        return super.getMeanPixelValue();
    }

    /**
     * @param address
     * @return
     */
    @Override
    public
    double[] getPixel ( IAddress address ) {
        return get(address.getX(), address.getY());
    }

    /**
     * @param address
     * @param data
     * @return
     */
    @Override
    public
    double[] put ( IAddress address, double[] data ) {
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
    int put ( int x, int y, double[] data ) {
        getMat().put(x, y, data);

        return data;
    }

    /**
     * @param address
     * @param j
     * @return
     */
    @Override
    public
    double[] get ( int x, int y ) {
        return getPixel(x, y);
    }

    /**
     * @param rectangle
     * @return
     */
    @Override
    public
    IImageBlock getSubImage ( Rectangle rectangle ) throws ValueError {
        return getSubImage(
                rectangle.getX(),
                rectangle.getY(),
                rectangle.getWidth(),
                rectangle.getHeight());
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock getSubImage () {
        return new ImageBlock(getActualImage(), blockSize, geometry);
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    /**
     * @param meanPixelValue
     */
    @Override
    public
    void setMeanPixelValue ( double meanPixelValue ) {
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
    IImageBlock merge ( List <IImageBlock> iImageBlocks, List <IImageBlock> blocksToMerge ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IClassifiable getClassifiable () {
        return classifiable;
    }

    /**
     * @return
     */
    @Override
    public
    boolean isOverlapping () {
        return false;
    }

    /**
     * @return
     */
    @Override
    public
    int getUsageCount () {
        return usageCount;
    }

    /**
     * @return
     */
    @Override
    public
    int incUsageCount () {
        return ++usageCount;
    }

    /**
     * @param classifiable
     */
    public
    void setClassifiable ( IClassifiable classifiable ) {
        this.classifiable = classifiable;
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
        return getWidth() * getHeight() - o.getHeight() * o.getWidth();
    }

    @Override
    public
    void setRegions ( List <IImageBlock> blocks ) {

    }

    /**
     * @return
     */
    @Override
    public
    int getChannelsAmount () {
        return getMat().channels();
    }

    /**
     * @param x
     * @param y
     * @return
     */
    @Override
    public
    double pixelValues ( int x, int y ) {
        return new double[0];
    }

    /**
     * @param x
     * @param y
     * @param c
     * @return
     */
    @Override
    public
    double getPixelValuesLayer ( int x, int y, int c ) {
        return 0;
    }

    /**
     * @param c
     * @param v
     */
    @Override
    public
    void setMeanPixelValuesLayer ( int c, double v ) {

    }

    /**
     * @param originalImageWidth
     */
    @Override
    public
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
    public
    IIntSize restoreSize ( int w, int h, int originalImageWidth, int originalImageHeight ) {
//        setOriginalImageWidth(originalImageWidth);
//        setOriginalImageHeight(originalImageHeight);

        return new IntSize(w, h, originalImageWidth, originalImageHeight);
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    public
    void setOriginalImageHeight ( int originalImageHeight ) {
        this.originalImageHeight = originalImageHeight;
    }

    @Override
    public
    boolean equals ( Object o ) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImageBlock)) {
            return false;
        }

        ImageBlock that = (ImageBlock) o;

        if (!getActualImage().equals(that.getActualImage())) {
            return false;
        }
        if (!blockSize.equals(that.blockSize)) {
            return false;
        }
        if (!getGeometry().equals(that.getGeometry())) {
            return false;
        }
        throw new IllegalStateException();
    }

    @Override
    public
    int hashCode () {
        int result = getActualImage().hashCode();
        result = 31 * result + blockSize.hashCode();
        result = 31 * result + getGeometry().hashCode();
        result = 31 * result + getClassifiable().hashCode();
        result = 31 * result + getUsageCount();

        return result;
    }

}
