package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public
class ColorTransform</* M extends IImage <A> */, A extends IAddress <A>, G extends BitBuffer>
        implements ITransform <A, G> {
    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    Mat warpAffine ( Mat inputImage, Mat transformMatrix, EInterpolationType interpolationType ) {
        throw new UnsupportedOperationException();
    }

    /**
     * @return
     */
    @Override
    public
    IAddress <A> getAddress () throws InstantiationException, IllegalAccessException {
        return null;
    }

    /**
     * @param bb
     * @param transform
     */
    @Override
    public
    void writeBits ( G bb, ImageTransform <A, G> transform ) {

    }

    /**
     * @param bb
     * @return
     */
    @Override
    public
    ImageTransform <A, G> readBits ( G bb ) {
        return null;
    }
}
