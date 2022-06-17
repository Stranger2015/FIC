package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.transform.EInterpolationType;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public
class BlockSegmentationTransform</*M extends IImage<A>,*/ A extends IAddress<A>, G extends BitBuffer>
        extends ImageTransform<M,A,G>{
    /**
     * @param image
     * @param type
     * @param address
     */
    public
    BlockSegmentationTransform ( GrayScaleImage<A> image, EInterpolationType type, IAddress <A> address ) {
        super(image, type, address);
    }

    /**
     * @throws ValueError
     */
    protected
    BlockSegmentationTransform () throws ValueError {
    }

    /**
     * @param image
     * @param type
     * @param address
     * @param brightnessOffset
     * @param contrastScale
     * @param dihedralAffineTransformIndex
     */
    public
    BlockSegmentationTransform ( GrayScaleImage<A> image,
                                 EInterpolationType type,
                                 IAddress <A> address,
                                 int brightnessOffset,
                                 double contrastScale,
                                 int dihedralAffineTransformIndex ) {
        super(image,
                type,
                address,
                brightnessOffset,
                contrastScale,
                dihedralAffineTransformIndex);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    @Override
    public
    GrayScaleImage<A> transform ( @NotNull GrayScaleImage<A> inputImage, GrayScaleImage<A> transformMatrix, EInterpolationType type ) {
        return super.transform(inputImage, transformMatrix, type);
    }
}
