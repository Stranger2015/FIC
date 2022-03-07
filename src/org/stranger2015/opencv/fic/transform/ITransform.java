package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <M>
 * @param <A>
 * @param <G>
 */
public
interface ITransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer> {

    /**
     *
     * @param inputImage
     * @param transformMatrix
     * @param type
     * @return
     */
    M transform( M inputImage, Mat transformMatrix, EInterpolationType type/*, Address<A> address */);

    /**
     *
     * @param inputImage
     * @return
     */
    M warpAffine ( M inputImage, M transformMatrix, EInterpolationType interpolationType/*, Address<A> address*/ );

    /**
     *
     *
     * @return
     */
    Address <A> getAddress () throws InstantiationException, IllegalAccessException;

    /**
     *
     *
     * @param bb
     * @param transform
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    void writeBits ( G bb, ImageTransform <M, A, G> transform) throws InstantiationException, IllegalAccessException;

    /**
     *
     *
     * @param bb
     * @return
     * @throws ValueError
     */
    ImageTransform <M, A, G> readBits ( G bb) throws ValueError;
}
