package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 */
public
class SipCodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Codec <N, A, G> {

    /**
     * @param scheme
     * @param encoder
     * @param decoder
     */
    public
    SipCodec ( EPartitionScheme scheme, IEncoder <N, A, G> encoder, IDecoder <N, A, G> decoder ) {
        super(scheme, encoder, decoder);
    }

    public
    SipCodec ( EPartitionScheme scheme, EncodeTask<N, A, G> action ) {
        super(scheme, action);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    public
    IEncoder <N, A, G> getEncoder ( IImage<A> image, IIntSize rangeSize, IIntSize domainSize ) {
        return new SipEncoder <>(image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder <N, A, G> getEncoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <N,A,G> getDecoder () {
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

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress <A> createAddress ( int address ) throws ValueError {
        return new SipAddress <>(address);
    }
}