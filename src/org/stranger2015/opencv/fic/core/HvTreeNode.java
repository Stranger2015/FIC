package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 */
public class HvTreeNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer>
        extends TreeNodeBase <N, A, M, G> {
    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    HvTreeNode ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect ) {
        super(parent, null, rect);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
    public
    HvTreeNode <N, A, M, G> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new HvTreeNode <>(null, quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param quadrant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    HvTreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect boundingBox )
            throws ValueError {
        return new HvTreeNode <>(parent, quadrant, boundingBox);
    }

    @Override
    public
    TreeNode <N, A, M, G> createChild ( int address ) throws ValueError {
      throw new UnsupportedOperationException();//  return new HvTreeNode<N, A,M>();
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
    TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusteIndex, int x, int y, int address )
            throws ValueError {
        return null;
    }

//    @Override
    public
    TreeNode <N, A, M, G> createChild ( int layerIndex, int imageBlock, int address ) throws ValueError {
        throw new UnsupportedOperationException();
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode<N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {
        return null;
    }

    /**
     * @param <N>
     * @param <A>
     */
    public
    class HvLeafNode<N extends HvLeafNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
            extends LeafNode <N, A, M, G>
            implements ILeaf <N, A, M, G> {

        /**
         * @param parent
         * @param image
         * @param rect
         * @throws ValueError
         */
        protected
        HvLeafNode ( TreeNode <N, A, M, G> parent, M image, Rect rect ) throws ValueError {
            super(parent, null, rect);//fixme
        }

        /**
         * @param point
         * @param layerIndex
         * @param clusterIndex
         * @param address
         * @return
         * @throws ValueError
         */
        @Override
        public
        TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, Address <A> address ) throws ValueError {
            return null;
        }

        /**
         * @param point
         * @param layerIndex
         * @param clusterIndex
         * @param x
         * @param y
         * @param address
         * @return
         * @throws ValueError
         */
        @Override
        public
        TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, int x, int y, int address )
                throws ValueError {
            return null;
        }

        /**
         * @param parent
         * @param quadrant
         * @param boundingBox
         * @return
         */
//        @Override
        public
        HvLeafNode <N, A, M, G> createNode ( TreeNode<N, A, M, G> parent, EDirection quadrant, Rect boundingBox )
                throws ValueError {
            return new HvLeafNode <>(parent, null, boundingBox);//fixme
        }

        //        @Override
        public
        HvLeafNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox )
                throws ValueError {
            return new HvLeafNode <>(parent, image, boundingBox);
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNodeBase <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox )
                throws ValueError {
            return null;
        }

        /**
         * @return
         */
        @Override
        public
        int getX () {
            return 0;
        }

        /**
         * @return
         */
        @Override
        public
        int getY () {
            return 0;
        }

        /**
         * @return
         */
        @Override
        public
        M getMat () {
            return null;
        }

        /**
         * @return
         */
        @Override
        public
        M getImage () {
            return null;
        }

        /**
         * @return
         */
        @Override
        public
        Rect getBoundingBox () {
            return null;
        }

//        @Override
        public
        TreeNode <N, A, M, G> createChild ( int layerIndex, int imageBlock, int address ) throws ValueError {
            return null;
        }
    }
}
