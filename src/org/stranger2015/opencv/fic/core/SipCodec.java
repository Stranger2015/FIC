package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class SipCodec<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends Codec <N, A, M, G> {

    /**
     * @param scheme
     * @param action
     */
    public
    SipCodec ( EPartitionScheme scheme, EncodeAction action ) {
        super(scheme, action);
    }

    /**
     * @param scheme
     * @param action
     * @return
     */
    @Override
    public
    Codec <N, A, M, G> create ( EPartitionScheme scheme, EncodeAction action ) {
        return new SipCodec <>(scheme, action);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    IEncoder <N, A, M, G> getEncoder ( M image, Size rangeSize, Size domainSize ) {
        return new SipEncoder <>(image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder <N, A, M, G> getEncoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <M> getDecoder () {
        return null ;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 3;
    }//9 ???
}

