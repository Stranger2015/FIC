package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;

/**
 * @param <A>
 */
public
class SaImageBlock<A extends IAddress <A>> extends ImageBlock {
    /**
     * @param image
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public
    SaImageBlock ( IImage <A> image, int x, int y, int w, int h ) throws ValueError {
        super(image, x, y, w, h);
    }

    public
    SaImageBlock ( Mat submat ) {
        super(submat, geometry);
    }
}
