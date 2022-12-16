package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
public
class SipbVrCodec<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends SipCodec<N> {

    /**
     * @param scheme
     * @param action
     */
    public
    SipbVrCodec ( EPartitionScheme scheme, EncodeTask<N> action ) {
        super(scheme, action);
    }
}
