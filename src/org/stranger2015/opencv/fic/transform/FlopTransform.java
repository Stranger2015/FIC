package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 
 * @param
 * @param <G>
 */
public
class FlopTransform extends ImageTransform {

    /**
     * @param image
     * @param type
     * @param address
     */
    public
    FlopTransform ( IImage image, EInterpolationType type, IAddress  address ) {
        super(image, type, address);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
//    @Override
    public
    IImage transform ( IImage inputImage, IImage transformMatrix, EInterpolationType type ) {
        return null;
    }
}
