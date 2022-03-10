package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 *
 */
public
class AffineFlipTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends AffineTransform <M, A, G> {


    /**
     * @param image
     * @param interpolationType
     */
    public
    AffineFlipTransform ( M image, EInterpolationType interpolationType, IAddress <A> address ) {
        super(image, interpolationType, address );
    }

    /**
     * @param image
     */
    public
    AffineFlipTransform ( M image, IAddress<A> address ) {
        this(image, BILINEAR, address);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    @Override
    public
    M transform ( M inputImage, Mat transformMatrix, EInterpolationType type ) {
        return null;
    }
}
