package org.stranger2015.opencv.fic.transform;

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
class PreserveAlphaTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends ImageTransform <M, A, G> {

    private final boolean preserveAlpha;

    /**
     * @param image
     * @param preserveAlpha
     */
    protected
    PreserveAlphaTransform ( M image, boolean preserveAlpha, IAddress <A> address ) {
        super(image, null, address);

        this.preserveAlpha = preserveAlpha;
    }

    /**
     * @return
     */
    public
    boolean isPreserveAlpha () {
        return preserveAlpha;
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
        return super.transform(inputImage, transformMatrix,type);
    }
}
