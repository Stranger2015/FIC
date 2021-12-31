package org.stranger2015.opencv.fic.core;

/**
 * @param <N>
 */
public
class SipTreeTraverser<N extends SipTreeNode<N,?>> extends TreeTraverser<N> {
    /**
     * @param tree
     * @param depth
     * @param action
     */
    public
    SipTreeTraverser ( Tree<N,Image,?> tree, int depth, TreeNodeAction<N> action ) {
        super(tree, depth, action);
    }

    /**
     * Perform  a neighbored traversal, visiting the  children in  the  order  specified  by  childorder.
     *
     * @param node
     * @param depth
     * @param neighbors
     * @param action
     * @throws DepthLimitExceeded
     */
    @Override
    public
    void traverse ( TreeNodeBase <N, ?> node, int depth, NodeList <N> neighbors, TreeNodeAction <N> action )
            throws DepthLimitExceeded {
        super.traverse(node, depth, neighbors, action);
    }
}
