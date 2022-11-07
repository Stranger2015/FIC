package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * functor class to affineFlip an image
 */
public
class FlipTransform<A extends IAddress <A>, G extends BitBuffer>
        extends PreserveAlphaTransform <A, G> {

    /**
     * @param preserveAlpha
     */
    public
    FlipTransform ( IImage<A> image, boolean preserveAlpha, IAddress <A> address ) {
        super(image, preserveAlpha, address);
    }
}
