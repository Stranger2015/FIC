package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class SaCodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends Codec<N, A, G> {

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     */
    protected
    SaCodec ( EPartitionScheme scheme, EncodeTask <N, A, G> encodeTask, DecodeTask <N, A, G> decodeTask ) {
        super(scheme, encodeTask, decodeTask);
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
        return null;
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
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 7;
    }

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress <A> createAddress ( int address ) throws ValueError {
        return new SaAddress <>(address);
    }
}
