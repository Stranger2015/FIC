package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;

/**
 *
 */
public
class SipImageBlockGenerator extends SquareImageBlockGenerator {

    /**
     * @param rangeSize
     * @param domainSize
     */
    public
    SipImageBlockGenerator ( Size rangeSize, Size domainSize ) {
        super(rangeSize, domainSize);
    }
}
