package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 
 * @param
 * @param <G>
 */
public
class PreserveAlphaTransform extends ImageTransform {

    private final boolean preserveAlpha;

    /**
     * @param image
     * @param preserveAlpha
     */
    protected
    PreserveAlphaTransform ( IImage image, boolean preserveAlpha, IAddress  address ) {
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
    IImage transform ( IImage inputImage, Mat transformMatrix, EInterpolationType type ) {
        return super.transform(inputImage, transformMatrix,type);
    }
}
