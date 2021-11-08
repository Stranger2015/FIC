package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.DomainPool;

public
class BuildAction<N extends TreeNode<N>> extends TreeNodeAction<N> {
    public
    BuildAction ( DomainPool domainPool ) {
        super(domainPool);
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
