package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to affine shear an image
 */
public
class AffineShearTransform extends AffineTransform  {

    private final double shearX;
    private final double shearY;

    /**
     * @param shearX            the multiplier by which coordinates are shifted in the
     *                          direction of the positive X axis as a factor of their Y coordinate
     * @param shearY            the multiplier by which coordinates are shifted in the
     *                          direction of the positive Y axis as a factor of their X coordinate
     * @param interpolationType
     */
    public
    AffineShearTransform ( IImage image,
                           double shearX,
                           double shearY,
                           EInterpolationType interpolationType,
                           IAddress  address ) {

        super(image, interpolationType, address);

        this.shearX = shearX;
        this.shearY = shearY;
    }

    /**
     * @param image
     * @param shearX
     * @param shearY
     * @param address
     */
    public
    AffineShearTransform ( IImage image, double shearX, double shearY, IAddress  address ) {
        this(image, shearX, shearY, BILINEAR, address);
    }

    /**
     * @return
     */
    public
    double getShearX () {
        return shearX;
    }

    /**
     * @return
     */
    public
    double getShearY () {
        return shearY;
    }
}
