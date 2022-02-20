package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @param <N>
 * @param <M>
 */
public
class SipEncoder<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends SaEncoder <N, A, M, G> {

    /**
     *
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SipEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    /**
     *
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 9;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M randomTransform ( M image, ImageTransform <M, A, G> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyTransform ( M image, ImageTransform <M, A, G> transform ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M, A, G> transform ) {
        return null;//todo
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess () {
        super.onPreprocess();
    }

    /**
     *
     */
    @Override
    public
    void onProcess () {
        super.onProcess();
    }

    /**
     *
     */
    @Override
    public
    void onPostprocess () {
        super.onPostprocess();
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <ImageTransform <M, A, G>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return new ArrayList <>();
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    M encode ( M image ) {
        return super.encode(image);
    }

    /**
     *
     * @param encoder
     * @param image
     * @param rangeSize
     * @return
     */
    @Override
    public
    SipImageBlockGenerator<N,A,M,G> createBlockGenerator ( IEncoder <N, A, M, G> encoder, M image, Size rangeSize, Size domainSize ) {
        return new SipImageBlockGenerator<>(
                encoder,
                image,
                rangeSize,
                domainSize);
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return Collections.emptyList(); //todo
    }
}