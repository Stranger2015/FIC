package org.stranger2015.opencv.fic.core.codec;


import org.stranger2015.opencv.fic.core.IImage;

/**
 *
 */
public
interface IDecoder<M extends IImage> {
    /**
     *
     */
    M decode ();
}
