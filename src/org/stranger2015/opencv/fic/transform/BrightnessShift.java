package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Image;

public
class BrightnessShift<M extends Image> extends ShiftTransform <M> {
    public
    BrightnessShift (M image, float brightness) {
        super(image,  brightness);
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
