package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public
class SipImage extends Image {
    private int[] addresses;

    /**
     * @param rows
     * @param cols
     * @param type
     */
    public
    SipImage ( int rows, int cols, int type ) {
        super(rows, cols, type);
    }

    /**
     * @param n
     */
    public
    SipImage ( double... n ) {
        super(n);
    }

    public
    <M extends Image, A extends SipAddress <A>>
    SipImage ( M input, int[] addresses ) {
        super(input);
        this.addresses = addresses;
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
        int address = addresses[row + getWidth() * col];

        int row1;
        int col1;
        return super.put(row1, col1, data);
    }

    /**
     * @param imread
     */
    public
    SipImage ( Mat imread ) {
        super(imread.rows(), imread.cols(), imread.type());
    }

    /**
     * @param imread
     * @param size
     */
    public
    SipImage ( Mat imread, Size size ) {
        super(imread, size);
    }

//    /**
//     * @return
//     */
//    public
//    List <Pixel> getPixels () {
//        return pixels;
//    }

    /**
     * @return
     */
    public
    Image toImage () {
        Image image = new Image();

        return image;
    }

    public
    int[] getAddresses () {
        return addresses;
    }
}
