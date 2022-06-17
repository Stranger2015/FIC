package org.stranger2015.opencv.fic.core;

import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 */
@SuppressWarnings("unchecked")
public
class SaTreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends TreeNode <N, A, G> {

    protected int layerIndex;

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @throws ValueError
     */
    public
    SaTreeNode ( TreeNodeBase <N, A, G> parent, EDirection hexant, IIntSize boundingBox ) throws ValueError {
        super(parent,hexant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @throws ValueError
     */
    public
    SaTreeNode ( TreeNodeBase <N, A, G> parent, IIntSize boundingBox ) throws ValueError {
        super(parent, null, boundingBox);
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    SaTreeNode ( TreeNodeBase <N, A, G> parent, IImage <A> image, IIntSize boundingBox ) {
        super(parent, image, boundingBox);
    }

    //    /**
//     *
//     */
//    public
//    SaTreeNode () {
//    }
//
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

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode<N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IIntSize boundingBox ) throws ValueError {
        return null;
    }

    /**
     * @param imageBlock
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @return
     * @throws ValueError
     */
//    @Override
    public
    TreeNode <N, A, G> createChild ( IImageBlock <A> imageBlock,
                                        int layerIndex,
                                        int clusterIndex,
                                        IAddress <A> address ) throws ValueError {
        return null;
    }

    /**
     * @param image
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @return
     * @throws ValueError
     */
//    @Override
    public
    TreeNode <N, A, G> createChild ( IImage <A> image,
                                        int layerIndex,
                                        int clusterIndex,
                                        IAddress <A> address ) throws ValueError {
        return null;
    }

    /**
     * @param ib
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @return
     * @throws ValueError
     */
//    @Override
    public
    TreeNode <N, A, G> createChild ( GrayScaleImage <A> ib,
                                        int layerIndex,
                                        int clusterIndex,
                                        IAddress <A> address ) throws ValueError {
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
    TreeNode <N, A, G> createChild ( Point point,
                                        int layerIndex,
                                        int clusterIndex,
                                        int address ) throws ValueError {
        return createChild(address);
    }

    /**
     * @param hexant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNodeBase <N, A, G> createChild ( EDirection hexant, IntSize boundingBox ) throws ValueError {
        return new SaTreeNode <>(this, hexant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IntSize boundingBox ) throws ValueError {
        return new SaTreeNode <>(parent, boundingBox);
    }

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @return
     */
    public
    TreeNode <N, A, G> createNode ( TreeNode <N, A, G> parent, EDirection hexant, IIntSize boundingBox )
            throws ValueError {
        return new SaTreeNode <>(parent, hexant, boundingBox);
    }

    /**
     * @param <N>
     * @param <A>
     */
    public static
    class SaLayerClusterNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,
            G extends BitBuffer>
            extends LeafNode <N, A, G> {
        /**
         * @param parent
         * @param point
         * @param imageBlock
         * @param rect
         */
        protected
        SaLayerClusterNode ( TreeNodeBase <N, A, G> parent,
                             Point point,
                             IImageBlock <A> imageBlock,
                             IIntSize rect ) {
            super(parent, point, imageBlock, rect);
        }

        protected int layerIndex;
        protected IAddress <A> address;

        /**
         * @param parent
         * @param image
         * @param rect
         * @param layerIndex
         * @param address
         */
        protected
        SaLayerClusterNode ( TreeNodeBase <N, A, G> parent,
                             IImageBlock <A> image,
                             IIntSize rect,
                             int layerIndex,
                             int address ) {

            super(parent, null, image, rect);

            this.layerIndex = layerIndex;
            this.address = address;
        }

        /**
         * @param parent
         * @param point
         * @param image
         * @param boundingBox
         */
        protected
        SaLayerClusterNode ( TreeNode <N, A, G> parent, Point point, M image, IntSize boundingBox ) {
            super(parent, point, (ImageBlock <A>) image, boundingBox);
        }

//        /**
//         * @param parent
//         * @param image
//         * @param boundingBox
//         * @param layerIndex
//         * @param x
//         * @param y
//         * @param address
//         */
//        public
////        <N extends TreeNode <N, A, G>, A extends SipAddress <A>, M extends IImage<A>,
//        SaLayerClusterNode ( TreeNode <N, A, G> parent,
//                              M image,
//                              IntSize boundingBox,
//                              int layerIndex,
//                              IAddress<A> address ) {
//            super(parent, image, image.getWidth(), image.getHeight());
//        }

//        public
////        </*M extends IImage<A>,*/ A extends SipAddress <A>, N extends TreeNode <N, A, G>>
//        SaLayerClusterNode ( TreeNode <N, A, G> parent,
//                             IImage<A> image,
//                             int layerIndex,
//                             int clusterIndex,
//                             IAddress <A> address ) {
//            //todo
//            this(parent, image, layerIndex, clusterIndex, image.getOriginalImageSideSize(), address);
//        }

//        public
////        <N extends TreeNode <N, A, G>, A extends SipAddress <A>, M extends IImage<A>,
//        SaLayerClusterNode ( TreeNode <N, A, G> parent,
//                             IntSize boundingBox,
//                             int layerIndex,
//                             int clusterIndex,
//                             IAddress<A> address ) {
//
//            this(parent, layerIndex, clusterIndex, 0, 0, address);
//        }

//        public
////        <N extends TreeNode <N, A, G>, A extends SipAddress <A>, M extends IImage<A>,
//        SaLayerClusterNode ( TreeNode <N, A, G> parent,
//                             int layerIndex,
//                             int clusterIndex,
//                             IAddress <A> address ) {
//            super(parent, image, rect.width, rect.height);
//        }

//        public
////        </*M extends IImage<A>,*/ N extends TreeNode <N, A, G>, A extends SipAddress <A>>
//        SaLayerClusterNode ( TreeNode <N, A, G> parent,
//                             int layerIndex,
//                             int clusterIndex,
//                             int i,
//                             int i1,
//                             IAddress <A> address ) {
//
//
//            super(parent, image, rect.width, rect.height);
//        }

//        public
//        SaLayerClusterNode ( TreeNode <N, A, G> parent, M image, IntSize boundingBox, int i, int i1 ) {
//
//            super(parent, image, boundingBox.width, boundingBox.height);
//        }

        public
        SaLayerClusterNode ( TreeNodeBase <N, A, G> parent,
                             IImageBlock <A> imageBlock,
                             int layerIndex,
                             int clusterIndex,
                             int addr ) throws ValueError {
            super(
                    parent,
                    imageBlock,
                    layerIndex,
                    clusterIndex,
                    addr
            );
        }

        public
        SaLayerClusterNode ( TreeNodeBase<N, A, G> parent,
                             IImageBlock<A> imageBlock,
                             IIntSize boundingBox,
                             IAddress<A> address ) {
            super(parent, imageBlock, boundingBox, address);
        }

        public
        SaLayerClusterNode ( ImageBlock<A> imageBlock, IIntSize bb, int layerIndex, int clusterIndex, IAddress<A> address ) {
            super(imageBlock, bb, layerIndex, clusterIndex, address);
        }


        /**
         * @param parent
         * @param imageBlock
         * @param boundingBox
         * @return
         * @throws ValueError
         */
//        @Override
        public
        TreeNode <N, A, G> createNode ( TreeNodeBase <N, A, G> parent,
                                           IImageBlock <A> imageBlock,
                                           IIntSize boundingBox )
                throws ValueError {

            return null;//todo
        }

        /**
         * @param point
         * @param layerIndex
         * @param clusterIndex
         * @param addr
         * @return
         * @throws ValueError
         */
        //@Override
        public
        TreeNodeBase <N, A, G> createChild (
                                            Point point,
                                            int layerIndex,
                                            int clusterIndex,
                                            int addr ) throws ValueError {
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
        IImageBlock <A> getImageBlock () {
            return imageBlock;
        }

        /**
         * @return
         */
        @Override
        public
        MatOfInt getMat () {
            return imageBlock.getMat();
        }

        /**
         * @return
         */
        @Override
        public
        IImage<A> getImage () {
            return imageBlock;
        }//todo

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

        /**
         * @return
         */
        @Override
        public
        IIntSize getBoundingBox () {
            return null;
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNode <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IIntSize boundingBox ) throws ValueError {
            return null;
        }

        /**
         * @param point
         * @param layerIndex
         * @param clusteIndex
         * @param x
         * @param y
         * @return
         * @throws ValueError
         */
//        @Override
        public
        TreeNodeBase <N, A, G> createChild ( Point point,
                                            int layerIndex,
                                            int clusteIndex,
                                            int x,
                                            int y,
                                            int addr ) throws ValueError {
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
//        @Override
        public
        TreeNodeBase <N, A, G> createChild ( Point point, int layerIndex, int clusterIndex, IAddress <A> address )
                throws ValueError {

            return null;
        }


        /**
         * @param parent
         * @param image
         * @param boundingBox
         * @return
         */
        public
        TreeNode <N, A, G> createNode ( TreeNodeBase <N, A, G> parent,
                                           Point point,
                                           IImage <A> image,
                                           IIntSize boundingBox )
                throws ValueError {

            return null;
        }

        /**
         * @return
         */
        public
        int getLayerIndex () {
            return layerIndex;
        }
    }
}

