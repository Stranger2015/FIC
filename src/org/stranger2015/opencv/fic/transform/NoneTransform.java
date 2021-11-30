package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

/**
 * The 'None' transform -- makes no change to the given image.
 * This is useful to traverse the transforms without special casing
 * the comparison with the normal (non-transformed) image.
 */
public class NoneTransform<M extends Image, C extends CompressedImage> extends ImageTransform<M,C> {

    /**
     * @param image
     */
    protected
    NoneTransform ( M image ) {
        super(image);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType ) {
        return inputImage;
    }
}
