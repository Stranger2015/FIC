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
        super(image, action, DEFAULT_BOUNDING_BOX, DEFAULT_DEPTH);
    }

    public
    <N extends QuadTreeNode<N>, M extends Mat>
    BinTree ( TreeNode<N> root, M image, TreeNodeAction<N> action ) {
        super(root, image, action);
    }

    //    @Override
    public
    void draw ( M image, Rect rect ) {

    }

    public
    BinTreeNode <N> merge () {
        return null;
    }

    /**
     * @param parent
     * @param rect
     * @return
     */
    @Override
    public
    BinTreeNode <N> nodeInstance ( TreeNode <N> parent, CornerDirection quadrant, Rect rect ) {
        throw new UnsupportedOperationException("Tree#nodeInstance()");
    }
}
