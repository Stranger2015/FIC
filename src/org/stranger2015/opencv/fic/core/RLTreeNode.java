package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 */
public
class RLTreeNode<N extends RLTreeNode <N,A>, A extends Address<A,?>> extends TreeNode <N, A> {


    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    RLTreeNode ( TreeNode<N, A> parent, EDirection quadrant, Rect rect ) throws ValueError {
        super(parent, rect);
    }

    //    @Override
public
    TreeNodeBase <N, A> createNode ( TreeNode <N, A> parent, EDirection quadrant, Rect boundingBox )
        throws ValueError {
        return new RLTreeNode<>(parent, quadrant, boundingBox);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     * @throws ValueError
     */
    @Override
    public
    TreeNodeBase <N, A> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new RLTreeNode <>((TreeNode <N, A>) parent, quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNodeBase <N, A> createNode ( TreeNodeBase <N, A> parent, Rect boundingBox ) throws ValueError {
        return null;
    }
}
