package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * 
 */
public class AffineFlopTransfrom extends ImageTransform {

    private int interpolationType;

    public AffineFlopTransfrom(final int interpolationType) {
        this.interpolationType = interpolationType;
    }

    public AffineFlopTransfrom() {
        this(AffineTransformOp.TYPE_BILINEAR);
    }

//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        AffineTransform transform = new AffineTransform();
//        transform.translate(inputimage.getWidth()  / 2, inputimage.getHeight()  / 2);
//        transform.scale(-1, 1);
//        transform.translate(-inputimage.getWidth() / 2, -inputimage.getHeight() / 2);
//        return affineTransform(inputimage, transform, interpolationType);
//    }

    /**
     * @param inputimage
     * @param outputimage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    void transform ( Mat inputimage, Mat outputimage, Mat transformMatrix, int interpolationType ) {

    }
}
