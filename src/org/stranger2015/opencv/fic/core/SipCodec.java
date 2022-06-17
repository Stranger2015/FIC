package org.stranger2015.opencv.fic.core;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.*;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 */
public
class SipCodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, M extends IImage<A>, G extends BitBuffer>
        extends Codec <N, A, G> {

    /**
     * @param scheme
     */
    public
    SipCodec ( EPartitionScheme scheme, Class<?>[]paramTypes,Object...params  ) {
        super(scheme,paramTypes, params);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
//    @Override
    public
    IEncoder <N, A, G> getEncoder ( M image, Size rangeSize, Size domainSize ) {
        return new SipEncoder <N, A, G> (image, rangeSize, domainSize);
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
    IDecoder <M,A> getDecoder () {
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

