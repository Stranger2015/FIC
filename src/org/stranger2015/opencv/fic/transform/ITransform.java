package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Image;

/**
 * @param <M>
 */
public interface ITransform<M extends Image> {

    /**
     * @param inputImage
     * @return
     */
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType );
}
