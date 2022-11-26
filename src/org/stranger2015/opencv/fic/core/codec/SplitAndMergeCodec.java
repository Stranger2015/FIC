package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class SplitAndMergeCodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Codec <N, A, G> {
//    /**
//     * @param scheme
//     * @param paramTypes
//     * @param params
//     */
//    protected
//    SplitAndMergeCodec ( EPartitionScheme scheme, Class <?>[] paramTypes, Object... params ) {
//        super(scheme, paramTypes, params);
//    }

    /**
     * @param scheme
     * @param encoder
     * @param decoder
     */
    public
    SplitAndMergeCodec ( EPartitionScheme scheme, IEncoder <N, A, G> encoder, IDecoder <N, A, G> decoder ) {
        super(scheme, encoder, decoder);
    }

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    IAddress <A> createAddress ( int address ) throws ValueError {
        return null;
    }
}
