package org.stranger2015.opencv.fic.core;


import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.util.List;

/**
 * @param <N>
 */
public
class BuildAction<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage>
        extends TreeNodeAction <N, A, M> {
    /**
     * @param domainPool
     */
    public
    BuildAction (List<ImageBlock> domainPool ) {
        super(domainPool, new NodeList <>());
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
