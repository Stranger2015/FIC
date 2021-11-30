package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

/**
 *
 */
public
class BinTree<N extends BinTreeNode <N>, M extends Mat> extends Tree <N, M> {

    /**
     * Constructs a new object.
     *
     * @param image
     */
    public
    BinTree (M image, TreeNodeAction <N> action ) {
        this(null, image, action);
    }

    public
    BinTree ( TreeNode<N> root, M image, TreeNodeAction<N> action ) {
        super(root, image, action);
    }

    //    @Override
    public
    void draw ( M image, Rect rect ) {

    }
//
//    public
//    BinTreeNode <N> merge () {
//        return null;
//    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N> nodeInstance ( TreeNode <N> parent, Direction quadrant, Rect rect ) {
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
