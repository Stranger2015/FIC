package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.*;

import static org.stranger2015.opencv.fic.core.codec.IEncoder.flipAxis;
import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 *
 */
public
class AffineFlipTransform extends AffineTransform{

    /**
     *
     * @param image
     * @param interpolationType
     * @param address
     */
    public
    AffineFlipTransform ( IImageBlock image, EInterpolationType interpolationType, IAddress  address ) {
        super(image, interpolationType, address );
    }

     /**
     * @param inputImage
     */
    public
    AffineFlipTransform ( IImageBlock inputImage, IAddress address ) {
        this(inputImage, BILINEAR, address);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    @Override
    public
    IImageBlock transform ( IImageBlock inputImage,
                       Mat transformMatrix,
                       EInterpolationType type ) {

        return inputImage;
    }

    @Override
    public
    IImageBlock warpDihedral ( IImageBlock inputImage,
                               int[] transformMatrixArray,
                               EInterpolationType interpolationType ) throws ValueError {
        return flipAxis(inputImage, 0);
    }
}
