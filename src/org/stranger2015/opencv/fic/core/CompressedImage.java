package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.ICompressedImage;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *
 */
public
class CompressedImage<A extends Address <A>> extends Image <A> implements ICompressedImage <A> {

    public int originalImageWidth;
    public int originalImageHeight;

    private List <ImageTransform <?, A, ?>> transforms;

    /**
     * @param rows
     * @param cols
     * @param type
     */
    public
    CompressedImage (  IImage<A>  image, int rows, int cols, int type ) {
        super(image, rows, cols, type);
    }

    /**
     * @param inputImage
     */
    public
    CompressedImage (  IImage<A>  inputImage ) {
        super(inputImage);
    }

    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <?, A, ?>> getTransforms () {
        return transforms;
    }

    /**
     * @param transforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <?, A, ?>> transforms ) {
        this.transforms = transforms;
    }

    @Override
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }
}
