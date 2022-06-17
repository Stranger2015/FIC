package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public
class ZoomTransform</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform<A, G>{

    /**
     * @param image
     * @param address
     */
    public
    ZoomTransform (M image, IAddress <A> address) {
        super(image, EInterpolationType.BILINEAR, address);
    }
}
