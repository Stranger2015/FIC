package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.IImage;

/**
 *
 */
public
class SaImageBlockGenerator extends ImageBlockGenerator {
    /**
     * @param rangeSize
     * @param domainSize
     */
    public
    SaImageBlockGenerator ( IEncoder <?, ?, ?> encoder, IImage image, Size rangeSize, Size domainSize ) {
        super(encoder, image, rangeSize, domainSize);
    }
}
