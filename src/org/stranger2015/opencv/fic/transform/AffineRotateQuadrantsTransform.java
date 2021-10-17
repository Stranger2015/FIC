package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * functor class to rotate an image by the given quadrant
 */
public class AffineRotateQuadrantsTransform<T extends Mat> extends ImageTransform<T> {

    private int quadrants;
    private int interpolationType;

    /**
     * @param quadrants the number of 90 degree arcs to rotate by
     * @param interpolationType  
     */
    public AffineRotateQuadrantsTransform(final int quadrants, final int interpolationType) {
        this.quadrants = quadrants;
        this.interpolationType = interpolationType;
    }

    public AffineRotateQuadrantsTransform(final int quadrants) {
        this(quadrants, AffineTransformOp.TYPE_BILINEAR);
    }
//
//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        return affineTransform(inputimage, AffineTransform.getQuadrantRotateInstance(
//                quadrants, inputimage.getWidth()  / 2,
//                           inputimage.getHeight() / 2), interpolationType);
//    }
//
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
