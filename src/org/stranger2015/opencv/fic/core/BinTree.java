package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

/**
 *
 */
public
class BinTree<N extends BinTreeNode <N, A>, M extends Image, A extends Address <A>>
        extends Tree <N, M, A> {

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
    BinTree ( TreeNode <N, A> root, M image, TreeNodeAction <N> action ) {
        super(root, image, action);
    }

    public
    void draw ( M image, Rect rect ) {

    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A> nodeInstance ( TreeNode <N, A> parent, Direction quadrant, Rect rect ) {
        throw new UnsupportedOperationException("Tree#nodeInstance()");
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
