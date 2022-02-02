package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.ICompressedImage;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public
class CompressedImage extends Image implements ICompressedImage {

    public int originalImageWidth;
    public int originalImageHeight;

    /**
     * @param rows
     * @param cols
     * @param type
     */
    public
    CompressedImage ( int rows, int cols, int type ) {
        super(rows, cols, type);
    }

    /**
     * @param inputImage
     */
    public
    CompressedImage ( Image inputImage ) {

    }


    /**
     * @return
     */
    @Override
    public
    List <ImageTransform <Image>> getTransforms () {
        return t;
    }

    /**
     * @param transforms
     */
    @Override
    public
    void setTransforms ( List <ImageTransform <Image>> transforms ) {

    }
}
