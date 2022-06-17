package org.stranger2015.opencv.fic.transform;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public
class PreserveAlphaTransform</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform <A, G> {

    private final boolean preserveAlpha;

    /**
     * @param image
     * @param preserveAlpha
     */
    protected
    PreserveAlphaTransform ( IImage<A> image, boolean preserveAlpha, IAddress <A> address ) {
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
    IImage<A> transform ( @NotNull IImage<A> inputImage, IImage<A> transformMatrix, EInterpolationType type ) {
        return super.transform(inputImage, transformMatrix,type);
    }

}
