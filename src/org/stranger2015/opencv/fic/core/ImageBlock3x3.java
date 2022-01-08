package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

/**
 *
 */
public
class ImageBlock3x3 extends ImageBlock {

    protected int centerX;
    protected int centerY;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public
    ImageBlock3x3 ( int x, int y, int width, int height ) {
        super(x, y, width, height);

        centerX = x + width / 2;
        centerY = y + height / 2;

    }

    /**
     * @param image
     * @param rect
     */
    @SuppressWarnings("unchecked")
    public
    ImageBlock3x3 ( Image image, Rect rect ) {
        this(rect.x, rect.y, rect.width, rect.height);
        this.image = (Image) image.submat(rect);
    }

    public
    ImageBlock3x3 ( int rows, int cols, int type ) {
        //todo
        super(rows, cols, type);
    }

    public
    ImageBlock3x3 ( Image image, int x, int y, int w, int h ) {
        this(x, y, w, h);
        this.image = (Image) image.submat(new Rect(x, y, w, h));
    }
}
