package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class QuadTreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeNode <N, A, G> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    QuadTreeNode ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect ) throws ValueError {
        super(parent, quadrant, rect);
    }

    /**
     * @param parent
     * @param boundingBox
     * @throws ValueError
     */
    public
    QuadTreeNode ( TreeNodeBase <N, A, G> parent, IIntSize boundingBox ) throws ValueError {
        this(parent, null, boundingBox);
    }

    /**
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNodeBase <N, A, G> createChild ( EDirection quadrant, IIntSize rect ) throws ValueError {
        return new QuadTreeNode <>(this, quadrant, rect);
    }
}