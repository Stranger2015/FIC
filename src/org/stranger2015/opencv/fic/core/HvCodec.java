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
class HvCodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends Codec <N, A, G> {

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     */
    public
    HvCodec ( EPartitionScheme scheme,
              EncodeTask <N, A, G> encodeTask,
              DecodeTask <N, A, G> decodeTask ) {

        super(scheme, encodeTask, decodeTask);
    }

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     * @return
     */
    public
    ICodec <N, A, G> create ( EPartitionScheme scheme, EncodeTask <N, A, G> encodeTask, DecodeTask <N, A, G> decodeTask ) {
        return new HvCodec <>(scheme, encodeTask, decodeTask);
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
        return new HvEncoder <N, A, G>(image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder<N,A,M,G>  getEncoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder <M, A> getDecoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return 2;
    }

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress <A> createAddress ( int address ) throws ValueError {
        return new DecAddress <>(address);
    }
}
