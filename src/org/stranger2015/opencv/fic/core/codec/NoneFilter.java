package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IImage;

/**
 
 * @param
 */
public
class NoneFilter
        implements IImageFilter  {
    /**
     * @param image
     * @return
     */
    @Override
    public
    IImage filter ( IImage image ) {
        return image;
    }
}
