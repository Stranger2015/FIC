package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.List;

/**
 * @param <N>
 */
public
class RLTreeNode<N extends RLTreeNode <N>> extends TreeNode <N> {
    public
    RLTreeNode ( N parent, CornerDirection direction, Rect rect, List <N> children ) {
        super(parent, direction, rect, children);
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
