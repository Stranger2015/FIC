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
    TreeNode <N> createNode ( TreeNode <N> parent, Direction quadrant, Rect boundingBox ) {
        return new RLTreeNode<>(parent, quadrant, boundingBox);
    }

    public
    RLTreeNode ( TreeNode <N> parent, Direction quadrant, Rect rect ) {
        super(parent, quadrant, rect);
    }

    @Override
    public
    RLTreeNode<N> createChild ( Direction quadrant, Rect boundingBox ) {
        return new RLTreeNode <>(parent, quadrant, boundingBox);
    }

    @Override
    public
    void draw ( Mat image, Rect rect ) {
    }
}
