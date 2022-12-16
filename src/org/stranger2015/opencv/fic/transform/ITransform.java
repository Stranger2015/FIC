package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.codec.IConstants;

/**
 * @param
 * @param <G>
 */
public
interface ITransform extends IConstants {

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    default
    IImage transform ( IImage inputImage,
                       Mat transformMatrix,
                       EInterpolationType type ) {

        return warpAffine(inputImage, transformMatrix, type);
    }

    /**
     * @param inputImage
     * @return
     */
    IImage warpAffine ( IImage inputImage,
                        Mat transformMatrix,
                        EInterpolationType interpolationType );

    /**
     * @return
     */
    IAddress getAddress () throws InstantiationException, IllegalAccessException;
}
