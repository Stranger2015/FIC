package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

public
class SearchlessEncoder<N extends TreeNodeBase <N, A>, M extends Image, A extends Address <A, ?>>
        extends Encoder <N, M, A> {

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
    M randomTransform ( M image, ImageTransform <M> transform ) {
        return null;//todo
    }

    @Override
    public
    M applyTransform ( M image, ImageTransform <M> transform ) {
        return null;//todo
    }

    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M> transform ) {
        return null;//todo
    }

    @Override
    public
    List <ImageTransform <M>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
    }

    @Override
    public
    List <ImageBlock <M>> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
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
