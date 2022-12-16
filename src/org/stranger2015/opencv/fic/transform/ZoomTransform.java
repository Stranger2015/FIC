package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 
 * @param
 * @param <G>
 */
public
class ZoomTransform extends ImageTransform{

    /**
     * @param image
     * @param address
     */
    public
    ZoomTransform ( IImage image, IAddress  address) {
        super(image, EInterpolationType.BILINEAR, address);
    }
}
