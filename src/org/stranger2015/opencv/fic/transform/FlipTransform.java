package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 * functor class to affineFlip an image
 */
public
class FlipTransform
        extends PreserveAlphaTransform  {

    /**
     * @param preserveAlpha
     */
    public
    FlipTransform ( IImage image, boolean preserveAlpha, IAddress  address ) {
        super(image, preserveAlpha, address);
    }
}
