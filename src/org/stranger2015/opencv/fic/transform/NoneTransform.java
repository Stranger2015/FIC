package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.ValueError;

/**
 * The 'None' transform -- makes no change to the given image.
 * This is useful to traverse the transforms without special casing
 * the comparison with the normal (non-transformed) image.
 */
public
class NoneTransform extends ImageTransform {

    /**
     * @param image
     */
    public
    NoneTransform ( IImage image,
                    EInterpolationType type,
                    IAddress address,
                    int brightnessOffset,
                    double contrastScale,
                    int dihedralAffineTransformerIndex ) throws ValueError {

        super(
                (IImageBlock) image,
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
    @SuppressWarnings({"rawtype"})
    @Override
    public
    IImage transform ( IImage inputImage, Mat transformMatrix, EInterpolationType type ) {
        return warpAffine(inputImage, transformMatrix, type);
    }
}
