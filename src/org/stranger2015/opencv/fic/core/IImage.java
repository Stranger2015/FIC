package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
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
interface IImage<A extends IAddress <A>> {

    void initialize ();

    /**
     * @return
     */

    /**
     * @return
     */
    default
    Mat createMask ( IIntSize bb, List <Geometry<?>> polygonList ) {
        Mat mMask = Mat.zeros(bb.toSize(), CV_8U);//cv_8u
        fillPoly(mMask, polygonList, new Scalar(0));
        imshow("mMask", mMask);

        return mMask;
    }

    private static
    void fillPoly ( Mat mMask, List <Geometry<?>> polygonList, Scalar scalar ) {
        Imgproc.fillPoly(mMask, polygonListToMatList(polygonList), scalar);
    }

    /**
     * @param polygonList
     * @return
     */
    static @NotNull
    List <MatOfPoint> polygonListToMatList ( List <Geometry<?>> polygonList ) {
        List <MatOfPoint> matOfPoints = new ArrayList <>(polygonList.size());
        for (Geometry<?> polygon : polygonList) {
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
    IImage <A> contract ( int contractivity );

    /**
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @return
     */
    Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd );

    /**
     * @param n
     * @return
     */
//    @Override
    int plus ( int... n );

    /**
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @return
     */
//    @Override
    IImageBlock <A> subImage ( int rowStart, int rowEnd, int colStart, int colEnd );

    /**
     * @return
     */
    List <IImage <A>> split ();

    /**
     * @return
     */
    IImage <A> merge ( List <IImage <A>> layers, IImage <A> inputImage );//imageblocks to merge

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
    IImageBlock <A> getSubImage ( int x, int y, int width, int height ) throws ValueError;

    /**
     * @param i
     * @param i1
     * @param sideSize
     * @param img1pixels
     * @param i2
     * @param i3
     */
    void getRGB ( int i, int i1, int sideSize, double[] img1pixels, int i2, int i3 );

    /**
     * @return
     */
    List <ImageTransform <A, ?>> getTransforms () throws ValueError;

    /**
     * @param transforms
     */
    void setTransforms ( List <ImageTransform <A, ?>> transforms ) throws ValueError;

    /**
     * @return
     */
    boolean isGrayScale ();

    /**
     * @return
     */
    List <IImage <A>> getComponents ();

    /**
     * @param addr
     * @param y
     * @param data
     * @return
     */
    default
    double[] getPixel ( int x, int y) {
//        int channels=getMat().channels();
//        int stride=getMat().width()*channels;
//        for (int i=0; i< getMat().height(); i++) {
//         stride is the number of bytes in a row of smallImg
//        for (int j=0; j<stride; j+=channels)        {
//            int b = buff[(i * stride) + j];
//            int g = buff[(i * stride) + j + 1];
//            int r = buff[(i * stride) + j + 2];
//            float[] hsv = new float[3];
//            Color.RGBtoHSB(r,g,b,hsv);
        // Do something with the hsv.
//            System.out.println("hsv: " + hsv[0]);
//        }
//    }

        return getMat().get(x,y);
    }

//    /**
//     *
//     * @return
//     */

//    int[][] getAddressTable ();
//
//    default
//    int[][] createAddressTable () {
//
//        int[][] table = new int[rows()][cols()];
//
//        System.out.printf("mat %d x %d", rows(), cols());
//
//        final int maxAddress = cols() * rows();
//
//        for (int i = 0, addressNo = 0; i < rows(); i++) {
//            System.out.printf("row: %d\n", i);
//            for (int j = 0; j < cols() && addressNo < maxAddress; j++, addressNo++) {
//                System.out.printf("\tcol: %d, address: %d\n", j, addressNo);
//                table[i][j] = addressNo;
//            }
//        }
//        getLogger()printf("Address table: %s", Arrays.toString(table));
//
//        return table;
//    }
//

    /**
     * @param addr
     */
    void putPixel ( IAddress <A> address, double[] pixelData ) throws ValueError;

    void putPixels ( double[] pixelData );

    // assuming it's of CV_8UC3 == BGR, 3 byte/pixel
    // Effectively assuming channels = 3
//        for (int i=0; i< height; i++)
//    {
//         stride is the number of bytes in a row of smallImg
//        int stride = channels * width;
//        for (int j=0; j<stride; j+=channels)  {
//            int b = buff[(i * stride) + j];
//            int g = buff[(i * stride) + j + 1];
//            int r = buff[(i * stride) + j + 2];
//            float[] hsv = new float[3];
//            Color.RGBtoHSV(r,g,b,hsv);
//             Do something with the hsv .
//            System.out.println("hsv: " + hsv[0]);
//        }
//    }
//    ========================================================================================
//Mat A = Highgui.imread(image_addr); \\"image_addr" is the address of the image
//    Mat C = A.clone();
//A.convertTo(A, CvType.CV_64FC3); \\New line added.
//    int size = (int) (A.total() * A.channels());
//    double[] temp = new double[size]; \\ use double[] instead of byte[]
//A.get(0, 0, temp);
//for (int i = 0; i &lt; size; i++)
//    temp[i] = (temp[i] / 2);  \\ no more casting required.
//            C.put(0, 0, temp);
//====================================================================
//Mat A = Highgui.imread(image_addr); \\"image_addr" is the address of the image
//    Mat C = A.clone();
//    int size = (int) (A.total() * A.channels());
//    byte[] temp = new byte[size];
//A.get(0, 0, temp);
//for (int i = 0; i < size; i++)
//    temp[i] = (byte) (temp[i] / 2);
//C.put(0, 0, temp);
    double[] getPixels ();

    /**
     * @return
     */
    double[] getMeanPixelValue ();

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
    IImageBlock <A> getSubImage ( Rectangle rectangle ) throws ValueError;

    /**
     * @return
     */
    IImageBlock <A> getSubImage () throws ValueError;

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
    void setMeanPixelValue ( double[] meanPixelValue );

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
    int compareTo ( @NotNull IImage <A> other );

    /**
     * @param blocks
     */
    void setRegions ( List <RegionOfInterest <A>> blocks );

    /**
     * @return
     */
    List<RegionOfInterest<A>> getRegions ();

    /**
     * @param row
     * @param col
     * @return
     * @throws ValueError
     */
    IAddress<A>  getAddress (int row, int col) throws ValueError;

    /**
     * @return
     */
    EAddressKind getAddressKind();

    /**
     * @return
     */
    int getChannelsAmount();

    /**
     * @param x
     * @param y
     * @return
     */
    double pixelValues ( int x, int y );

    /**
     * @param x
     * @param y
     * @param ch
     * @return
     */
    double getPixelValuesLayer ( int x, int y, int ch );

    void setMeanPixelValuesLayer ( int c, double v );
}
