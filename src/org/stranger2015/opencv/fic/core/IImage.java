package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.core.geom.Polygon;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.opencv.core.CvType.CV_8U;
import static org.opencv.highgui.HighGui.imshow;
import static org.stranger2015.opencv.fic.core.IAddress.valueOf;

/**
 *
 */
public
interface IImage<A extends IAddress <A>>
        extends Comparable <IImage <A>>,
                IShape {

    /**
     * @return
     */
    IShape getShape ();

    /**
     * @return
     */
    default
    Mat createMask ( IIntSize bb, List<Polygon > polygonList ) {
        Mat mMask = Mat.zeros(bb.toSize(), CV_8U);
        fillPoly(mMask, polygonList, new Scalar(0));
        imshow("mMask", mMask);

        return mMask;
    }

    private static
    void fillPoly ( Mat mMask, List <Polygon > polygonList, Scalar scalar ){
        Imgproc.fillPoly(mMask, polygonListToMatList(polygonList), scalar);
    }

    static
    List <MatOfPoint> polygonListToMatList ( List <Polygon> polygonList ) {
        List <MatOfPoint> matOfPoints = new ArrayList <>(polygonList.size());
        for (Polygon polygon : polygonList) {
            Point[] apply = apply(polygon);
            MatOfPoint matOfPoint = new MatOfPoint(apply);
            matOfPoints.add(matOfPoint);
        }
        return matOfPoints;
    }

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
    void setBeta ( double beta);

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
     * @param address
     * @param pixel
     */
    int putPixel ( IAddress <A> address, int[] pixel ) throws ValueError;

    /**
     * @param address
     * @param pixels
     */
    default
    void putPixels ( IAddress <A> address, int[][] pixels ) throws ValueError {
        for (int[] pixel : pixels) {
            putPixel(address, pixel);
        }
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
    default
    void setPixel ( IAddress <A> address, int[] iArray ) {
        IntStream.range(0, getNumBands()).forEach(i -> setSample(address, i, iArray[i]));
    }

    /**
     * @return
     */
    default
    int getNumBands () {
        return getMat().channels();
    }

    /**
     * Returns the samples for a specified band for the specified rectangle
     * of pixels in an int array, one sample per array element.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param sideSize The width of the pixel square.
     * @param b        The band to return.
     * @param iArray   If non-null, returns the samples in this array.
     * @return the samples for the specified band for the specified region of pixels.
     * @throws NullPointerException           if data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates or
     *                                        the band index are not in bounds, or if iArray is too small to
     *                                        hold the output.
     */
    default
    int[] getSamples ( IAddress <A> address, int sideSize, int dummy, int b, int[] iArray ) throws ValueError {
        int[] pixels;
        int offset = 0;

        pixels = iArray != null ? iArray : new int[pixelAmount()];

        for (int addr = 0; addr < pixelAmount(); addr++) {
            pixels[offset++] = getSample(valueOf(addr), b);
        }

        return pixels;
    }

    /**
     * Sets a sample in the specified band for the pixel located at (x,y)
     * in the DataBuffer using an int for input.
     * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
     * not in bounds.
     *
     * @param b The band to set.
     * @param s The input sample as an int.
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
     * @param b The band to return.
     * @return the sample in a specified band for the specified pixel.
     * @throws NullPointerException           if data is null.
     * @throws ArrayIndexOutOfBoundsException if the coordinates or
     *                                        the band index are not in bounds.
     */
    int getSample ( IAddress <A> address, int b );

    /**
     * @return
     */
    IAddress <A> getAddress ();

    /**
     * @return
     */
    int pixelAmount ();

    /**
     * @param address
     */
//    @Override
    void setAddress ( IAddress <A> address );

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
     * @param x
     * @param y
     */
    void setAddress ( int x, int y ) throws ValueError;

    /**
     * @return
     */
    List <IImage <A>> split ();

    /**
     * @return
     */
    IImage <A> merge ( List <IImage <A>> layers, IImage <A> inputImage );

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
    MatOfInt getMat ();

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    IImage <A> getSubImage ( int x, int y, int width, int height ) throws ValueError;

    /**
     * @return
     */
//    default
//    IImage <A> getSubImage ( Rectangle rectangle ) throws ValueError {
//        return getSubImage(rectangle.address.x, rectangle.y, rectangle.width, rectangle.height);
//    }

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
    double[] getPixels () throws ValueError;

    /**
     * @return
     */
    boolean isGrayScale ();

    /**
     * @return
     */
    List <IImage <A>> getComponents ();

    /**
     * @param address
     * @param data
     * @return
     */
    int get ( IAddress <A> address, int[] data );

    /**
     * @return
     */
    List <RegionOfInterest <A>> getRegions ();

    /**
     * @param regions
     */
    void setRegions ( List <RegionOfInterest <A>> regions );

    /**
     * @param addr
     * @return
     */
    int[] get ( int addr );

    /**
     * @param addr
     * @return
     */
    double[] getPixels ( IAddress <A> addr );

    /**
     * @param addr
     * @param i
     * @return
     */
    int pixelValues ( int addr, int i );//fixme

    /**
     * @param x
     * @param i
     */
//    @Override
    void put ( int x, int i );

    /**
     * @return
     */
//    @Override
    int getMeanPixelValue ();

    //    @Override
//    int compareTo ( IIntSize minRangeSize );

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
    IImageBlock <A> getSubImage ();

    int getOriginalImageHeight ();

    int getOriginalImageWidth ();

    /**
     * @param meanPixelValue
     */
    void setMeanPixelValue ( int meanPixelValue );

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
    int compareTo ( @NotNull IImage <A> other );

    private
    Point[] apply ( Polygon polygon ) {
        return getVertices();
    }
}
