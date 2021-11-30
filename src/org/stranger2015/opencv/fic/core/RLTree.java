package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.function.Consumer;

public
class RLTree<N extends RLTreeNode <N>, M extends Mat> extends Tree <N, M> {
    /**
     * Constructs a new object.
     *
     * @param root
     * @param image
     * @param action
     */    public
    RLTree (RLTreeNode <N> root, M image, TreeNodeAction <N> action) {
        this(root, image, action,DEFAULT_BOUNDING_BOX, DEFAULT_DEPTH);
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
    RLTree ( RLTreeNode<N> root, M image, TreeNodeAction<N> action, Rect area, int depth ) {
        super(root, image, action, area, depth);
    }


     /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N> nodeInstance ( TreeNode <N> parent, Direction quadrant, Rect rect ) {
        return new RLTreeNode <N>(parent, quadrant, rect);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) RLTreeNode.class;
    }
}
