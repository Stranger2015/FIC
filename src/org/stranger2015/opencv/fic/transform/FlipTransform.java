package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * functor class to affineFlip an image
 */
public
class FlipTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends PreserveAlphaTransform <M, A, G> {

    /**
     * @param preserveAlpha
     */
    public
    FlipTransform ( M image, boolean preserveAlpha, IAddress <A> address ) {
        super(image, preserveAlpha, address);
    }
}
