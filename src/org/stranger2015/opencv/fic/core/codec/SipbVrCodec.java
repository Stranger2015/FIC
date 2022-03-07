package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

public
class SipbVrCodec<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>, G extends BitBuffer>
        extends SipCodec<N, A, M, G> {

    /**
     * @param scheme
     * @param action
     */
    public
    SipbVrCodec ( EPartitionScheme scheme, EncodeAction action ) {
        super(scheme, action);
    }
}
