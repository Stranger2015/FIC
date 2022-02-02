package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class SipCodec<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends Codec <N, A, M> {

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
    Codec <N, A, M> create ( EPartitionScheme scheme, EncodeAction action ) {
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
    Encoder <N, A, M> getEncoder ( M image, Size rangeSize, Size domainSize ) {
        return new SipEncoder <>(image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder <N, A, M> getEncoder () {
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
    }
}

