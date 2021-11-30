package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.AffineTransforms;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 * @param <M>
 * @param <C>
 */
public
class SearchlessEncoder<M extends Image, C extends CompressedImage> extends Encoder<M, C>{
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
    M randomTransform ( M image, ImageTransform <M, C> transform ) {
        return null;
    }

    @Override
    public
    M applyTransform ( M image, ImageTransform <M, C> transform ) {
        return null;
    }

    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M, C> transform ) {
        return null;
    }

    @Override
    public
    List <ImageTransform <M, C>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return null;
    }

    @Override
    public
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return null;
    }
}
