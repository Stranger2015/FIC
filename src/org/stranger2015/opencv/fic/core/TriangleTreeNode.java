package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param 
 */
public
class TriangleTreeNode<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends TreeNode <N> {
    /**
     * @param parent
     * @param quadrant
     * @param boundingBox
     * @throws ValueError
     */
    public
    TriangleTreeNode ( TreeNodeBase<N> parent, EDirection quadrant,IIntSize boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param boundingBox
     * @throws ValueError
     */
    public
    TriangleTreeNode ( TreeNodeBase <N> parent, IIntSize boundingBox ) throws ValueError {
        super(parent, null, boundingBox);
    }

    @Override
    public
    TreeNode <N> createChild ( int addr ) throws ValueError {
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
    TreeNodeBase <N> createChild ( IImageBlock  imageBlock, IAddress  address ) throws ValueError {
        return null;
    }

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
    TreeNode <N> createChild ( int layerIndex, int clusteIndex, int address )
            throws ValueError {
        return null;
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N> createChild ( EDirection quadrant, IIntSize boundingBox ) throws ValueError {
        return new TriangleTreeNode <N,A,G>(quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N> createNode ( TreeNode <N> parent, IIntSize boundingBox ) throws ValueError {
        return new TriangleTreeNode <>(parent, boundingBox);
    }

//    public
//    class TriangleLeafNode<N extends LeafNode <N>, A extends IAddress , IImage extends IImage>
//            extends LeafNode <N> {
//
//        /**
//         * @param parent
//         * @param image
//         * @param rect
//         */
//        protected
//        TriangleLeafNode ( TreeNode <N> parent, IImage image, Rect rect ) {
//            super(parent, point, image, rect);
//        }
//
//        /**
//         * @param point
//         * @param layerIndex
//         * @param clusterIndex
//         * @param address
//         * @return
//         * @throws ValueError
//         */
//        @Override
//        public
//        TreeNode <N> createChild ( Point point, int layerIndex, int clusterIndex, Address  address ) throws ValueError {
//            return null;
//        }
//
//        /**
//         * @param point
//         * @param layerIndex
//         * @param clusterIndex
//         * @param x
//         * @param y
//         * @param address
//         * @return
//         * @throws ValueError
//         */
//        @Override
//        public
//        TreeNode <N> createChild ( Point point, int layerIndex, int clusterIndex, int x, int y, int address )
//                throws ValueError {
//
//            return null;
//        }
//
//        /**
//         * @param layerIndex
//         * @param clusterIndex
//         * @param address
//         * @return
//         * @throws ValueError
//         */
////        @Override
//        public
//        TreeNode <N> createChild ( int layerIndex, int clusterIndex, int address ) throws ValueError {
//            return null;
//        }
//
//        /**
//         * @return
//         */
//        @Override
//        public
//        int getX () {
//            return 0;
//        }
//
//        /**
//         * @return
//         */
//        @Override
//        public
//        int getY () {
//            return 0;
//        }
//
//        /**
//         * @return
//         */
//        @Override
//        public
//        IImage getMat () {
//            return null;
//        }
//
//        /**
//         * @return
//         */
//        @Override
//        public
//        IImage getImage () {
//            return null;
//        }
//
//        /**
//         * @return
//         */
//        @Override
//        public
//        Rect getBoundingBox () {
//            return null;
//        }
//
//        /**
//         * @param parent
//         * @param boundingBox
//         * @return
//         */
//        @Override
//        public
//        TreeNodeBase <N> createNode ( TreeNode <N> parent, Rect boundingBox ) throws ValueError {
//            return null;
//        }
//
//        /**
//         * @param parent
//         * @param image
//         * @param boundingBox
//         * @return
//         * @throws ValueError
//         */
//        @Override
//        public
//        TreeNode <N> createNode ( TreeNode <N> parent, IImage image, Rect boundingBox ) throws ValueError {
//            return null;
//        }
//    }
}
