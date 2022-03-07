package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class BinTree<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends Tree <N, A, M, G> {

    /**
     * Constructs a new object.
     *
     * @param image
     */
    public
    BinTree ( M image, TreeNodeAction <N, A, M, G> action ) {
        this(null, image, action);
    }

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    BinTree ( TreeNode <N, A, M, G> root, M image, TreeNodeAction<N, A, M, G> action ) {
        super(root, image, action);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A, M, G> nodeInstance ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect ) throws ValueError {
        return null;
    }

//    /**
//     * @return
//     */
////    @SuppressWarnings("unchecked")
//    @Override
//    public
//    Class <TreeNode <N, A, M, G>> getNodeClass () {
//        return ( Class <TreeNode <N, A, M, G>>) BinTreeNode.class;
//    }
}
