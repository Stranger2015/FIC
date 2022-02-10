package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class CompressedSipImage extends SipImage implements ICompressedImage {

   public ICompressedImage image;

    /**
     * @param input
     * @param pixels
     * @param <M>
     */
    public
    <M extends IImage> CompressedSipImage ( M input, Pixel[] pixels, ICompressedImage image ) {
        super(input, pixels);
        this.image = image;
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <IImage>> getTransforms () {
        return image.getTransforms();
    }

    /**
     * @param transforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <IImage>> transforms ) {

    }
}
