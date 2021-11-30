package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class VsaEncoder extends Encoder<Image, CompressedImage> {
    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    Image randomTransform ( Image image, ImageTransform <Image, CompressedImage> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    Image applyTransform ( Image image, ImageTransform <Image, CompressedImage> transform ) {
        return null;
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    Image applyAffineTransform ( Image image, AffineTransform <Image, CompressedImage> transform ) {
        return null;
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
    List <ImageTransform <Image, CompressedImage>> compress ( Image image, int sourceSize, int destinationSize, int step ) {
        return null;
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
    List <ImageBlock> generateAllTransformedBlocks ( Image image, int sourceSize, int destinationSize, int step ) {
        return null;
    }
}
