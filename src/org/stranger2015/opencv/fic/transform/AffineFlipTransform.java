package org.stranger2015.opencv.fic.transform;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 *
 */
public
class AffineFlipTransform
        extends AffineTransform{

    /**
     * @param image
     * @param interpolationType
     */
    public
    AffineFlipTransform ( IImage image, EInterpolationType interpolationType, IAddress  address ) {
        super(image, interpolationType, address );
    }

     /**
     * @param inputImage
     */
    public
    AffineFlipTransform (GrayScaleImage inputImage, IAddress address ) {
        this(inputImage, BILINEAR, address);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
//    @Override
    public
    IImage transform ( @NotNull IImage inputImage,
                                  IImage transformMatrix,
                                  EInterpolationType type ) {

        return inputImage;
    }
}
