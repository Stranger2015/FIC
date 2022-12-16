package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

public
class RLTree<N extends TreeNode <N>>
        extends Tree <N> {
    /**
     * Constructs a new object.
     *
     * @param root
     * @param image
     * @param action
     */
    public
    RLTree ( TreeNode <N> root, IImage image, TreeNodeTask <N> action ) {
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
    RLTree ( TreeNode <N> root, IImage image, TreeNodeTask <N> action, Rect area, int depth ) {
        super(root, image, action, area, depth);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    public
    TreeNode <N> nodeInstance ( TreeNode <N> parent, EDirection quadrant, Rect rect )
            throws ValueError {
        return new RLTreeNode <>(parent, quadrant, rect);
    }

    @SuppressWarnings("unchecked")
//    @Override
    public
    Class <? extends TreeNode <N>> getNodeClass ( TreeNode <N> clazz ) {
        return (Class <? extends TreeNode <N>>) clazz.getClass();
    }
}
