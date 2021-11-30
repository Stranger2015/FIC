package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

/**
 *
 *
 *
 * @param <M>
 */
public
class DctTransform<M extends Image, C extends CompressedImage> extends ImageTransform <M, C> {

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix, int interpolationType ) {
        M out= (M) new Image();
        return out;
    }
}
