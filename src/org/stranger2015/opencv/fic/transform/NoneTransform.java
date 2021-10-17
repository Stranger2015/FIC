package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;

/*
 * The 'None' transform -- makes no change to the given image.
 * This is useful to traverse the transforms without special casing
 * the comparison with the normal(non-transformed) image.
 */
public class NoneTransform<T extends Mat> extends ImageTransform<T> {
    /**
     * @param inputimage
     * @param outputimage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    void transform ( T inputimage, T outputimage, T transformMatrix, int interpolationType ) {

    }

}
