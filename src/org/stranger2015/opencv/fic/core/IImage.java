package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.codec.Pixel;

/**
 *
 */
public
interface IImage {
    /**
     *
     * @return
     */
    int getX();

    /**
     *
     * @return
     */
    int getY();

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
    IImage contract ( int contractivity);

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

    int get ( int x, int y, int[] ia );

    void put ( int destX, int destY, int pixel );

    void put ( Pixel[] pixels, double v, double v1, double v2, double v3 );

    int cols ();
    int rows ();
}




