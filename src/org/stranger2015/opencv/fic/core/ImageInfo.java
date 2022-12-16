package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.EtvColorSpace;

/**
 *
 */
public
class ImageInfo extends IntSize {
    private EtvColorSpace originalColorSpace;

    /**
     * @param width
     * @param height
     * @param originalImageWidth
     * @param originalImageHeight
     * @param originalColorSpace
     */
    public
    ImageInfo ( int width,
                int height,
                int originalImageWidth,
                int originalImageHeight,
                EtvColorSpace originalColorSpace
    ) {
        super(width, height, originalImageWidth, originalImageHeight);

        this.originalColorSpace = originalColorSpace;
    }

    /**
     * @param width
     * @param height
     * @param originalColorSpace
     */
    public
    ImageInfo ( int width, int height, EtvColorSpace originalColorSpace ) {
        super(width, height);
        this.originalColorSpace = originalColorSpace;
    }

    /**
     * @return
     */
    public
    void setOriginalColorSpace ( EtvColorSpace originalColorSpace ) {
        this.originalColorSpace = originalColorSpace;
    }

    /**
     * @return
     */
    public
    EtvColorSpace getOriginalColorSpace () {
        return originalColorSpace;
    }
}
