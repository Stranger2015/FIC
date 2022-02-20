package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.codec.Pixel;

/**
 *
 */
public
interface IImage<A extends Address<A>> {
//    /**
//     *
//     * @return
//     */
//    int getX();
//
//    /**
//     *
//     * @return
//     */
//    int getY();

    /**
     *
     * @return
     */
    int getWidth();

    /**
     *
     * @return
     */
    int getHeight();
    /**
     *
     * @return
     */
    Size getSize ();

    /**
     *
     * @param contractivity
     */
    IImage<A> contract ( int contractivity);

    /**
     * @return
     */
    int width ();

    /**
     * @return
     */
    int height ();

    /**
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @return
     */
    Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd );

    /**
     * @return
     */
    int  getOriginalImageWidth();

    /**
     * @param originalImageWidth
     */
    void setOriginalImageWidth ( int originalImageWidth );

    /**
     * @param originalImageHeight
     */
    void setOriginalImageHeight ( int originalImageHeight );

    /**
     * @return
     */
    int  getOriginalImageHeight();

    /**
     * @param x
     * @param y
     * @param ia
     * @return
     */
    int get ( int x, int y, int[] ia );

    /**
     * @param destX
     * @param destY
     * @param pixel
     */
    void put ( int destX, int destY, int pixel );

    /**
     * @param pixels
     * @param v
     * @param v1
     * @param v2
     * @param v3
     */
    void put ( Pixel[] pixels, double v, double v1, double v2, double v3 );

    /**
     * @return
     */
    int cols ();

    /**
     * @return
     */
    int rows ();

    /**
     * @param x
     * @param y
     */
    void setAddress ( int x, int y );

}




