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
class SaTreeNode<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends TreeNode <N> {

    protected int layerIndex;

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @throws ValueError
     */
    public
    SaTreeNode ( TreeNodeBase <N> parent, EDirection hexant, IIntSize boundingBox ) throws ValueError {
        super(parent,hexant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @throws ValueError
     */
    public
    SaTreeNode ( TreeNodeBase <N> parent, IIntSize boundingBox ) throws ValueError {
        super(parent, null, boundingBox);
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    SaTreeNode ( TreeNodeBase <N> parent, IImage image, IIntSize boundingBox ) {
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

    @Override
    public
    TreeNodeBase <?> createNode ( TreeNodeBase <?> parent, IAddress  address ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode<N> createNode ( TreeNodeBase <N> parent, IIntSize boundingBox ) throws ValueError {
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
    TreeNode <N> createChild ( IImageBlock  imageBlock,
                                        int layerIndex,
                                        int clusterIndex,
                                        IAddress  address ) throws ValueError {
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
    TreeNode <N> createChild ( IImage image,
                                        int layerIndex,
                                        int clusterIndex,
                                        IAddress  address ) throws ValueError {
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
    TreeNode <N> createChild ( GrayScaleImage  ib,
                                        int layerIndex,
                                        int clusterIndex,
                                        IAddress  address ) throws ValueError {
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
    TreeNode <N> createChild ( Point point,
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
    TreeNodeBase <N> createChild ( EDirection hexant, IntSize boundingBox ) throws ValueError {
        return new SaTreeNode <>(this, hexant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N> createNode ( TreeNodeBase <N> parent, IntSize boundingBox ) throws ValueError {
        return new SaTreeNode <>(parent, boundingBox);
    }

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @return
     */
    public
    TreeNode <N> createNode ( TreeNode <N> parent, EDirection hexant, IIntSize boundingBox )
            throws ValueError {
        return new SaTreeNode <>(parent, hexant, boundingBox);
    }

    /**
     * @param <N>
     * @param
     */
    public static
    class SaLayerClusterNode<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
            extends LeafNode <N> {

        /**
         * @param parent
         * @param point
         * @param imageBlock
         * @param rect
         */
        protected
        SaLayerClusterNode ( TreeNodeBase <N> parent,
                             Point point,
                             IImageBlock  imageBlock,
                             IIntSize rect ) throws ValueError {
            super(parent, point, imageBlock, rect);
        }

        protected int layerIndex;
        protected IAddress  address;

        /**
         * @param parent
         * @param image
         * @param rect
         * @param layerIndex
         * @param address
         */
        protected
        SaLayerClusterNode ( TreeNodeBase <N> parent,
                             IImageBlock  image,
                             IIntSize rect,
                             int layerIndex,
                             int address ) throws ValueError {

            super(parent, null, image, rect);

            this.layerIndex = layerIndex;
            this.address = IAddress.valueOf( address, inputImage.getWidth(), i2);
        }

        /**
         * @param parent
         * @param point
         * @param image
         * @param boundingBox
         */
        protected
        SaLayerClusterNode ( TreeNode <N> parent, Point point, IImage image, IntSize boundingBox ) throws ValueError {
            super(parent, point, image.getSubImage(), boundingBox);
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
////        <N extends TreeNode <N>, A extends SipAddress , IImage extends IImage,
//        SaLayerClusterNode ( TreeNode <N> parent,
//                              IImage image,
//                              IntSize boundingBox,
//                              int layerIndex,
//                              IAddress address ) {
//            super(parent, image, image.getWidth(), image.getHeight());
//        }

//        public
////        </*M extends IImage,*/ A extends SipAddress , N extends TreeNode <N>>
//        SaLayerClusterNode ( TreeNode <N> parent,
//                             IImage image,
//                             int layerIndex,
//                             int clusterIndex,
//                             IAddress  address ) {
//            //todo
//            this(parent, image, layerIndex, clusterIndex, image.getOriginalImageSideSize(), address);
//        }

//        public
////        <N extends TreeNode <N>, A extends SipAddress , IImage extends IImage,
//        SaLayerClusterNode ( TreeNode <N> parent,
//                             IntSize boundingBox,
//                             int layerIndex,
//                             int clusterIndex,
//                             IAddress address ) {
//
//            this(parent, layerIndex, clusterIndex, 0, 0, address);
//        }

//        public
////        <N extends TreeNode <N>, A extends SipAddress , IImage extends IImage,
//        SaLayerClusterNode ( TreeNode <N> parent,
//                             int layerIndex,
//                             int clusterIndex,
//                             IAddress  address ) {
//            super(parent, image, rect.width, rect.height);
//        }

//        public
////        </*M extends IImage,*/ N extends TreeNode <N>, A extends SipAddress >
//        SaLayerClusterNode ( TreeNode <N> parent,
//                             int layerIndex,
//                             int clusterIndex,
//                             int i,
//                             int i1,
//                             IAddress  address ) {
//
//
//            super(parent, image, rect.width, rect.height);
//        }

//        public
//        SaLayerClusterNode ( TreeNode <N> parent, IImage image, IntSize boundingBox, int i, int i1 ) {
//
//            super(parent, image, boundingBox.width, boundingBox.height);
//        }

        public
        SaLayerClusterNode ( TreeNodeBase <N> parent,
                             IImageBlock  imageBlock,
                             int layerIndex,
                             int clusterIndex,
                             int addr ) throws ValueError {
            super(
                    parent,
                    imageBlock,
                    layerIndex,
                    clusterIndex,
                  IAddress.valueOf(  addr, inputImage.getWidth(), i2)
            );
        }

        public
        SaLayerClusterNode ( TreeNodeBase<N> parent,
                             IImageBlock imageBlock,
                             IIntSize boundingBox,
                             IAddress address ) {
            super(parent, imageBlock, boundingBox, address);
        }

        public
        SaLayerClusterNode ( ImageBlock imageBlock, IIntSize bb, int layerIndex, int clusterIndex, IAddress address ) {
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
        TreeNode <N> createNode ( TreeNodeBase <N> parent,
                                           IImageBlock  imageBlock,
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
        TreeNodeBase <N> createChild (
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
        IImageBlock  getImageBlock () {
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
        IImage getImage () {
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
        TreeNodeBase <N> createChild ( IImageBlock  imageBlock, IAddress  address ) throws ValueError {
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
        TreeNode <N> createNode ( TreeNodeBase <N> parent, IIntSize boundingBox ) throws ValueError {
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
        TreeNodeBase <N> createChild ( Point point,
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
        TreeNodeBase <N> createChild ( Point point, int layerIndex, int clusterIndex, IAddress  address )
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
        TreeNode <N> createNode ( TreeNodeBase <N> parent,
                                           Point point,
                                           IImage image,
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

