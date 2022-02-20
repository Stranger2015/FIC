package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 *
 */
public
class AffineFlipTransform<M extends IImage, A extends Address <A>, G extends BitBuffer>
        extends AffineTransform <M, A, G> {


    /**
     * @param image
     * @param interpolationType
     */
    public
    AffineFlipTransform ( M image, EInterpolationType interpolationType, Address<A> address ) {
        super(image, interpolationType, address );
    }

    /**
     * @param image
     */
    public
    AffineFlipTransform ( M image, Address<A> address ) {
        this(image, BILINEAR, address);
    }
}
