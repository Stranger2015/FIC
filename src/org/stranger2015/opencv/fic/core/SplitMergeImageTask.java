package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
@Deprecated
public
class SplitMergeImageTask<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,
        G extends BitBuffer>

        extends BidiTask <N, A, G> {
    /**
     * @param fn
     * @param scheme //     * @param tasks
     */
    SplitMergeImageTask ( String fn, EPartitionScheme scheme, ICodec <N, A, G> codec ) {
        super(fn, scheme, codec, List.of());
    }
}
