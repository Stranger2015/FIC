package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.Point;

/**
 * @param <N>
 */
public
class RLTreeNode<N extends TreeNode <N>> extends TreeNode <N> {


    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    RLTreeNode ( TreeNode <N> parent, EDirection quadrant, Rect rect )
            throws ValueError {
        super(parent, rect);
    }

    //    @Override
    public
    TreeNode <N> createNode ( TreeNode <N> parent, EDirection quadrant, Rect boundingBox )
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
    TreeNode <N> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new RLTreeNode <>((TreeNode<N>) parent, quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N> createNode ( TreeNode<N> parent, Rect boundingBox )
            throws ValueError {
        return null;
    }

    @Override
    public
    TreeNode <N> createChild ( int address ) throws ValueError {
        return null;
    }

    @Override
    public
    TreeNodeBase <N> createChild ( IImageBlock  imageBlock, IAddress  address ) throws ValueError {
        return null;
    }

    @Override
    public
    TreeNodeBase <N> createNode ( TreeNodeBase <N> parent, IImageBlock  imageBlock, IAddress  address ) throws ValueError {
        return null;
    }

    @Override
    public
    TreeNodeBase <N> createNode ( TreeNodeBase <N> parent, IAddress  address ) throws ValueError {
        return null;
    }

    /**
     * @param point
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @return
     * @throws ValueError
     */
//    @Override
    public
    TreeNode <N> createChild ( Image  point, int layerIndex, int clusterIndex, IAddress  address ) throws ValueError {
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
//    @Override
    public
    TreeNode <N> createChild ( Point point, int layerIndex, int clusteIndex, int x, int y, int address )
            throws ValueError {

        return null;
    }

//    @Override
    public
    TreeNode <N> createChild ( int layerIndex, int clusterIndex, int address )
            throws ValueError {

        return null;
    }
}
