package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import org.stranger2015.opencv.fic.core.codec.Pixel;

import java.awt.*;

/**
 *
 */
public
class Image extends MatOfDouble implements IImage {

    /**
     *
     */
enum EColorType {
    GRAYSCALE(0),
    TRUE_COLOR(2),
    INDEXED_COLOR(3),
    GRAYSCALE_WITH_ALPHA(4),
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
    @Contract(pure = true)
    public
    int getCType () {
        return cType;
    }
}

    public /*final fixme */ EColorType cType;

       public int originalImageWidth;
    public int originalImageHeight;

    /**
     * @param rows
     * @param cols
     * @param cType
     * @param pixelData
     */
    public
    Image ( int rows, int cols, EColorType/*int*/ cType, Scalar pixelData ) {
        super(rows,
                cols,
                cType.getCType(),//FIXME
                pixelData.val[0],
                pixelData.val[1],
                pixelData.val[2],
                pixelData.val[3]
        );
        this.cType=cType;
    }

    /**
     * @param image
     * @param n
     */
    public
    Image ( IImage image, double... n ) {
        super(n);
    }

    /**
     * @param image
     * @param x
     * @param y
     * @param w
     * @param h
     * @param cType
     */
    public
    Image ( IImage image, int x, int y, int w, int h, EColorType cType ) {
//todo
        this.cType = cType;
    }

    public
    Image ( IImage image, int address, int blockSize, EColorType cType ) {
     //todo
        this.cType = cType;
    }

    /**
     * @return
     */
//    @SuppressWarnings("unchecked")
    public
    IImage subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return (IImage) submat(rowStart, rowEnd, colStart, colEnd);
    }

    /**
     * @param imread
     */
    public
    Image ( Mat imread ) {
        imread.create(imread.size(), type());
    }

    /**
     * @param imread
     */
    public
    Image ( Mat imread, Size size ) {
        imread.create(size, type());
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
    public
    int getWidth () {
        return width();
    }

    /**
     * @return
     */
    public
    int getHeight () {
        return height();
    }

    /**
     * @return
     */
    @Override
    public
    Size getSize () {
        return size();
    }

    /**
     * @param row
     * @param col
     * @return
     */
    @Override
    public
    double[] get ( int row, int col ) {
        return super.get(row, col);
    }

    /**
     * @param row
     * @param col
     * @param data
     * @return
     */
    @Override
    public
    int put ( int row, int col, double... data ) {
        return super.put(row, col, data);
    }

    /**
     *
     *
     * @param inputImage
     * @param outputImage
     * @param factor
     * @return
     */
    public
    IImage reduce ( IImage inputImage, IImage outputImage, int factor ) {
        return outputImage;
    }

    /**
     * @param m
     * @param rtype
     * @param alpha
     * @param beta
     */
    @Override
    public
    void convertTo ( Mat m, int rtype, double alpha, double beta ) {
        super.convertTo(m, rtype, alpha, beta);
    }

    /**
     * @param image
     * @return
     */
    public
    IImage convertTo ( IImage image ) {
        return image;
    }

    /**
     * @param address
     * @param data
     * @return
     */
    public
    int put ( int address, double... data ) {
        return put(address, 0, data);
    }

    /**
     * @return
     */
    public
    IImage createInputImage (IImage image) {
        return new Image(image);
    }

    /**
     *
     * @param image
     * @return
     */
    public
    IImage createOutputImage(IImage image){
        return new CompressedImage(image);
    }

    /**
     * @param row
     * @param col
     * @return
     */
    public
    Pixel getPixel ( int row, int col ) {
        return new Pixel(get(row, col));
    }

    /**
     * @param contractivity
     * @return
     */
    @Override
    public
    IImage contract ( int contractivity){

        IImage image = new Image(this);

        int newWidth = image.width()/contractivity;
        int newHeight = image.height()/contractivity;

        Imgproc.resize(this, (Mat) image, new Size(newWidth, newHeight));

        return image;
    }

    @Override
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    @Override
    public
    void setOriginalImageWidth ( int originalImageWidth ) {
        this.originalImageWidth = originalImageWidth;
    }

    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }


    @Override
    public
    void setOriginalImageHeight ( int originalImageHeight ) {
        this.originalImageHeight = originalImageHeight;
    }


    @Override
    public
    void put ( int destX, int destY, int pixel ) {

    }

    @Override
    public
    void put ( Pixel[] pixels, double v, double v1, double v2, double v3 ) {

    }
}
