package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;

/**
 * root --> rectangle diagonally divided into 2 triangles --> triangle tree nodes
 *
 */
public
class TriangleTree<N extends TreeNode<N>> extends BinTree <BinTreeNode<N>, Mat> {
    /**
     * Constructs a new object.
     *
     * @param parent
     * @param image
     * @param action
     */
    public
    TriangleTree ( BinTreeNode<N> parent, Mat image, TreeNodeAction<N> action ) {
        super(parent, image, action);
    }
}
