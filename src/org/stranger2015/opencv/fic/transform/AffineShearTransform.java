package lib.transformations;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * functor class to affineShear an image
 */
public class AffineShearTransform<T extends Mat> extends ImageTransform <T> {

    private final double shearx;
    private final double sheary;
    private final int interpolationType;

    /**
     * 
     * @param shearx the multiplier by which coordinates are shifted in the 
     * direction of the positive X axis as a factor of their Y coordinate
     * @param sheary the multiplier by which coordinates are shifted in the 
     * direction of the positive Y axis as a factor of their X coordinate
     * @param interpolationType  
     */
    public AffineShearTransform(final double shearx, final double sheary, final int interpolationType) {
        this.shearx = shearx;
        this.sheary = sheary;
        this.interpolationType = interpolationType;
    }

    public AffineShearTransform(final double shearx, final double sheary) {
        this(shearx, sheary, AffineTransformOp.TYPE_BILINEAR);
    }

//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        return affineTransform(inputimage, AffineTransform.getShearInstance(shearx, sheary), interpolationType);
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
    void transform ( T inputimage, T outputimage, T transformMatrix, int interpolationType ) {

    }
}
