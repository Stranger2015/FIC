package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

import java.awt.image.AffineTransformOp;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to affineShear an image
 */
public class AffineShearTransform<M extends Image, C extends CompressedImage> extends AffineTransform <M, C> {

    private final double shearX;
    private final double shearY;
    /**
     *
     * @param shearX the multiplier by which coordinates are shifted in the 
     * direction of the positive X axis as a factor of their Y coordinate
     * @param shearY the multiplier by which coordinates are shifted in the 
     * direction of the positive Y axis as a factor of their X coordinate
     * @param interpolationType
     */
    public AffineShearTransform(M image, double shearX,
                                final double shearY,
                                final EInterpolationType interpolationType) {
        super(image,interpolationType);
        this.shearX = shearX;
        this.shearY = shearY;
    }

    public AffineShearTransform(M image, double shearX, double shearY) {
        this(image,shearX, shearY, BILINEAR);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix,EInterpolationType interpolationType ) {

        M outputImage= (M) new Image();

        return outputImage;
    }

    public
    double getShearX () {
        return shearX;
    }

    public
    double getShearY () {
        return shearY;
    }

//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        return affineTransform(inputimage, AffineTransform.getShearInstance(shearX, shearY), interpolationType);
//    }

}
