package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.codec.ICompressedImage;
import org.stranger2015.opencv.fic.core.geom.CoordinateSequenceComparator;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public
class CompressedImage<A extends IAddress <A>>
        extends Image <A>
        implements ICompressedImage <A>,
                   IIntSize {

    public int originalImageWidth;
    public int originalImageHeight;

    private List <ImageTransform <A, ?>> transforms = new ArrayList <>(16);//fixme;

    /**
     * @param rows
     * @param cols
     * @param type
     */
    public
    CompressedImage ( IImage <A> image, int rows, int cols, int type ) throws ValueError {
        super(image, rows, cols, type);
    }

    /**
     * @param inputImage
     */
    public
    CompressedImage ( IImage <A> inputImage ) {
        super(inputImage, size);
    }

    public
    CompressedImage ( Mat dest ) {
        super(dest, size);
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <A, ?>> getTransforms () {
        return transforms;
    }

    /**
     * @param transforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <A, ?>> transforms ) {
        this.transforms = transforms;
    }

    /**
     * @return
     */
    @Override
    public
    int[] getPixels () {
        return new int[0];
    }

    /**
     * @param rectangle
     * @return
     */
    @Override
    public
    IImageBlock <A> getSubImage ( Rectangle rectangle ) throws ValueError {
        return getSubImage(rectangle.getAddress(), rectangle.width, rectangle.height);
    }

    private
    IImageBlock <A> getSubImage ( IAddress <?> address, int width, int height ) throws ValueError {
        Mat mat = getMat().submat((Rect) address.getCartesianCoordinates(address.radix()));

        return new ImageBlock <>(mat);
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    /**
     * @param meanPixelValue
     */
    @Override
    public
    void setMeanPixelValue ( int meanPixelValue ) {

    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    @Override
    public
    int compareTo ( @NotNull IIntSize o ) {
        return -1;
    }

    /**
     * @param address
     * @param pixel
     */
//    @Override
    public
    void putPixel ( IAddress <A> address, int pixel ) {

    }

    /**
     * @param i
     * @param i1
     * @param width
     * @param height
     * @param img1pixels
     * @param i2
     * @param i3
     */
    @Override
    public
    void getRGB ( int i, int i1, int width, int height, int[] img1pixels, int i2, int i3 ) {

    }

    /**
     * @param address
     * @param pixels
     */
    @Override
    public
    int putPixel ( IAddress <A> address, int[] pixels ) {
        return getMat().put((int) address.getIndex(), 0, pixels[0]);
    }

    //    @Override
    int compareTo () {
        return -1;//compareTo(null);
    }

//    @Override
//    public
//    int compareTo ( @NotNull Object o ) {
//        return 0;
//    }

    @Override
    protected
    int compareToSameClass ( Geometry o ) {
        return 0;
    }

    @Override
    protected
    int compareToSameClass ( Geometry o, CoordinateSequenceComparator comp ) {
        return 0;
    }
}
