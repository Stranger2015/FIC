package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * root --> rectangle diagonally divided into 2 triangles --> triangle tree nodes
 */
public
class TriangleTree<N extends TreeNode <N>, A extends IAddress , IImage extends IImage, G extends BitBuffer>
        extends BinTree<N,A,G> {
    /**
     * Constructs a new object.
     *
     * @param parent
     * @param image
     * @param action
     */
    public
    TriangleTree ( TreeNode <N,A,G> parent, IImageBlock  image, TreeNodeTask <N,A,G> action ) {
        super(parent, image, action);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
//    @Override
    @Override
    public
    TreeNode <N,A,G> nodeInstance ( TreeNodeBase <N,A,G> parent, EDirection quadrant, IIntSize rect ) throws ValueError {
        return new TriangleTreeNode <>(parent,  rect);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    Class <? extends TreeNode <N,A,G>> getNodeClass ( TreeNode <N,A,G> clazz ) {
        return (Class <? extends TreeNode <N>>) clazz.getClass();
    }
}
