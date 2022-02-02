package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Image;

/**
 * @param <M>
 */
public
class ShearTransform<M extends Image> extends ImageTransform<M>{
private final double degrees;
    /**
     * @param image
     * @param degrees
     */
    protected
    ShearTransform ( M image, double degrees ) {
        super(image);
        this.degrees = degrees;
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
        return null;
    }

    public
    double getDegrees () {
        return degrees;
    }
}
