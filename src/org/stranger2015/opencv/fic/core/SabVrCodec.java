package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.DecodeTask;
import org.stranger2015.opencv.fic.core.codec.EncodeTask;
import org.stranger2015.opencv.fic.core.codec.SaCodec;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class SabVrCodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends SaCodec <N, A, G> {

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     */
    protected
    SabVrCodec ( EPartitionScheme scheme, EncodeTask <N, A, G> encodeTask, DecodeTask <N, A, G> decodeTask ) {
        super(scheme, encodeTask, decodeTask);
    }
}
