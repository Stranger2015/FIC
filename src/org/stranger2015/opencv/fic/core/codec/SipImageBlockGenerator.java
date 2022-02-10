package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.IImage;


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
    SipImageBlockGenerator ( IEncoder <?, ?, ?> encoder, IImage image, Size rangeSize, Size domainSize ) {
        super(encoder, image, rangeSize, domainSize);
    }

    /**
     *
     * @return
     */
    @Override
    public
    SipImageBlockGenerator newInstance () {
        return this;
    }
}
