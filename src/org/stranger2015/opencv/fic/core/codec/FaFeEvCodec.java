package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 * @param <G>
 */
public
class FaFeEvCodec<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        extends Codec<N, A, M, G> {
    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     */
    protected
    FaFeEvCodec ( EPartitionScheme scheme, EncodeTask <N, A, M, G> encodeTask, DecodeTask <N, A, M, G> decodeTask ) {
        super(scheme, encodeTask, decodeTask);
    }

    /**
     * @return
     */
    @Override
    public
    IEncoder<N, A, M, G> getEncoder () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IDecoder<M,A> getDecoder () {
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
    Address <A> createAddress ( int address ) throws ValueError {
        return null;
    }
}
