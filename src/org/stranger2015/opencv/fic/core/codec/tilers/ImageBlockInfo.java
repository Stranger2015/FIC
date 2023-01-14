package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.classifiers.IClassifiable;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.AbstractMap.SimpleImmutableEntry;

/**
 *
 */
public
class ImageBlockInfo implements IImageBlock, IClassifiable {
    private int index;//id from 1..24 ** 4
    public static final int SIZES = 3;
    public static final int LEVEL1_CLASSES_AMOUNT = SIZES * 8;
    public static final int SUCCESSOR_AMOUNT = 4;
    public static final int LEVEL2_CLASSES_AMOUNT = (int) Math.pow(LEVEL1_CLASSES_AMOUNT, SUCCESSOR_AMOUNT);

    protected Map <IIntSize, SrImageBlockInfo[]> sizeToLevel1Map = Map.ofEntries(
            new SimpleImmutableEntry <>(
                    new IntSize(4, 4),
                    new SrImageBlockInfo[LEVEL2_CLASSES_AMOUNT]),
            new SimpleImmutableEntry <>(
                    new IntSize(8, 8),
                    new SrImageBlockInfo[LEVEL2_CLASSES_AMOUNT]),
            new SimpleImmutableEntry <>(
                    new IntSize(16, 16),
                    new SrImageBlockInfo[LEVEL2_CLASSES_AMOUNT])
    );

    final transient private EColorType colorType;

    /**
     * @return
     */
    public
    Map <IIntSize, SrImageBlockInfo[]> getSizeToLevel1Map () {
        return sizeToLevel1Map;
    }

    @Override
    public
    IIntSize getOriginalSize () {
        return null;
    }

    private double pixelSum;
    final transient private IImageBlock imageBlock;
    private final ImageBlockInfo[] infos = new ImageBlockInfo[SUCCESSOR_AMOUNT];
    private final List <IChannel> channels = new ArrayList <>(SUCCESSOR_AMOUNT);

    /**
     *
     */
    @Contract(pure = true)
    public
    ImageBlockInfo ( EColorType colorType, IImageBlock block ) throws ValueError, IOException {
        this.colorType = colorType;
        imageBlock = block;
        int x = 0;
        int y = 0;
        int halfW = imageBlock.getWidth() / 2;
        int halfH = imageBlock.getHeight() / 2;

        IImageBlock q0 = block.getSubImage(x, y, halfW, halfH);
        IImageBlock q1 = block.getSubImage(x + halfW, y, halfW, halfH);
        IImageBlock q2 = block.getSubImage(x, y + halfH, halfW, halfH);
        IImageBlock q3 = block.getSubImage(x + halfW, y + halfH, halfW, halfH);

        pixelSum = infos[0].setPixelSum(sumPixelIntensities(q0));
        pixelSum += infos[1].setPixelSum(sumPixelIntensities(q1));
        pixelSum += infos[2].setPixelSum(sumPixelIntensities(q2));
        pixelSum += infos[3].setPixelSum(sumPixelIntensities(q3));
    }

    /**
     * @param block
     * @return
     */
    private
    double sumPixelIntensities ( IImageBlock block ) {
        int channels = imageBlock.getChannelsAmount();

        for (int channelIndex = 0; channelIndex < channels; channelIndex++) {//fixme
            double[] imgData = new double[Math.toIntExact(imageBlock.getMat().total() * channels)];
            imageBlock.get(0, 0, imgData);
            pixelSum = 0;
            for (int x = 0; x < imageBlock.getWidth(); x++) {
                for (int y = 0; y < imageBlock.getHeight(); y++) {
                    pixelSum += imgData[y * block.cols() + x];
                }
            }
        }

        return pixelSum;
    }

    /**
     * @return
     */
    public
    int getIndex () {
        return index;
    }

    /**
     * @return
     */
    public
    double getPixelSum () {
        return pixelSum;
    }

    /**
     * @param pixelSum
     */
    public
    double setPixelSum ( double pixelSum ) {
        return this.pixelSum = pixelSum;
    }

    /**
     * @param index
     */
    public
    void setIndex ( int index ) {
        this.index = index;
    }

    /**
     * @return
     */
    public
    IImageBlock getImageBlock () {
        return imageBlock;
    }

    public
    ImageBlockInfo[] getInfos () {
        return infos;
    }

    /**
     *
     */
    @Override
    public
    void initialize () {

    }

    /**
     * @return
     */
    @Override
    public
    EColorType getColorType () {
        return colorType;
    }

