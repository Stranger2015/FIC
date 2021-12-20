package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

/**
 * @param <N>
 */
public
class RLTreeNode<N extends RLTreeNode <N,?>, A extends Address<A>> extends TreeNode <N, A> {

    @Override
    public
    TreeNode <N, A> createNode ( TreeNode <N, A> parent, Direction quadrant, Rect boundingBox ) {
        return new RLTreeNode<>(parent, quadrant, boundingBox);
    }

    public
    RLTreeNode ( TreeNode <N, A> parent, Direction quadrant, Rect rect ) {
        super(parent, quadrant, rect);
    }

    @Override
    public
    RLTreeNode<N, A> createChild ( Direction quadrant, Rect boundingBox ) {
        return new RLTreeNode <>(parent, quadrant, boundingBox);
    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Image image, Rect rect ) {

    }
}
