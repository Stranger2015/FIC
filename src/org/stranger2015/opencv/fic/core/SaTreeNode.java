package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 */
@SuppressWarnings("unchecked")
public
class SaTreeNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends TreeNode <N, A, M, G> {

    protected int layerIndex;

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @throws ValueError
     */
    public
    SaTreeNode ( TreeNode <N, A, M, G> parent, EDirection hexant, Rect boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @throws ValueError
     */
    public
    SaTreeNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    SaTreeNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox ) {
        super(parent, image, boundingBox);
    }

    /**
     *
     */
    public
    SaTreeNode () {
    }

    @Override
    public
    TreeNode <N, A, M, G> createChild ( int address ) throws ValueError {
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
    TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusteIndex, int x, int y, int address )
            throws ValueError {
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
    TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, int address ) throws ValueError {
        return createChild(address);
    }

    /**
     * @param hexant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNodeBase <N, A, M, G> createChild ( EDirection hexant, Rect boundingBox ) throws ValueError {
        return new SaTreeNode <>(this, hexant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {
        return new SaTreeNode <>(parent, boundingBox);
    }

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @return
     */
    public
    TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, EDirection hexant, Rect boundingBox )
            throws ValueError {
        return new SaTreeNode <>(parent, hexant, boundingBox);
    }

    /**
     * @param <N>
     * @param <A>
     * @param <M>
     */
    public static
    class SaLayerClusterNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
            G extends BitBuffer>
            extends LeafNode <N, A, M, G> {

        protected int layerIndex;

        /**
         * @param parent
         * @param image
         * @param rect
         * @throws ValueError
         */
        protected
        SaLayerClusterNode ( TreeNode <N, A, M, G> parent,
                              M image,
                              Rect rect,
                              int layerIndex,
                              Address<A> address )
                throws ValueError {

            super(parent, image, rect, address);

            this.layerIndex = layerIndex;
        }

        /**
         * @param parent
         * @param point
         * @param image
         * @param boundingBox
         */
        protected
        SaLayerClusterNode ( TreeNode <N, A, M, G> parent, Point point, M image, Rect boundingBox ) {
            super(parent, point, (ImageBlock<A>) image, boundingBox);
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         * @param layerIndex
         * @param x
         * @param y
         * @param address
         */
        public
//        <N extends TreeNode <N, A, M, G>, A extends SipAddress <A>, M extends IImage<A>,
        SaLayerClusterNode ( TreeNode <N, A, M, G> parent,
                              M image,
                              Rect boundingBox,
                              int layerIndex,
                              int x,
                              int y,
                              int address ) {
            super(parent, image, image.getWidth(), image.getHeight());
        }

        public
//        <M extends IImage<A>, A extends SipAddress <A>, N extends TreeNode <N, A, M, G>>
        SaLayerClusterNode ( TreeNode <N, A, M, G> parent,
                             M image,
                             int layerIndex,
                             int clusterIndex,
                             int x,
                             int y,
                             int address ) {
            //todo
            this(parent, image, layerIndex, clusterIndex,x, y, image.getWidth(),address);
        }

        public
//        <N extends TreeNode <N, A, M, G>, A extends SipAddress <A>, M extends IImage<A>,
        SaLayerClusterNode ( TreeNode <N, A, M, G> parent, Rect boundingBox, int layerIndex, int clusterIndex, int address ) {
            this(parent, layerIndex, clusterIndex, 0, 0, address);
        }

        public
//        <N extends TreeNode <N, A, M, G>, A extends SipAddress <A>, M extends IImage<A>,
        SaLayerClusterNode ( TreeNode <N, A, M, G> parent,
                             M image,
                             int layerIndex,
                             int clusterIndex,
                             int x,
                             int y,
                             int width,
                             int address ) {
        }

        public
//        <M extends IImage<A>, N extends TreeNode <N, A, M, G>, A extends SipAddress <A>>
        SaLayerClusterNode ( TreeNode <N, A, M, G> parent, int layerIndex, int clusterIndex, int i, int i1, int address ) {


        }

        public
        SaLayerClusterNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox, int i, int i1 ) {

        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         * @return
         * @throws ValueError
         */
        @Override
        public
        TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox )
                throws ValueError {
            
            return null;//todo
        }

        /**
         * @param point
         * @param layerIndex
         * @param clusterIndex
         * @param address
         * @return
         * @throws ValueError
         */
        //@Override
        public
        TreeNode <N, A, M, G> createChild ( Point point,
                                         int layerIndex,
                                         int clusterIndex,
                                         int address ) throws ValueError {
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
        TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusteIndex, int x, int y, int address ) throws ValueError {
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
        @Override
        public
        TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, Address <A> address ) throws ValueError {
            return null;
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNodeBase <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {
            return null;
        }
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     * @return
     */
    public
    TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Point point, M image, Rect boundingBox )
            throws ValueError {
        return new SaLayerClusterNode <>(parent, point, image, boundingBox) {
            /**
             * @param point
             * @param layerIndex
             * @param clusteIndex
             * @param x
             * @param y
             * @param address
             * @return
             */
            @Override
            public
            TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusteIndex, int x, int y, int address ) {

                return null;
            }

            /**
             * @param parent
             * @param boundingBox
             * @return
             */
            @Override
            public
            TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {
                return null;
            }

            /**
             * @return
             */
            @Override
            public
            int getX () {
                return imageBlock.getX();
            }

            /**
             * @return
             */
            @Override
            public
            int getY () {
                return imageBlock.getY();
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
        };
    }

    /**
     * @return
     */
    public
    int getLayerIndex () {
        return layerIndex;
    }

    //    @Override
    public
    M getMat () {
        return null;
    }

    //    @Override
    public
    M getImage () {
        return null;
    }

    //    @Override
    public
    Rect getBoundingBox () {
        return null;
    }
}

