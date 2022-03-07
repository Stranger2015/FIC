package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * //tracking#19710153023525 {
 *
 * @param <N>
 */
public
class SipTreeTraverser<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
        G extends BitBuffer>

        extends TreeTraverser <N, A, M, G> {
    /**
     * @param tree
     * @param depth
     * @param action
     */
    public
    SipTreeTraverser ( SipTree <N, A, M, G> tree, int depth, TreeNodeAction <N, A, M, G> action ) {
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
    void traverse ( TreeNode <N, A, M, G> node, int depth, NodeList <N, A, M, G> neighbors, TreeNodeAction <N, A, M, G> action )
            throws DepthLimitExceeded {
        super.traverse(node, depth, neighbors, action);
    }
}
