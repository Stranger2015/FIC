package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 
 * @param <A>
 */
public
class NoneFilter<A extends IAddress <A>>
        implements IImageFilter <A> {
    /**
     * @param image
     * @return
     */
    @Override
    public
    IImage <A> filter ( IImage <A> image ) {
        return image;
    }
}
