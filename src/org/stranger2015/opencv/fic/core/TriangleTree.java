package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.Triangle.TriangleTreeNode;

/**
 * root --> rectangle diagonally divided into 2 triangles --> triangle tree nodes
 *
 */
public
class TriangleTree<N extends TriangleTreeNode<N>, M extends Mat> extends BinTree <N, M> {
    /**
     * Constructs a new object.
     *
     * @param parent
     * @param image
     * @param action
     */
    public
    TriangleTree ( TriangleTreeNode<N> parent, M image, TreeNodeAction<N> action ) {
        super(parent, image, action);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) TriangleTreeNode.class;
    }
}
