package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.codec.IAddress;

/**
 *
 */
public
class QuadTreeNode<N extends QuadTreeNode <N, A>, A extends Address <A>> extends BinTreeNode <N, A> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    QuadTreeNode ( QuadTreeNode <N, A> parent, Direction quadrant, Rect rect ) {
        super(parent, quadrant, rect);
    }

    /**
     * @param quadrant
     * @param rect
     * @return
     */
    @SuppressWarnings("*")
    public
    TreeNode <N, A> createChild ( Direction quadrant, Rect rect ) {
        return new QuadTreeNode <>(this, quadrant, rect);
    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Image image, Rect rect ) {
        super.draw(image, rect);
    }
}