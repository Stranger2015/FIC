package org.stranger2015.opencv.fic.transform;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 *
 */
public
class AffineFlipTransform</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends AffineTransform <A, G> {

    /**
     * @param image
     * @param interpolationType
     */
    public
    AffineFlipTransform ( IImage <A> image, EInterpolationType interpolationType, IAddress <A> address ) {
        super(image, interpolationType, address );
    }

     /**
     * @param inputImage
     */
    public
    AffineFlipTransform (GrayScaleImage<A> inputImage, IAddress<A> address ) {
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
    IImage<A> transform ( @NotNull IImage <A> inputImage,
                                  IImage<A> transformMatrix,
                                  EInterpolationType type ) {

        return inputImage;
    }
}
