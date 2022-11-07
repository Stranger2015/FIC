package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.*;

/**
 
 * @param <A>
 * @param <G>
 */
public
class DctTransform<A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform <A, G> {

    /**
     * @param image
     */
    protected
    DctTransform ( IImage<A> image, IAddress <A> address ) {
        super(image, BILINEAR, address);
    }
}
