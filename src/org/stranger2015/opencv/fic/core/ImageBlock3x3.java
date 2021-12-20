package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
class ImageBlock3x3 extends ImageBlock<Image>{
    /**
     * @param type
     */
    public
    ImageBlock3x3 ( int type ) {
        super(3, 3, type);
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param type
     */
    public
    ImageBlock3x3 ( int x, int y, int width, int height, int type ) {
        super(x, y, width, height, type);
    }
}
