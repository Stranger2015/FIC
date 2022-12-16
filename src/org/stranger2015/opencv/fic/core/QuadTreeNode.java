package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class QuadTreeNode<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends BinTreeNode <N> {

    /**
     * @param <N>Iparent
     * @param quadrant
     * @param rect
     */
    public
    QuadTreeNode ( TreeNodeBase <N> parent, EDirection quadrant, Rectangle rect ) throws ValueError {
        super(parent, quadrant, rect);
    }

    /**
     * @param parent
     * @param boundingBox
     * @throws ValueError
     */
    public
    QuadTreeNode ( TreeNodeBase <N> parent, Rectangle boundingBox ) throws ValueError {
        this(parent, null, boundingBox);
    }

    /**
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNodeBase <N> createChild ( EDirection quadrant, Rectangle rect ) throws ValueError {
        return new QuadTreeNode <>(this, quadrant, rect);
    }
}