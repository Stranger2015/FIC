package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

/**
 * @param <N>
 */
public
class RLTreeNode<N extends RLTreeNode <N>> extends TreeNode <N> {
    @Override
    public
    RLTreeNode<N> createChild ( CornerDirection quadrant, Rect boundingBox ) {
        return new RLTreeNode <>((N) this, quadrant, boundingBox);
    }

    public
    RLTreeNode ( N parent, CornerDirection direction, Rect rect ) {
        super(parent, direction, rect);
    }

    @Override
    public
    void draw ( Mat image, Rect rect ) {
    }

    @Override
    public
    N merge () {
        return null;
    }
}
