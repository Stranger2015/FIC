package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * root --> rectangle diagonally divided into 2 triangles --> triangle tree nodes
 */
public
class TriangleTree<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends BinTree <N, A, M, G> {
    /**
     * Constructs a new object.
     *
     * @param parent
     * @param image
     * @param action
     */
    public
    TriangleTree ( TreeNode <N, A, M, G> parent, M image, TreeNodeAction <N, A, M, G> action ) {
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
    TreeNode <N, A, M, G> nodeInstance ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect ) throws ValueError {
        return new TriangleTreeNode <>(parent, rect);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    Class <? extends TreeNode <N, A, M, G>> getNodeClass ( TreeNode <N, A, M, G> clazz ) {
        return (Class <? extends TreeNode <N, A, M, G>>) clazz.getClass();
    }
}
