package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.CvType.CV_8U;
import static org.opencv.highgui.HighGui.imshow;

/**
 *
 */
public
interface IImage extends IChannel {
    /**
     *
     */
    void initialize ();

    EColorType getColorType();

    /**
     * @return
     */
    default
    Mat createMask ( IIntSize bb, List <Geometry <?>> polygonList ) {
        Mat mMask = Mat.zeros(bb.toSize(), CV_8U);//cv_8u
        fillPoly(mMask, polygonList, new Scalar(0));
        imshow("mMask", mMask);

        return mMask;
    }

    private static
    void fillPoly ( Mat mMask, List <Geometry <?>> polygonList, Scalar scalar ) {
        Imgproc.fillPoly(mMask, polygonListToMatList(polygonList), scalar);
    }

    /**
     * @param polygonList
     * @return
     */
    static @NotNull
    List <MatOfPoint> polygonListToMatList ( List <Geometry <?>> polygonList ) {
        List <MatOfPoint> matOfPoints = new ArrayList <>(polygonList.size());
        for (Geometry <?> polygon : polygonList) {
            Point[] apply = null;//apply(polygon);
            MatOfPoint matOfPoint = new MatOfPoint(apply);
            matOfPoints.add(matOfPoint);
        }

        return matOfPoints;
    }

    int cols ();

    int rows ();

    /**
     * @return
     */
    int getWidth ();

    /**
     * @return
     */
    int getHeight ();

    /**
     * @return
     */
    double getBeta ();

    /**
     * @param beta
     */
    void setBeta ( double beta );

    /**
     * @return
     */
    IIntSize getSize ();

    /**
     * @param contractivity
     */
    IImage contract ( int contractivity );

    /**
     * @param scale
     * @return
     */
    IImage resize ( int scale );

    /**
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @return
     */
    Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd );

    Coordinate[] getCoordinates ();

    Coordinate getCentroid ();

    int getBoundaryDimension ();

    /**
     * @param n
     * @return
     */
    int plus ( int... n );

    /**
     * @param layers
     * @param inputImage
     * @return
     */
    Mat merge ( List <Mat> channels, Mat inputImage );

    /**
     * @return
     */
    List <Mat> split ( Mat m, List <Mat> mv );

    /**
     *
     */
    void release ();

    /**
     * @return
     */
    String dump ();

    /**
     * @return
     */
    Mat getMat ();

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    IImageBlock getSubImage ( int x, int y, int width, int height ) throws ValueError;

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
    List <ImageTransform> getTransforms () throws ValueError;

    /**
     * @param transforms
     */
    void setTransforms ( List <ImageTransform> transforms ) throws ValueError;

    /**
     * @return
     */
    boolean isGrayScale ();

    /**
     * @return
     */
    List <IImage> getComponents ();

    /**
     * @param addr
     * @param y
     * @param data
     * @return
     */
    default
    double[] getPixel ( int x, int y ) {
        return getMat().get(x, y);
    }

    /**
     * @param addr
     */
    default
    void putPixel ( IAddress address, double[] pixelData ) throws ValueError {
        getMat().put(address.getX(), address.getY(), pixelData);
    }

    /**
     * @param x
     * @param y
     * @param pixelData
     * @throws ValueError
     */
    default
    void putPixel ( int x, int y, double[] pixelData ) throws ValueError {
        getMat().put(x, y, pixelData);
    }

    /**
     * @param pixelData
     */
    void putPixels ( double[] pixelData );

    /**
     * @return
     */
    double[] getPixels ();

    /**
     * @return
     */
    double getMeanPixelValue ();

    /**
     * @return
     */
    default
    boolean isSquare () {
        return getWidth() == getHeight();
    }

    /**
     * @return
     */
    IImageBlock getSubImage ( Rectangle rectangle ) throws ValueError;

    /**
     * @return
     */
    IImageBlock getSubImage () throws ValueError;

    /**
     * @return
     */
    int getOriginalImageHeight ();

    /**
     * @return
     */
    int getOriginalImageWidth ();

    /**
     * @param meanPixelValue
     */
    void setMeanPixelValue ( double meanPixelValue );

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
//    @Override
    int compareTo ( @NotNull IImage other );

    /**
     * @param blocks
     */
    void setRegions ( List <IImageBlock> blocks );

    /**
     * @return
     */
    List <IImageBlock> getRegions ();

    /**
     * @param row
     * @param col
     * @return
     * @throws ValueError
     */
    IAddress getAddress ( int row, int col ) throws ValueError;

    /**
     * @return
     */
    EAddressKind getAddressKind ();

    /**
     * @return
     */
    int getChannelsAmount ();

    /**
     * @param x
     * @param y
     * @return
     */
    double pixelValues ( int x, int y );

    double getPixelValuesLayer ( int x, int y, int c );

    /**
     * @param x
     * @param y
     * @param ch
     * @return
     */
//    double getPixelValuesLayer ( int x, int y, int c );

//    void setMeanPixelValuesLayer ( int c, double v );

    void setOriginalImageWidth ( int originalImageWidth );

    IIntSize restoreSize ( int w, int h, int originalImageWidth, int originalImageHeight );

    int get ( int x, int y, double[] data );

    List <IChannel> getChannels ();

    void setMeanPixelValues ( double v );
    /**
     *
     */
    enum EColorType {
        BINARY(0),
        GRAYSCALE(1),
        GRAYSCALE_WITH_ALPHA(4),
        INDEXED_COLOR(3),
        TRUE_COLOR(2),
        TRUE_COLOR_WITH_ALPHA(6);

        private final int cType;

        /**
         * @param cType
         */
        @Contract(pure = true)
        EColorType ( int cType ) {
            this.cType = cType;
        }

        /**
         * @return
         */
//        @Contract(pure = true)
        public
        int getCType () {
            return cType;
        }

    }
}
