package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.DomainPool;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.util.List;

/**
 * @param <N>
 */
public
class BuildAction<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends TreeNodeAction <N, A, M> {
    /**
     * @param domainPool
     */
    public
    BuildAction ( DomainPool domainPool ) {
        super(domainPool, List.of());
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
