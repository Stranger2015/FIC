package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.nio.ByteBuffer;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class CsDpEncoder<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer>
        extends Encoder <N, A, M, G> {
    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    CsDpEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    M encode ( M image ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M randomTransform ( M image, ImageTransform <M, A, G> transform ) {
        return null;
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
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <ImageTransform <M, A, G>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
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
        return null;//todo
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess () {

    }

    /**
     *
     */
    @Override
    public
    void onProcess () {

    }

    /**
     *
     */
    @Override
    public
    void onPostprocess () {

    }
}
