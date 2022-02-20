package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class SearchlessEncoder<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
        G extends BitBuffer>
        extends Encoder <N, A, M, G> {

    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    SearchlessEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    @Override
    public
    M randomTransform ( M image, ImageTransform <M, A, G> transform ) {
        return null;//todo
    }

    @Override
    public
    M applyTransform ( M image, ImageTransform <M, A, G> transform ) {
        return null;//todo
    }

    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M, A, G> transform ) {
        return null;//todo
    }

    @Override
    public
    List <ImageTransform <M, A, G>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
    }

    @Override
    public
    List <ImageBlock<A>> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo//todo
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
