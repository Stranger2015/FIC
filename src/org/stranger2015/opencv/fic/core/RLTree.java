package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

public
class RLTree<N extends RLTreeNode <N, A>, M extends Image, A extends Address <A,?>>
        extends Tree <N, M, A> {
    /**
     * Constructs a new object.
     *
     * @param root
     * @param image
     * @param action
     */
    public
    RLTree ( RLTreeNode <N, A> root, M image, TreeNodeAction <N> action ) {
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
    RLTree ( RLTreeNode <N, A> root, M image, TreeNodeAction <N> action, Rect area, int depth ) {
        super(root, image, action, area, depth);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
//    @Override
    public
    TreeNodeBase <N, A> nodeInstance ( TreeNodeBase <N, A> parent, EDirection quadrant, Rect rect ) {
        return new RLTreeNode <N, A>(parent, quadrant, rect);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A> nodeInstance ( TreeNode <N, A> parent, EDirection quadrant, Rect rect ) throws ValueError {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) RLTreeNode.class;
    }
}
