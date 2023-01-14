package org.stranger2015.opencv.fic.core;

//import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Rect;
import org.opencv.core.Size;
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
class CompressedImage extends Image
        implements ICompressedImage {

    private List <ImageTransform> transforms = new ArrayList <>(16);//fixme;
    private int originalImageWidth;
    private int originalImageHeight;

    /**
     * @param rows
     * @param cols
     * @param type
     */
    public
    CompressedImage ( IImage image, int rows, int cols, int type ) throws ValueError {
        super((MatOfInt) image.getMat(), rows, cols, type, colorType);
    }

    /**
     * @param inputImage
     */
    public
    CompressedImage ( IImage inputImage ) {
        super((MatOfInt) inputImage.getMat(), inputImage.getSize(), colorType);

        inputImage.getOriginalImageWidth();
        inputImage.getOriginalImageHeight();
    }

    public
    CompressedImage ( Mat dest ) {
        super((MatOfInt) dest, colorType);
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
     * @param layers
     * @param inputImage
     * @return
     */
    @Override
    public
    Mat merge ( List <IImage> layers, IImage inputImage ) {
        return inputImage;
    }

    /**
     * @return
     */
    @Override
    public
    List <IImageBlock> getRangeBlocks () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    List <IImageBlock> getDomainBlocks () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getOriginalSize () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform> getTransforms () throws ValueError {
        return transforms;
    }

    /**
     * @param transforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform> transforms ) {
        this.transforms = transforms;
    }

    /**
     * @param rectangle
     * @return
     */
    @Override
    public
    IImageBlock getSubImage ( Rectangle rectangle ) throws ValueError {
        return getSubImage(rectangle.getAddress(), rectangle.width, rectangle.height);
    }

    public
    IImageBlock getSubImage ( IAddress address, int width, int height ) throws ValueError {
        Mat mat = getMat().submat((Rect) address.getCartesianCoordinates(address.radix()));

        return new ImageBlock(mat, new Rectangle(address, width, height));
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    @Override
    public
    void setMeanPixelValue ( double meanPixelValue ) {

    }

    @Override
    public
    double pixelValues ( int x, int y ) {
        return new double[0];
    }

    @Override
    public
    double getPixelValuesLayer ( int x, int y, int ch ) {
        return 0;
    }

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
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public
    int compareTo ( @NotNull IIntSize o ) {
        return -1;
    }

    /**
     * @return
     */
    @Override
    public
    Size toSize () {
        return null;//todo
    }

    /**
     * @return
     */

    public
    void getRGB ( int i, int i1, int width, int height, double[] img1pixels, int i2, int i3 ) {

    }

    /**
     * @param address
     * @param pixels
     */
    @Override
    public
    void putPixel ( IAddress address, double[] pixels ) {
        getMat().put(address.getX(), address.getY(), pixels[0]);
    }

    /**
     * @param x
     * @param y
     * @param pixelData
     * @throws ValueError
     */
    @Override
    public
    void putPixel ( int x, int y, double[] pixelData ) throws ValueError {
        super.putPixel(x, y, pixelData);
    }

    @Override
    public
    void putPixels ( double[] pixelData ) {

    }

    @Override
    public
    boolean isSquare () {
        return super.isSquare();
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

    //    @Override
    protected
    int compareToSameClass ( Geometry <?> o ) {
        return 0;
    }

    //    @Override
    protected
    int compareToSameClass ( Geometry <?> o, CoordinateSequenceComparator comp ) {
        return 0;
    }

}
