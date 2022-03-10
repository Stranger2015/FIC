package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

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
    <N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
    IDecoder <M,A> create ( String decoderClassName ) {
        return null;
    }

    /**
     *
     */
    public
    M decode () {
        return null;
    }

    /**
     *
     */
    @Override
    public
    void onCreateCodec () {

    }

    /**
     *
     */
    @Override
    public
    void onEncode () {

    }

    /**
     *
     */
    @Override
    public
    void onDecode () {

    }

    /**
     *
     */
    @Override
    public
    void onDecompress () {

    }
}