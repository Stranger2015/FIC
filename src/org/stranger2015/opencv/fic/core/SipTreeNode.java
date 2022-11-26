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
 * @param <A>
 */
public
class SipTreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends SaTreeNode <N, A, G> {

    protected final List <SipImageBlock <A>> blocks = new ArrayList <>();

    /**
     * @param parent
     * @param boundingBox
     */
    public
    SipTreeNode ( TreeNodeBase<N, A, G> parent, IIntSize boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    SipTreeNode ( TreeNodeBase <N, A, G> parent, IImage<A> image, IIntSize boundingBox ) {
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
    SipTreeNode ( TreeNodeBase <N, A, G> parent, EDirection hexant, IntSize boundingBox ) throws ValueError {
        super(parent, hexant, boundingBox);
    }

    /**
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
//    @Override
    protected
    IAddress<A> newAddress ( int index ) throws ValueError {
        return new SipAddress <>(index);
    }

    /**
     * @param boundingBox
     * @return
     */
    public
    TreeNodeBase<N, A, G> createChild ( IIntSize boundingBox ) throws ValueError {
        return new SipTreeNode <>(this, boundingBox);
    }

    /**
     * @param <N>
     * @param <A>
     */
    public static
    class SipLayerClusterNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,

            G extends BitBuffer>
            extends SaLayerClusterNode <N, A, G> {

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
        SipLayerClusterNode ( TreeNodeBase <N, A, G> parent,
                              IImageBlock <A> imageBlock,
                              IIntSize boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              IAddress <A> address
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
        SipLayerClusterNode ( TreeNodeBase <N, A, G> parent,     IImageBlock<A> image, IIntSize boundingBox ) throws ValueError {
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
        SipLayerClusterNode ( TreeNodeBase <N, A, G>  parent,
                              IImageBlock <A> imageBlock,
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
        SipLayerClusterNode ( ImageBlock <A> imageBlock, IIntSize bb, int layerIndex, int clusterIndex, IAddress <A> address ) {
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
        SipLayerClusterNode ( TreeNode <N, A, G> parent, M image, IntSize rect, int layerIndex, IAddress <A> address ) {
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
        TreeNode <N, A, G> createChild ( IImageBlock <A> point, int layerIndex, int clusterIndex, IAddress <A> address )
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
        SipLayerClusterNode ( TreeNode <N, A, G> node,
                              ImageBlock <A> image,
                              IntSize boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              IAddress <A> address ) {

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
        SipLayerClusterNode ( TreeNode <N, A, G> node,
                              SipImage <A> image,
                              IntSize boundingBox,
                              int layerIndex,
                              int clusterIndex ) {

            this(node, image, boundingBox);
            this.layerIndex = layerIndex;
            this.clusterIndex = clusterIndex;
        }

//        TreeNode <N, A, G> createChild ( ImageBlock <A> imageBlock,
//                                            int layerIndex,
//                                            int clusterIndex,
//                                            IAddress <A> address )
//                throws ValueError {
//            this.address = address;
//
//            TreeNode <N, A, G> n = new SipLayerClusterNode <N, A, G>(
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
        IAddress <A> getAddress () {
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
        IImage<A> getImage () {
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
        TreeNode <N, A, G> createChild ( Point point,
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
        TreeNodeBase <N, A, G> createNode ( TreeNode <N, A, G> parent, IntSize boundingBox ) throws ValueError {
            return null;
        }
    }
}
