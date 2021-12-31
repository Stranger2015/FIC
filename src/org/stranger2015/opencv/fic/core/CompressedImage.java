package org.stranger2015.opencv.fic.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public
class CompressedImage extends Image {

    public int originalImageWidth;
    public int originalImageHeight;

    public List <ImageBlock<Image>> rangeBlocks = new ArrayList <>();
    public List <ImageBlock<Image>> domainBlocks = new ArrayList <>();

    /**
     * @param rows
     * @param cols
     * @param type
     */
    public
    CompressedImage ( int rows, int cols, int type ) {
        super(rows, cols, type, pixelData);
    }

    public
    CompressedImage ( Image inputImage ) {

    }
}
