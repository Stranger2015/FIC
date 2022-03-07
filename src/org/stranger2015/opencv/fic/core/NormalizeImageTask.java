package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 * @param <G>
 */
public
class NormalizeImageTask<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer> extends BidiTask<N, A, M, G>{


    /**
     * @param filename
     * @param scheme
     * @param list
     */
    public
    NormalizeImageTask ( String filename, EPartitionScheme scheme, List<Task<N, A, M, G>> list ) {
        super(filename, scheme, list);
    }
}
