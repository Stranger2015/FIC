package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;

/**
 * @param <T>
 */
public interface ITransformer<T extends Mat> {

    /**
     * @param inputimage
     * @return
     */
    void transform ( final T inputimage, final T outputimage, T transformMatrix, int interpolationType );
}
