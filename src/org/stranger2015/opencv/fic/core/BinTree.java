package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class BinTree<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tree <N, A, G> {

    /**
     * @param root
     * @param imageBlock
     * @param action
     */
    public
    BinTree ( TreeNode <N, A, G> root, IImageBlock <A> imageBlock, TreeNodeTask <N, A, G> action ) {
        super(root, imageBlock, action);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A, G> nodeInstance ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect )
            throws ValueError {

        return new BinTreeNode <>(parent, quadrant, rect);
    }
}
