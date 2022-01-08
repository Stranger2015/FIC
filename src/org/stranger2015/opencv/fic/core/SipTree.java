package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.VsaTree;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class SipTree<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
        extends VsaTree <N, A, M> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    SipTree (TreeNode <N, A, M> root, M image, TreeNodeAction <N> action) {
        super(root, image, action);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) SipTreeNode.class;
    }
}
