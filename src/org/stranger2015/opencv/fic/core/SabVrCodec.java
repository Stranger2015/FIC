package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.DecodeTask;
import org.stranger2015.opencv.fic.core.codec.EncodeTask;
import org.stranger2015.opencv.fic.core.codec.SaCodec;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class SabVrCodec<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends SaCodec <N> {

    /**
     * @param scheme
     * @param encodeTask
     * @param decodeTask
     */
    protected
    SabVrCodec ( EPartitionScheme scheme, EncodeTask <N> encodeTask, DecodeTask <N> decodeTask ) {
        super(scheme, encodeTask, decodeTask);
    }
}
