package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class SipImage extends Image {
    protected int[] addresses;

    public
    <M extends Image>
    SipImage ( M input, int[] addresses ) {
        super(input);
        this.addresses = addresses;
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
     */
    @Override
    public
    Image createInputImage (Image image) {
        return new SipImage(image, new int[0]);//fixme
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    Image createOutputImage ( Image image ) {
        return new CompressedSipImage(image, new int[0], new ICompressedImage() {
            @Override
            public
            List <ImageTransform <Image>> getTransforms () {
                return null;
            }

            @Override
            public
            void setTransforms ( List <ImageTransform <Image>> transforms ) {

            }
        });//fixme
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
        int address = addresses[row + getWidth() * col];//??? fixme

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
