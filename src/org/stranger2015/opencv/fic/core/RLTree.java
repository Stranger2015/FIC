package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

public
class RLTree<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends Tree <N, A, M, G> {
    /**
     * Constructs a new object.
     *
     * @param root
     * @param image
     * @param action
     */
    public
    RLTree ( TreeNode <N, A, M, G> root, M image, TreeNodeAction <N, A, M, G> action ) {
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
    RLTree ( TreeNode <N, A, M, G> root, M image, TreeNodeAction <N, A, M, G> action, Rect area, int depth ) {
        super(root, image, action, area, depth);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    public
    TreeNode <N, A, M, G> nodeInstance ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect )
            throws ValueError {
        return new RLTreeNode <>(parent, quadrant, rect);
    }

    @SuppressWarnings("unchecked")
    @Override
    public
    Class <? extends TreeNode <N, A, M, G>> getNodeClass ( TreeNode <N, A, M, G> clazz ) {
        return (Class <? extends TreeNode <N, A, M, G>>) clazz.getClass();
    }
}
