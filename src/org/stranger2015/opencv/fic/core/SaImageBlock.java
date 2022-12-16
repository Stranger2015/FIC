package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;

/**
 * @param
 */
public
class SaImageBlock<A extends IAddress > extends ImageBlock {
    /**
     * @param image
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public
    SaImageBlock ( IImage image, int x, int y, int w, int h ) throws ValueError {
        super(image, x, y, w, h);
    }

    public
    SaImageBlock ( Mat submat ) {
        super(submat, geometry);
    }
}
