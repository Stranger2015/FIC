package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.function.Consumer;

public
class RLTree<N extends RLTreeNode <N>, M extends Mat> extends Tree <N, M> {
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
    RLTree ( N root, M image, Consumer <N> action, Rect area, int depth ) {
        super(root, image, action, area, depth);
    }


    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N> nodeInstance ( N parent, CornerDirection quadrant, Rect rect ) {
        return new RLTreeNode <>(parent, quadrant, rect);
    }
}
