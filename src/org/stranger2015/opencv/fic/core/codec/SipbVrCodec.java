package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class SipbVrCodec<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends SipCodec<N, A, G> {

    /**
     * @param scheme
     * @param action
     */
    public
    SipbVrCodec ( EPartitionScheme scheme, EncodeTask<N, A, G> action ) {
        super(scheme, action);
    }
}
