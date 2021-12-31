package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.Triangle.TriangleTreeNode;

/**
 * root --> rectangle diagonally divided into 2 triangles --> triangle tree nodes
 */
public
class TriangleTree<N extends TriangleTreeNode <N, A>, M extends Image, A extends Address <A,?>>
        extends BinTree <N, M, A> {
    /**
     * Constructs a new object.
     *
     * @param parent
     * @param image
     * @param action
     */
    public
    TriangleTree ( TriangleTreeNode <N, A> parent, M image, TreeNodeAction <N> action ) {
        super(parent, image, action);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
//    @Override
    public
    TreeNodeBase <N, A> nodeInstance ( TriangleTreeNode <N, A> parent, EDirection quadrant, Rect rect ) {
        return new TriangleTreeNode <>(parent, rect);
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
