package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IImage;

/**
 * @param <M>
 */
public interface ITransform<M extends IImage> {

    /**
     * @param inputImage
     * @return
     */
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType );
}
