package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.IConverter;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.utils.ImageUtils;

/**
 *
 */
public
class ImageToSipImageConverter implements IConverter <Image, SipImage> {
    /**
     * @param input
     * @return
     */
    @Override
    public
    SipImage map ( Image input ) {
        return ImageUtils.imageToSipImage(input);
    }

    /**
     * @param output
     * @return
     */
    @Override
    public
    Image unmap ( SipImage output ) {
        Image input = null;

        return input;
    }
}
