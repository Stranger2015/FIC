package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Image;

/**
 *
 */
public
interface IDecoder<M extends Image> {
    /**
     *
     */
    M decode ();
}
