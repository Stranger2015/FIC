package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 *
 */
public
class BinTree<N extends TreeNode <N, A,M>, A extends Address <A>,  M extends Image>
        extends Tree <N, A,M> {

    /**
     * Constructs a new object.
     *
     * @param image
     */
    public
    BinTree ( M image, TreeNodeAction <N> action ) {
        this(null, image, action);
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    BinTree ( TreeNode <N, A,M> root, M image, TreeNodeAction <N> action ) {
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
    TreeNode <N, A, M> nodeInstance ( TreeNode <N, A, M> parent, EDirection quadrant, Rect rect ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) BinTreeNode.class;
    }
}
