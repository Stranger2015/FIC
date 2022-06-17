package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 
 * @param <A>
 */
public
interface IImageFilter</* M extends IImage <A> */, A extends IAddress <A>> {
    /**
     * @param image
     * @return
     */
    IImage<A> filter ( IImage <A> image );
}
