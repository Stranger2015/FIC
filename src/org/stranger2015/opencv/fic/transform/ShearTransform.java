package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.*;

/**
 
 */
public
class ShearTransform</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform<A, G>{

    private final double degrees;
    /**
     * @param image
     * @param degrees
     */
    protected
    ShearTransform ( IImage<A> image, double degrees, Address<A> address ) {
        super(image, BILINEAR, address);
        this.degrees = degrees;
    }

    public
    double getDegrees () {
        return degrees;
    }
}
