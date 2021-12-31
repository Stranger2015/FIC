package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.codec.SipImage;

/**
 *
 */
public
class ImageBlock3x3<M extends Image> extends ImageBlock <M> {

    protected int[] addresses;

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
        centerX = x + width/2;
        centerY = y + height/2;

    }

    /**
     * @param image
     * @param rect
     */
    @SuppressWarnings("unchecked")
    public
    ImageBlock3x3 ( M image, Rect rect ) {
        this(rect.x, rect.y, rect.width, rect.height);
        this.image = (M) image.submat(rect);
    }
}
