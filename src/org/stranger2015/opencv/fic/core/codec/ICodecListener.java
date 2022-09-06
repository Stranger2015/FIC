package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
interface ICodecListener<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends IListener <ICodec <N, A, G>> {

//    /**
//     *
//     */
//    void onCreated ( ICodec <N, A, G> codec );

    /**
     *
     */
    default
    void onCodecCreated ( ICodec <N, A, G> codec ) {
        onCreated(codec);
    }
}
