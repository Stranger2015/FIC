package org.stranger2015.opencv.fic.core;


import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 */
public
class BuildAction<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends TreeNodeAction <N, A, M, G> {
    /**
     * @param domainPool
     */
    public
    BuildAction (List<ImageBlock<A>> domainPool ) {
        super(domainPool, new ArrayList <>());
    }

    /**
     * Performs this operation on the given argument.
     *
     * @param n the input argument
     */
    @Override
    public
    void accept ( N n ) {
        super.accept(n);
    }
}
