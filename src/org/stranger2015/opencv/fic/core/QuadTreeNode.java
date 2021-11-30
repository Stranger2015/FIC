package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

/**
 *
 */
public
class QuadTreeNode<N extends QuadTreeNode<N>> extends BinTreeNode <N> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    QuadTreeNode ( QuadTreeNode<N> parent, Direction quadrant, Rect rect ) {
        super(parent, quadrant, rect);
    }

    /**
     * @param quadrant
     * @param rect
     * @return
     */
    @SuppressWarnings("*")
    public
    TreeNode <N> createChild (Direction quadrant, Rect rect ) {
        return new QuadTreeNode<N>(this, quadrant, rect);
    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Mat image, Rect rect ) {
        super.draw(image, rect);
    }
}