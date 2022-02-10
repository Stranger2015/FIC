package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.Point;

/**
 * @param <N>
 */
public
class RLTreeNode<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage> extends TreeNode <N, A, M> {


    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    RLTreeNode ( TreeNode <N, A, M> parent, EDirection quadrant, Rect rect )
            throws ValueError {
        super(parent, rect);
    }

    //    @Override
    public
    TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, EDirection quadrant, Rect boundingBox )
            throws ValueError {
        return new RLTreeNode <>(parent, quadrant, boundingBox);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     * @throws ValueError
     */
//    @Override
    public
    TreeNode <N, A, M> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new RLTreeNode <>((TreeNode<N, A, M>) parent, quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N, A, M> createNode ( TreeNode<N, A, M> parent, Rect boundingBox )
            throws ValueError {
        return null;
    }

    @Override
    public
    TreeNode <N, A, M> createChild ( int address ) throws ValueError {
        return null;
    }

    /**
     * @param point
     * @param layerIndex
     * @param clusteIndex
     * @param x
     * @param y
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    TreeNode <N, A, M> createChild ( Point point, int layerIndex, int clusteIndex, int x, int y, int address )
            throws ValueError {
        return null;
    }

//    @Override
    public
    TreeNode <N, A, M> createChild ( int layerIndex, int clusterIndex, int address )
            throws ValueError {
        return null;
    }
}
