package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 */
public class HvTreeNode<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
       extends TreeNodeBase <N> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    HvTreeNode ( TreeNode <N> parent, EDirection quadrant, Rect rect ) throws ValueError {
        super(parent, quadrant, rect);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
    public
    HvTreeNode <N> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
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
    HvTreeNode <N> createNode ( TreeNode <N> parent, EDirection quadrant, Rect boundingBox )
            throws ValueError {
        return new HvTreeNode <>(parent, quadrant, boundingBox);
    }

    @Override
    public
    TreeNode <N> createChild ( int address ) throws ValueError {
      throw new UnsupportedOperationException();//  return new HvTreeNode<N, A,M>();
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
    TreeNode <N> createChild ( GrayScaleImage  point, int layerIndex, int clusterIndex, IAddress  address ) throws ValueError {
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
    TreeNode <N> createChild ( int layerIndex, int imageBlock, int address ) throws ValueError {
        throw new UnsupportedOperationException();
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNodeBase<N> createNode ( TreeNodeBase <N> parent, Rectangle boundingBox ) throws ValueError {
        return null;
    }

    /**
     * @param address@return
     * @throws ValueError
     */
    @Override
    public
    LeafNode <N> createChild ( IAddress  address ) throws ValueError {
        return null;
    }

    /**
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    LeafNode <N> createChild ( IImageBlock  imageBlock, IAddress  address ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    TreeNodeBase <N> createNode ( TreeNodeBase <N> parent, IImageBlock  imageBlock, IAddress  address ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param address
     * @return
     */
    @Override
    public
    TreeNodeBase <?> createNode ( TreeNodeBase <?> parent, IAddress  address ) throws ValueError {
        return null;
    }

    /**
     * @param <N>
     * @param
     */
    public static
    class HvLeafNode<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
            extends LeafNode <N>
            implements ILeaf <N> {

        private IImage image;

        /**
         * @param parent
         * @param image
         * @param rect
         */
        protected
        HvLeafNode ( TreeNode <N> parent, IImageBlock image, Rectangle rect ) throws ValueError {
            super(parent, image, rect);
        }

        /**
         * @param point
         * @param layerIndex
         * @param clusterIndex
         * @param address
         * @return
         * @throws ValueError
         */
//        @Override
        public
        TreeNode <N> createChild ( GrayScaleImage  point, int layerIndex, int clusterIndex, IAddress  address ) throws ValueError {
            throw new UnsupportedOperationException();
        }

        /**
         * @param point
         * @param layerIndex
         * @param clusterIndex
         * @param address
         * @return
         * @throws ValueError
         */
//        @Override
        public
        TreeNode <N> createChild ( Point point, int layerIndex, int clusterIndex, Address  address ) throws ValueError {
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
//        @Override
        public
        TreeNode <N> createChild ( Point point, int layerIndex, int clusterIndex, int x, int y, int address )
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
        HvLeafNode <N> createChild ( TreeNodeBase<N> parent, EDirection quadrant, Rectangle boundingBox )
                throws ValueError {
            return new HvLeafNode <>(parent, null, boundingBox);//fixme
        }

        //        @Override
        public
        HvLeafNode <N> create ( TreeNode <N> parent, Rectangle boundingBox )
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
        TreeNodeBase <N> createNode ( TreeNode <N> parent, Rect boundingBox )
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
        Mat getMat () {
            return image.getMat();
        }

        /**
         * @return
         */
        @Override
        public
        IImage getImage () {
            return null;
        }

        /**
         * @return
         */
        @Override
        public
        IIntSize getBoundingBox () {
            return null;
        }

//        @Override
        public
        TreeNode <N> createChild ( int layerIndex, int imageBlock, int address ) throws ValueError {
            return null;
        }

        public
        void setImage ( IImage image ) {
            this.image = image;
        }
    }
}
