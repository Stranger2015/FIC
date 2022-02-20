package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to affine shear an image
 */
public
class AffineShearTransform<M extends IImage, A extends Address <A>, G extends BitBuffer>
        extends AffineTransform <M, A, G> {

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
    AffineShearTransform ( M image,
                           double shearX,
                           double shearY,
                           EInterpolationType interpolationType,
                           Address <A> address ) {

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
    AffineShearTransform ( M image, double shearX, double shearY, Address <A> address ) {
        this(image, shearX, shearY, BILINEAR, address);
    }

    public
    double getShearX () {
        return shearX;
    }

    public
    double getShearY () {
        return shearY;
    }
}
