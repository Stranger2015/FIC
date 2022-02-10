package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.IImage;


/**
 *
 */
public
class SquareImageBlockGenerator extends ImageBlockGenerator {

    /**
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    SquareImageBlockGenerator ( IEncoder <?, ?, ?> encoder, IImage image, Size rangeSize, Size domainSize ) {
        super(encoder, image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    SquareImageBlockGenerator newInstance () {
        return this;
    }
}
