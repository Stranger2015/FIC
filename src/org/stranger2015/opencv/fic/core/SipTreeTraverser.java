package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * //tracking#19710153023525 {
 *
 * @param <N>
 */
public
class SipTreeTraverser<N extends TreeNode <N>, A extends IAddress , IImage extends IImage,
        G extends BitBuffer>

        extends TreeTraverser <N> {
    /**
     * @param tree
     * @param depth
     * @param action
     */
    public
    SipTreeTraverser ( SipTree <T, T1> tree, int depth, TreeNodeTask <N> action ) {
        super(tree, depth, action);
    }

    /**
     * Perform  a neighbored traversal, visiting the  children in  the  order  specified  by  child order.
     *
     * @param node
     * @param depth
     * @param neighbors
     * @param action
     * @throws DepthLimitExceeded
     */
    @Override
    public
    void traverse ( TreeNode <N> node, int depth, List <TreeNode<N>> neighbors, TreeNodeTask <N> action )
            throws DepthLimitExceeded {

        super.traverse(node, depth, neighbors, action);
    }
}
