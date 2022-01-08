package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;

/**
 * //tracking#19710153023525 {
 *
 * @param <N>
 */
public
class SipTreeTraverser<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
        extends TreeTraverser <N, A, M> {
    /**
     * @param tree
     * @param depth
     * @param action
     */
    public
    SipTreeTraverser ( SipTree <N, A, M> tree, int depth, TreeNodeAction <N> action ) {
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
    void traverse ( TreeNode <N, A, M> node, int depth, NodeList <N, A, M> neighbors, TreeNodeAction <N> action )
            throws DepthLimitExceeded {
        super.traverse(node, depth, neighbors, action);
    }
}
