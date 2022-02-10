package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * root --> rectangle diagonally divided into 2 triangles --> triangle tree nodes
 */
public
class TriangleTree<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage>
        extends BinTree <N, A, M> {
    /**
     * Constructs a new object.
     *
     * @param parent
     * @param image
     * @param action
     */
    public
    TriangleTree ( TreeNode <N, A, M> parent, M image, TreeNodeAction <N, A, M> action ) {
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
    TreeNode <N, A, M> nodeInstance ( TreeNode <N, A, M> parent, EDirection quadrant, Rect rect ) throws ValueError {
        return new TriangleTreeNode <>(parent, rect);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    Class <? extends TreeNode <N, A, M>> getNodeClass ( TreeNode <N, A, M> clazz ) {
        return (Class <? extends TreeNode <N, A, M>>) clazz.getClass();
    }
}
