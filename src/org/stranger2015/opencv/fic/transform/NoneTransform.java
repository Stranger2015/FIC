package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;

/**
 * The 'None' transform -- makes no change to the given image.
 * This is useful to traverse the transforms without special casing
 * the comparison with the normal (non-transformed) image.
 */
public class NoneTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends ImageTransform<M, A, G> {

    /**
     * @param image
     */
    public
    NoneTransform ( M image,
                    EInterpolationType type,
                    IAddress <A> address,
                    int brightnessOffset,
                    double contrastScale,
                    int dihedralAffineTransformerIndex ) {

        super(image,
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

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    Mat warpAffine ( Mat inputImage, Mat transformMatrix, EInterpolationType interpolationType ) {
        return null;
    }
}
