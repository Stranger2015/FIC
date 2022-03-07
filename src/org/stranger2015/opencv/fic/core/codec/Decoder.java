package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;

/**
 *
 */
public
class Decoder<M extends IImage <A>, A extends Address <A>> implements IDecoder <M, A> {

    /**
     *
     */
    public
    Decoder () {

    }

    public static
    Decoder <?, ?> create ( String decoderClassName ) {
        return null;
    }

    /**
     *
     */
    public
    M decode () {
        return null;
    }
}