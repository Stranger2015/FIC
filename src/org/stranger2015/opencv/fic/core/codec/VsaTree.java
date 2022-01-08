package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <M>
 */
public
class VsaTree<N extends TreeNode <N, A, M>, A extends SaAddress <A>, M extends Image>
        extends Tree <N, A, M> {


    public
    VsaTree ( TreeNode <N, A, M> root, M image, TreeNodeAction <N> action ) {
        super(root, image, action);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A, M> nodeInstance ( TreeNode <N, A, M> parent, EDirection quadrant, Rect rect ) {
        return new VsaTreeNode <>();
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) VsaTreeNode.class;
    }
}
