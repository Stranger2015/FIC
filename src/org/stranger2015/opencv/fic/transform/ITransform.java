package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <A>
 * @param <G>
 */
public
interface ITransform< A extends IAddress <A>, G extends BitBuffer> {

    /**
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    default
    IImage <A> transform ( IImage<A> inputImage,
                           Mat transformMatrix,
                           EInterpolationType type ) {

        return warpAffine(inputImage, transformMatrix, type);
    }

    /**
     * @param inputImage
     * @return
     */
    IImage <A> warpAffine ( IImage <A> inputImage,
                           Mat transformMatrix,
                            EInterpolationType interpolationType );

    /**
     * @return
     */
    IAddress <A> getAddress () throws InstantiationException, IllegalAccessException;

    /**
     * @param bb
     * @param transform
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    void writeBits ( G bb, ImageTransform <A, G> transform ) throws InstantiationException, IllegalAccessException;

    /**
     * @param bb
     * @return
     * @throws ValueError
     */
    ImageTransform <A, G> readBits ( G bb ) throws ValueError;
}
