package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * functor class to rotate an image by the given degrees
 */
public class AffineRotateTransform<T extends Mat> extends ImageTransform<T> {

    private final double degrees;
    private final int interpolationType;

    public AffineRotateTransform(final double degrees, final int interpolationType) {
        this.degrees = degrees;
        this.interpolationType = interpolationType;
    }

    public AffineRotateTransform(final double degrees) {
        this(degrees, AffineTransformOp.TYPE_BILINEAR);
    }

//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        return affineTransform(inputimage, AffineTransform.getRotateInstance(
//                Math.toRadians(degrees), inputimage.getWidth()  / 2,
//                                         inputimage.getHeight() / 2), interpolationType);
//    }

    /**
     * @param inputimage
     * @param outputimage
     * @param transformMatrix
     * @return
     */
    @Override
    public
    void transform ( Mat inputimage, Mat outputimage, Mat transformMatrix,int interpolationType ) {

    }
}
