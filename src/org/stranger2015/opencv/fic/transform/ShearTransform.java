package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

/**
 * @param <M>
 * @param <C>
 */
public
class ShearTransform<M extends Image, C extends CompressedImage> extends ImageTransform<M,C>{
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
