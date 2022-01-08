package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;

/**
 *
 */
public
class SipImage extends Image {

    private int[] addresses;

    /**
     * @param rows
     * @param type
     */
    public
    SipImage ( int rows, int type ) {
        super(rows, 1, type);
    }

    /**
     * @param imread
     * @param size
     */
    public
    SipImage ( Mat imread, Size size, int[] addresses ) {
        super(imread, size);
        this.addresses = addresses;
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
     * @return
     */
//    @Override
    public
    double[] get ( int row ) {
        return super.get(row, 0);
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
        int address = addresses[row + getWidth() * col];//??????????? fixme

        return super.put(address, data);
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

    /**
     * @return
     */
    public
    int[] getAddresses () {
        return addresses;
    }
}
