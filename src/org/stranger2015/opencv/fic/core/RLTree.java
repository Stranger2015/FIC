package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

public
class RLTree<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends Tree <N, A, M> {
    /**
     * Constructs a new object.
     *
     * @param root
     * @param image
     * @param action
     */
    public
    RLTree ( TreeNode <N, A, M> root, M image, TreeNodeAction <N, A, M> action ) {
        this(root, image, action, DEFAULT_BOUNDING_BOX, DEFAULT_DEPTH);
    }

    /**
     * Constructs a new object.
     *
     * @param root
     * @param image
     * @param action
     * @param area
     * @param depth
     */
    protected
    RLTree ( TreeNode <N, A, M> root, M image, TreeNodeAction <N, A, M> action, Rect area, int depth ) {
        super(root, image, action, area, depth);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    public
    TreeNode <N, A, M> nodeInstance ( TreeNode <N, A, M> parent, EDirection quadrant, Rect rect )
            throws ValueError {
        return new RLTreeNode <>(parent, quadrant, rect);
    }

    @SuppressWarnings("unchecked")
    @Override
    public
    Class <? extends TreeNode <N, A, M>> getNodeClass ( TreeNode <N, A, M> clazz ) {
        return (Class <? extends TreeNode <N, A, M>>) clazz.getClass();
    }
}
