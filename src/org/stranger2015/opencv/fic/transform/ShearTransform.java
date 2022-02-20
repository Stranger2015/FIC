package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <M>
 */
public
class ShearTransform<M extends IImage, A extends Address <A>, G extends BitBuffer>
        extends ImageTransform<M, A, G>{

    private final double degrees;
    /**
     * @param image
     * @param degrees
     */
    protected
    ShearTransform ( M image, double degrees, Address<A> address ) {
        super(image, EInterpolationType.BILINEAR, address);
        this.degrees = degrees;
    }

    public
    double getDegrees () {
        return degrees;
    }
}
