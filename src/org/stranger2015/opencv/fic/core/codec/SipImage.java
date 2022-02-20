package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class SipImage<A extends Address<A>> extends Image<A> {
    protected Pixel[] pixels;

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
     */
    @Override
    public
    IImage createInputImage ( IImage image ) {
        return new SipImage(image, new Pixel[image.width() * image.height()]);
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    CompressedSipImage createOutputImage ( IImage image ) {
        return new CompressedSipImage(image, new Pixel[0], new ICompressedImage() {
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
            @Override
            public
            int getWidth () {
                return 0;
            }

            /**
             * @return
             */
            @Override
            public
            int getHeight () {
                return 0;
            }

            /**
             * @return
             */
            @Override
            public
            Size getSize () {
                return null;
            }

            /**
             * @param contractivity
             */
            @Override
            public
            IImage contract ( int contractivity ) {
                return null;
            }

            /**
             * @return
             */
            @Override
            public
            int width () {
                return 0;
            }

            /**
             * @return
             */
            @Override
            public
            int height () {
                return 0;
            }

            /**
             * @param rowStart
             * @param rowEnd
             * @param colStart
             * @param colEnd
             * @return
             */
            @Override
            public
            Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd ) {
                return null;
            }

            /**
             * @return
             */
            @Override
            public
            int getOriginalImageWidth () {
                return 0;
            }

            /**
             * @param originalImageWidth
             */
            @Override
            public
            void setOriginalImageWidth ( int originalImageWidth ) {

            }

            /**
             * @param originalImageHeight
             */
            @Override
            public
            void setOriginalImageHeight ( int originalImageHeight ) {

            }

            /**
             * @return
             */
            @Override
            public
            int getOriginalImageHeight () {
                return 0;
            }

            @Override
            public
            List <ImageTransform <IImage>> getTransforms () {
                return null;
            }

            /**
             * @param transforms
             */
            @Override
            public
            void setTransforms ( List <ImageTransform <IImage>> transforms ) {

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
        int address = row + width() * col;//??? fixme

        return super.put(address, data);
    }

    /**
     * @param imread
     */
    public
    SipImage ( Mat imread ) {
        super(new SipImage(imread), imread.rows(), imread.cols(), imread.type());
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
