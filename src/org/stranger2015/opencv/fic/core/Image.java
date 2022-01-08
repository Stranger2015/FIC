package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

/**
 *
 */
public
class Image extends MatOfDouble {

    public int originalImageWidth;
    public int originalImageHeight;

    /**
     * @param rows
     * @param cols
     * @param type
     * @param pixelData
     */
    public
    Image ( int rows, int cols, int type, Scalar pixelData ) {
        super(rows,
                cols,
                type,
                pixelData.val[0],
                pixelData.val[1],
                pixelData.val[2],
                pixelData.val[3]);
    }

    /**
     * @param n
     */
    public
    Image ( double... n ) {
        super(n);
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    ImageBlock subImage () {
        return (ImageBlock) submat(0, 0, 0, 0);//fixme
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
     * @param inputImage
     * @param outputImage
     * @param factor
     * @return
     */
    public
    Image reduce ( Image inputImage, Image outputImage, int factor ) {
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
    Image convertTo ( Image image ) {
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
}
