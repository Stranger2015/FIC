package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 */
public
class RLTreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, M extends IImage<A>, G extends BitBuffer> extends TreeNode <N, A, G> {


    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    RLTreeNode ( TreeNode <N, A, G> parent, EDirection quadrant, Rect rect )
            throws ValueError {
        super(parent, rect);
    }

    //    @Override
    public
    TreeNode <N, A, G> createNode ( TreeNode <N, A, G> parent, EDirection quadrant, Rect boundingBox )
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
    TreeNode <N, A, G> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new RLTreeNode <>((TreeNode<N, A, G>) parent, quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N, A, G> createNode ( TreeNode<N, A, G> parent, Rect boundingBox )
            throws ValueError {
        return null;
    }

    @Override
    public
    TreeNode <N, A, G> createChild ( int address ) throws ValueError {
        return null;
    }

    @Override
    public
    TreeNodeBase <N, A, G> createChild ( IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
        return null;
    }

    @Override
    public
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
        return null;
    }

    @Override
    public
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) throws ValueError {
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
    TreeNode <N, A, G> createChild ( Image <A> point, int layerIndex, int clusterIndex, IAddress <A> address ) throws ValueError {
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
    TreeNode <N, A, G> createChild ( Point point, int layerIndex, int clusteIndex, int x, int y, int address )
            throws ValueError {

        return null;
    }

//    @Override
    public
    TreeNode <N, A, G> createChild ( int layerIndex, int clusterIndex, int address )
            throws ValueError {

        return null;
    }
}
