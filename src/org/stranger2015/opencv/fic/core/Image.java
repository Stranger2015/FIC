package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Size;

import java.awt.*;

/**
 *
 */
public
class Image extends MatOfDouble {
    /**
     * @param rows
     * @param cols
     * @param type
     */
    public
    Image ( int rows, int cols, int type ) {
        super(rows, cols, type);
    }

    /**
     * @param n
     */
    public
    Image ( double... n ) {
        super(n);
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
//    outputImage = np.zeros((img.shape[0] // factor, img.shape[1] // factor))
//    for i in range(outputImage.shape[0]):
//        for j in range(outputImage.shape[1]):
//            outputImage[i,j] = np.mean(img[i*factor:(i+1)*factor,j*factor:(j+1)*factor])
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
}
