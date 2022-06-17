package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 */
public
class TriangleTreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TreeNode <N, A, G> {
    /**
     * @param parent
     * @param quadrant
     * @param boundingBox
     * @throws ValueError
     */
    public
    TriangleTreeNode ( TreeNodeBase<N, A, G> parent, EDirection quadrant,IIntSize boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param boundingBox
     * @throws ValueError
     */
    public
    TriangleTreeNode ( TreeNodeBase <N, A, G> parent, IIntSize boundingBox ) throws ValueError {
        super(parent, null, boundingBox);
    }

    @Override
    public
    TreeNode <N, A, G> createChild ( int addr ) throws ValueError {
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
    TreeNodeBase <N, A, G> createChild ( IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
        return null;
    }

    @Override
    public
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param address
     * @return
     */
    @Override
    public
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) throws ValueError {
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
    TreeNode <N, A, G> createChild ( int layerIndex, int clusteIndex, int address )
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
    TreeNode <N, A, G> createChild ( EDirection quadrant, IIntSize boundingBox ) throws ValueError {
        return new TriangleTreeNode <N,A,G>(quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N, A, G> createNode ( TreeNode <N, A, G> parent, IIntSize boundingBox ) throws ValueError {
        return new TriangleTreeNode <>(parent, boundingBox);
    }

//    public
//    class TriangleLeafNode<N extends LeafNode <N, A, G>, A extends IAddress <A>, M extends IImage<A>>
//            extends LeafNode <N, A, G> {
//
//        /**
//         * @param parent
//         * @param image
//         * @param rect
//         */
//        protected
//        TriangleLeafNode ( TreeNode <N, A, G> parent, M image, Rect rect ) {
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
//        TreeNode <N, A, G> createChild ( Point point, int layerIndex, int clusterIndex, Address <A> address ) throws ValueError {
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
//        TreeNode <N, A, G> createChild ( Point point, int layerIndex, int clusterIndex, int x, int y, int address )
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
//        TreeNode <N, A, G> createChild ( int layerIndex, int clusterIndex, int address ) throws ValueError {
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
//        M getMat () {
//            return null;
//        }
//
//        /**
//         * @return
//         */
//        @Override
//        public
//        M getImage () {
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
//        TreeNodeBase <N, A, G> createNode ( TreeNode <N, A, G> parent, Rect boundingBox ) throws ValueError {
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
//        TreeNode <N, A, G> createNode ( TreeNode <N, A, G> parent, M image, Rect boundingBox ) throws ValueError {
//            return null;
//        }
//    }
}
