package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

/**
 *
 */
public
interface IDecoder<C extends CompressedImage, M extends Image> {
    /**
     *
     */
    M decode ();
}
