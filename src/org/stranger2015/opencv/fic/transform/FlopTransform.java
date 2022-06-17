package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public
class FlopTransform</* M extends IImage <A> */, A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform <A, G> {

    /**
     * @param image
     * @param type
     * @param address
     */
    public
    FlopTransform ( M image, EInterpolationType type, IAddress <A> address ) {
        super(image, type, address);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType type ) {
        return null;
    }
}
