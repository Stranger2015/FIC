package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <M>
 * @param <A>
 * @param <G>
 */
public
class DctTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends ImageTransform <M, A, G> {

    /**
     * @param image
     */
    protected
    DctTransform ( M image, Address<A> address ) {
        super(image, EInterpolationType.BILINEAR, address);
    }
}
