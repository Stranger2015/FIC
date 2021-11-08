package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.List;
import java.util.function.Consumer;

/**
 *
 */
public
class BinTree<N extends BinTreeNode<N>, M extends Mat> extends Tree <N, M> {

    /**
     * Constructs a new object.
     *
     * @param image
     */
    public
    BinTree (BinTreeNode<N> parent, M image, Consumer <N> action ) {
        super(parent, image, action, DEFAULT_BBOX, DEFAULT_DEPTH);
    }

//    @Override
    public
    void draw ( M image, Rect rect ) {

    }

    public
    BinTreeNode<N> merge () {
        return null;
    }

    /**
     * @param parent
     * @param rect
     * @param nodes
     * @return
     */
    @Override
    public
    BinTreeNode <N> nodeInstance (N parent,CornerDirection quadrant,Rect rect, List <N> nodes ) {
        throw new UnsupportedOperationException("Tree#nodeInstance()");
    }
}
