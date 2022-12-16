package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class BinTree<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Tree <N> {

    /**
     * @param root
     * @param imageBlock
     * @param action
     */
    public
    BinTree ( TreeNode <N> root, IImageBlock  imageBlock, TreeNodeTask <N> action ) {
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
    TreeNode <N> nodeInstance ( TreeNodeBase <N> parent, EDirection quadrant, IIntSize rect )
            throws ValueError {

        return new BinTreeNode <>(parent, quadrant, rect);
    }
}
