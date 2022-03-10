package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <M>
 * @param <A>
 * @param <G>
 */
public
class FlopTransform<M extends IImage <A>, A extends Address <A>, G extends BitBuffer> extends ImageTransform <M, A, G> {
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
    M transform ( M inputImage, Mat transformMatrix, EInterpolationType type ) {
        return null;
    }
}
