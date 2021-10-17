package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * 
 */
public class AffineScaleTransform<T extends Mat> extends ImageTransform<T>{

    private final double scalex;
    private final double scaley;
    private final int interpolationType;

    public AffineScaleTransform(final double scalex, final double scaley, final int interpolationType) {
        this.scalex = scalex;
        this.scaley = scaley;
        this.interpolationType = interpolationType;
    }

    public AffineScaleTransform(final double scalex, final double scaley) {
        this(scalex, scaley, AffineTransformOp.TYPE_BILINEAR);
    }

    /**
     * @param inputimage
     * @param outputimage
     * @param transformMatrix
     * @return
     */
    @Override
    public
    void transform ( Mat inputimage, Mat outputimage, Mat transformMatrix, int interpolationType ) {

    }
}
