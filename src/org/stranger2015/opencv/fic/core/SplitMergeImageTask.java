package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ICodec;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param
 
 * @param <G>
 */
@Deprecated
public
class SplitMergeImageTask<N extends TreeNode <N>, A extends IAddress , /* IImage extends IImage */,
        G extends BitBuffer>

        extends BidiTask <N> {
    /**
     * @param fn
     * @param scheme //     * @param tasks
     */
    SplitMergeImageTask ( String fn, EPartitionScheme scheme, ICodec <N> codec ) {
        super(fn, scheme, codec, List.of());
    }
}