    /**
     * @return
     */
    @Override
    public
    int cols () {
        return getWidth();
    }

    /**
     * @return
     */
    @Override
    public
    int rows () {
        return getHeight();
    }

    /**
     * @return
     */
    @Override
    public
    int getWidth () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int getHeight () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    double getBeta () {
        return 0;
    }

    /**
     * @param beta
     */
    @Override
    public
    void setBeta ( double beta ) {

    }

    /**
     * @param contractivity
     */
    @Override
    public
    IImage contract ( int contractivity ) {
        return null;
    }

    /**
     * @param scale
     * @return
     */
    @Override
    public
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
    public
    Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    Coordinate[] getCoordinates () {
        return new Coordinate[0];
    }

    /**
     * @return
     */
    @Override
    public
    Coordinate getCentroid () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getBoundaryDimension () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    List <Mat> split ( Mat m, List <Mat> mv ) {
        Core.split(m, mv);
        return mv;
    }

    /**
     * @param layers
     * @param inputImage
     * @return
     */
    @Override
    public
    Mat merge ( List <Mat> mv, Mat inputImage ) {
        Core.merge(mv, inputImage);
        return inputImage;
    }

    /**
     *
     */
    @Override
    public
    void release () {

    }

    /**
     * @return
     */
    @Override
    public
    String dump () {
        return null;
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    @Override
    public
    IImageBlock getSubImage ( int x, int y, int width, int height ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform> getTransforms () throws ValueError {
        return null;
    }

    /**
     * @param transforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform> transforms ) throws ValueError {

    }

    /**
     * @return
     */
    @Override
    public
    boolean isGrayScale () {
        return false;
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

    }

    /**
     * @return
     */
    @Override
    public
    double[] getPixels () {
        return new double[0];
    }

    /**
     * @param rectangle
     * @return
     */
    @Override
    public
    IImageBlock getSubImage ( Rectangle rectangle ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IImageBlock getSubImage () throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageWidth () {
        return 0;
    }

    /**
     * @param meanPixelValue
     */
    @Override
    public
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
    public
    int compareTo ( @NotNull IImage other ) {
        return 0;
    }

    /**
     * @param blocks
     */
    @Override
    public
    void setRegions ( List <IImageBlock> blocks ) {

    }

    /**
     * @return
     */
    @Override
    public
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
    public
    IAddress getAddress ( int row, int col ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    EAddressKind getAddressKind () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getChannelsAmount () {
        return 0;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    @Override
    public
    double pixelValues ( int x, int y ) {
        return 0;
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
        return null;
    }

    /**
     * @param x
     * @param y
     * @param data
     * @return
     */
    @Override
    public
    int get ( int x, int y, double[] data ) {
        return 0;
    }

    public
    List <IChannel> getChannels () {
        return channels;
    }

    /**
     * @param v
     */
    @Override
    public
    void setMeanPixelValues ( double v ) {

    }

    /**
     * @return
     */
    @Override
    public
    Geometry <?> getGeometry () {
        return imageBlock.getGeometry();
    }

    /**
     * @return
     */
    @Override
    public
    int pixelAmount () {
        return IImageBlock.super.pixelAmount();
    }

    /**
     * @param n
     * @return
     */
    @Override
    public
    int plus ( int... n ) {
        return 0;
    }

    /**
     * @param bb
     * @param polygonList
     * @return
     */
    @Override
    public
    Mat createMask ( IIntSize bb, List <Geometry <?>> polygonList ) {
        return IImageBlock.super.createMask(bb, polygonList);
    }

    /**
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @return
     */
    @Override
    public
    Mat subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return submat(rowStart, colStart, rowEnd, colEnd);
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getSize () {
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
    @Override
    public
    void getRGB ( int i, int i1, int sideSize, int height, double[] img1pixels, int i2, int i3 ) {

    }

    /**
     * @param x
     * @param y
     * @param data
     */
    @Override
    public
    double[] put ( int x, int y, double[] data ) {
        return new double[0];
    }

    /**
     * @return
     */
    @Override
    public
    double getMeanPixelValue () {
        return 0;
    }

    /**
     * @param x
     * @param y
     * @return
     */
    @Override
    public
    double[] get ( int x, int y ) {
        return new double[0];
    }

    /**
     * @param minRangeSize
     * @return
     */
    @Override
    public
    int compareTo ( IIntSize minRangeSize ) {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int getX () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int getY () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    IClassifiable getClassifiable () {
        return this;
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
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int incUsageCount () {
        return 0;
    }
}
