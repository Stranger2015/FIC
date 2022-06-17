package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * The 'None' transform -- makes no change to the given image.
 * This is useful to traverse the transforms without special casing
 * the comparison with the normal (non-transformed) image.
 */
public class NoneTransform</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform<A, G> {

    /**
     * @param image
     */
    public
    NoneTransform ( IImage<A> image,
                    EInterpolationType type,
                    IAddress <A> address,
                    int brightnessOffset,
                    double contrastScale,
                    int dihedralAffineTransformerIndex ) {

        super(
                image,
                type,
                address,
                brightnessOffset,
                contrastScale,
                dihedralAffineTransformerIndex);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtype"})
    @Override
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType type ) {
        return (M) warpAffine(inputImage.getMat(), transformMatrix.getMat(),type);
    }
}
