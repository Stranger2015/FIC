package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;

/**
 
 * @param <A>
 */
public
class NoneFilter</*M extends IImage<A>,*/ A extends Address<A>>
        implements IImageFilter <M, A> {
    /**
     * @param image
     * @return
     */
    @Override
    public
    GrayScaleImage <A> filter ( GrayScaleImage <A> image ) {
        return image;
    }
}
