package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.DomainPool;

import java.util.List;

/**
 * @param <N>
 */
public
class BuildAction<N extends TreeNodeBase <N,?>> extends TreeNodeAction<N> {
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
