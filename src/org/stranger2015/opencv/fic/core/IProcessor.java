package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgproc.Imgproc;

/**
 *
 */
public
interface IProcessor {
    /**
     * @param filename
     * @return
     */
    IImage preprocess ( String filename );

    /**
     * @param filename
     * @param inputImage
     * @return
     */
    IImage preprocess ( String filename, IImage inputImage ) throws ValueError;

    /**
     * @return
     */
    IImage process ( IImage inputImage ) throws Exception;

    /**
     * @param outputImage
     * @return
     */
    default
    CompressedImage  postprocess ( CompressedImage  outputImage ) {
        IIntSize size = outputImage.getSize();
        int w = size.getWidth();
        int h = size.getHeight();
        size = outputImage.restoreSize(, h, size.getOriginalImageWidth(), size.getOriginalImageHeight());
        Mat dest = new Mat();//CompressedImage(outputImage);

        Imgproc.resize(outputImage.getMat(), dest, size.toSize());

        return new CompressedImage ((IImage ) dest);
    }
    /**
     * @param image
     * @return
     */
    default
    IImage preprocess ( IImage image ) throws ValueError {
        IIntSize size = image.getSize();
        int w = size.getWidth();
        int h = size.getHeight();
        int radix = image.getAddress(0,0).radix();
        size = adjustSize(radix, w, h);
        image = new Image((MatOfInt) image.getMat(), size, colorType);

        return image;
    }

    /**
     * @param base
     * @param w
     * @param h
     * @return
     */
    IntSize adjustSize ( int base, int w, int h );
}
