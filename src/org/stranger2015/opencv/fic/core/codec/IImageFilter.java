package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;

/**
 * @param <M>
 * @param <A>
 */
public
interface IImageFilter<M extends IImage <A>, A extends Address <A>> {
    /**
     * @param image
     * @return
     */
    M filter ( M image );
}
