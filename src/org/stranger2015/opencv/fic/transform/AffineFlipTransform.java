package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * 
 */
public class AffineFlipTransform<T extends Mat> extends ImageTransform<T> {

    /**
     *
     */
    private final int interpolationType;

    public AffineFlipTransform(final int interpolationType) {
        this.interpolationType = interpolationType;
    }

    public AffineFlipTransform() {
        this(AffineTransformOp.TYPE_BILINEAR);
    }

    /**
    /**
     * @param inputimage
     * @param outputimage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    void transform ( T inputimage, T outputimage, T transformMatrix, int interpolationType ) {

    }
//
//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        AffineTransform transform = new AffineTransform();
//        transform.translate(inputimage.getWidth()  / 2, inputimage.getHeight()  / 2);
//        transform.scale(1, -1);
//        transform.translate(-inputimage.getWidth() / 2, -inputimage.getHeight() / 2);
//        return affineTransform(inputimage, transform, interpolationType);
//    }


}
