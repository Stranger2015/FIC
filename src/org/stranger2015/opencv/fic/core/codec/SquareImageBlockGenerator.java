package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;

/**
 *
 */
public
class SquareImageBlockGenerator extends ImageBlockGenerator {

    /**
     * @param rangeSize
     * @param domainSize
     */
    public
    SquareImageBlockGenerator ( Size rangeSize, Size domainSize ) {
        super(rangeSize, domainSize);
    }
}
