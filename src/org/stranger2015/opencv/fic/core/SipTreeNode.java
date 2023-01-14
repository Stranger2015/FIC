package org.stranger2015.opencv.fic.core;

import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param
 */
public
class SipTreeNode<N extends TreeNode <N>>
        extends SaTreeNode{

    protected final List <SipImageBlock > blocks = new ArrayList <>();

    /**
     * @param parent
     * @param boundingBox
     */
    public
    SipTreeNode ( TreeNodeBase<N> parent, IIntSize boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    SipTreeNode ( TreeNodeBase <N> parent, IImage image, IIntSize boundingBox ) {
        super(parent, image, boundingBox);
    }

//    /**
//     *
//     */
//    public
//    SipTreeNode () {
//    }

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @throws ValueError
     */
    public
    SipTreeNode ( TreeNodeBase <N> parent, EDirection hexant, IntSize boundingBox ) throws ValueError {
        super(parent, hexant, boundingBox);
    }

    /**
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
//    @Override
    protected
    IAddress newAddress ( int index ) throws ValueError {
        return new SipAddress <>(index);
    }

    /**
     * @param boundingBox
     * @return
     */
    public
    TreeNodeBase<N> createChild ( IIntSize boundingBox ) throws ValueError {
        return new SipTreeNode <>(this, boundingBox);
    }

    /**
     * @param <N>
     * @param
     */
    public static
    class SipLayerClusterNode<N extends TreeNode <N>, A extends IAddress , /* IImage extends IImage */,

            G extends BitBuffer>
            extends SaLayerClusterNode <N> {

        protected static final List <ImageBlock <?>> blocks = new ArrayList <>();

        protected int layerIndex;
        protected int clusterIndex;

        /**
         * @param parent
         * @param imageBlock
         * @param boundingBox
         */
//        @SuppressWarnings("unchecked")
        public
        SipLayerClusterNode ( TreeNodeBase <N> parent,
                              IImageBlock  imageBlock,
                              IIntSize boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              IAddress  address
        ) {
            super(parent, imageBlock, boundingBox, address);

            this.layerIndex=layerIndex;
            this.clusterIndex=clusterIndex;
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         */
        public
        SipLayerClusterNode ( TreeNodeBase <N> parent,     IImageBlock image, IIntSize boundingBox ) throws ValueError {
            super(parent, image, boundingBox, 0, 0);
        }

        /**
         * @param parent
         * @param imageBlock
         * @param layerIndex
         * @param clusterIndex
         * @param addr
         */
        public
        SipLayerClusterNode ( TreeNodeBase <N>  parent,
                              IImageBlock  imageBlock,
                              int layerIndex,
                              int clusterIndex,
                              int addr ) throws ValueError {

            super(parent, imageBlock, layerIndex, clusterIndex, addr);
        }

        /**
         * @param imageBlock
         * @param bb
         * @param layerIndex
         * @param clusterIndex
         * @param address
         */
        public
        SipLayerClusterNode ( ImageBlock  imageBlock, IIntSize bb, int layerIndex, int clusterIndex, IAddress  address ) {
            super(imageBlock, bb, layerIndex, clusterIndex, address);
        }

        /**
         * @param parent
         * @param image
         * @param rect
         * @param layerIndex
         * @param address
         */
        protected
        SipLayerClusterNode ( TreeNode <N> parent, IImage image, IntSize rect, int layerIndex, IAddress  address ) {
            super(parent, image, rect, layerIndex, address);
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
        TreeNode <N> createChild ( IImageBlock  point, int layerIndex, int clusterIndex, IAddress  address )
                throws ValueError {
            return null;
        }

        /**
         * @param node
         * @param image
         * @param boundingBox
         * @param layerIndex
         * @param clusterIndex
         * @param address
         */
        public
        SipLayerClusterNode ( TreeNode <N> node,
                              ImageBlock  image,
                              IntSize boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              IAddress  address ) {

            this(node, image, boundingBox);

            this.address = address;
            this.layerIndex = layerIndex;
            this.clusterIndex = clusterIndex;
        }

        /**
         * @param node
         * @param image
         * @param boundingBox
         * @param layerIndex
         * @param clusterIndex
         */
        public
        SipLayerClusterNode ( TreeNode <N> node,
                              SipImage  image,
                              IntSize boundingBox,
                              int layerIndex,
                              int clusterIndex ) {

            this(node, image, boundingBox);
            this.layerIndex = layerIndex;
            this.clusterIndex = clusterIndex;
        }

//        TreeNode <N> createChild ( ImageBlock  imageBlock,
//                                            int layerIndex,
//                                            int clusterIndex,
//                                            IAddress  address )
//                throws ValueError {
//            this.address = address;
//
//            TreeNode <N> n = new SipLayerClusterNode <N>(
//                    imageBlock,
//                    layerIndex,
//                    clusterIndex,
//                    address
//            );
//
//            this.children.add(n);

//            return n;
//        }

        /**
         * @return
         */
        public
        List <ImageBlock <?>> getBlocks () {
            return blocks;
        }


        /**
         * @return
         */
        @Override
        public
        IAddress  getAddress () {
            return address;
        }

        /**
         * @return
         */
        @SuppressWarnings("unchecked")
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
            return null;
        }

        /**
         * @return
         */
        @Override
        public
        IIntSize getBoundingBox () {
            return boundingBox;
        }

        /**
         * @return
         */
//        @Override
        public
        int getLayerIndex () {
            return layerIndex;
        }

        /**
         * @return
         */
        public
        int getClusterIndex () {
            return clusterIndex;
        }

        /**
         * @param point
         * @param layerIndex
         * @param clusterIndex
         * @param addr
         * @return
         * @throws ValueError
         */
        @Override
        public
        TreeNode <N> createChild ( Point point,
                                            int layerIndex,
                                            int clusterIndex,
                                            int x,
                                            int y,
                                            int addr ) throws ValueError {
            return null;
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNodeBase <N> createNode ( TreeNode <N> parent, IntSize boundingBox ) throws ValueError {
            return null;
        }
    }
}
