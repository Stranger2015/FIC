package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 
 */
public
class ShearTransform
        extends ImageTransform{

    private final double degrees;
    /**
     * @param image
     * @param degrees
     */
    protected
    ShearTransform ( IImage image, double degrees, IAddress address ) {
        super(image, BILINEAR, address);
        this.degrees = degrees;
    }

    public
    double getDegrees () {
        return degrees;
    }

    /**
     * @param bb
     * @param transform
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Override
    public
    void writeBits ( BitBuffer bb, ImageTransform transform ) throws InstantiationException, IllegalAccessException {

    }

    /**
     * @param bb
     * @return
     * @throws ValueError
     */
    @Override
    public
    ImageTransform readBits ( BitBuffer bb ) throws ValueError {
        return null;
    }
}
