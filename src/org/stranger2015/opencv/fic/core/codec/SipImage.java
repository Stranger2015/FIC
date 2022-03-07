package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class SipImage< A extends Address<A>> extends Image<A> {

    protected Pixel[] pixels;

    /**
     * @param input
     * @param pixels
     */
    public
    <M extends IImage<A>>
    SipImage ( M input, Pixel[] pixels ) {
        super(input);

        this.pixels = pixels;
    }

    /**
     * @param row
     * @return
     */
    public
    double[] get ( int row ) {
        return super.get(row, 0);
    }

    /**
     * @return
     * @param image
     */
    @Override
    public
    SipImage<A> createInputImage ( IImage<A> image ) {
        return new SipImage<>(image, new Pixel[image.getWidth() * image.getHeight()]);
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    CompressedSipImage<A> createOutputImage ( IImage<A> image ) {
        return new CompressedSipImage <A>();
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
        int address = row + width() * col;//??? fixme

        return super.put(address, data);
    }

    /**
     * @param imread
     */
    public
    SipImage ( Mat imread ) {
        super(new SipImage <>(imread), imread.rows(), imread.cols(), imread.type());
    }

    /**
     * @param imread
     * @param size
     */
    public
    SipImage ( Mat imread, Size size ) {
        super(imread, size);
    }

    public
    Pixel getPixelValue ( int address ) {
        return pixels[address];
    }

    /**
     * @return
     */
    public
    Pixel[] getPixels () {
        return pixels;
    }
}
