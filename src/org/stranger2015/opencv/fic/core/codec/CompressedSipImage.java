package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class CompressedSipImage extends SipImage implements ICompressedImage {

    ICompressedImage image;

    /**
     * @param input
     * @param addresses
     * @param <M>
     */
    public
    <M extends Image> CompressedSipImage ( M input, int[] addresses, ICompressedImage image ) {
        super(input, addresses);
        this.image = image;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <Image>> getTransforms () {
        return image.getTransforms();
    }

    /**
     * @param transforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <Image>> transforms ) {
        image.setTransforms(transforms);
    }
}
