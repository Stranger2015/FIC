package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.SipTreeNodeBuilder.BB;

/**
 * @param <N>
 * @param <A>
 */
public
class SipTreeNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends SaTreeNode <N, A, M, G> {

    protected final List <SipImageBlock <A>> blocks=new ArrayList <>();

    /**
     * @param parent
     * @param boundingBox
     */
    public
    SipTreeNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    SipTreeNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox ) {
        super(parent, image, boundingBox);
    }

    /**
     *
     */
    public
    SipTreeNode () {
    }

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @throws ValueError
     */
    public
    SipTreeNode ( TreeNode <N, A, M, G> parent, EDirection hexant, Rect boundingBox ) throws ValueError {
        super(parent, hexant, boundingBox);
    }

    /**
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
//    @Override
    protected
    A newAddress ( int index ) throws ValueError {
        return (A) new SipAddress <>(index);
    }

    /**
     * @param boundingBox
     * @return
     */
//    @SuppressWarnings("unchecked")
    public
    TreeNode <N, A, M, G> createChild ( Rect boundingBox ) throws ValueError {
        return new SipTreeNode <>(this, boundingBox);
    }

    /**
     * @param <N>
     * @param <A>
     */
    public static
    class SipLayerClusterNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
            G extends BitBuffer>

            extends SaLayerClusterNode <N, A, M, G> {

        protected static final List <SipImageBlock<?>> blocks = new ArrayList <>();

//        private int address;
        protected int layerIndex;
        protected int clusterIndex;

        /**
         * @param parent
         * @param imageBlock
         * @param boundingBox
         */
//        @SuppressWarnings("unchecked")
        public
        SipLayerClusterNode ( TreeNode <N, A, M, G> parent,
                              SipImage<A> imageBlock,
                              Rect boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              int x,
                              int y) {
            super(parent, layerIndex, clusterIndex);
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         */
        public
        SipLayerClusterNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox )
                throws ValueError {
            super(parent, image, boundingBox, 0, 0);
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
        TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, IAddress <A> address ) throws ValueError {
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
        SipLayerClusterNode ( TreeNode <N, A, M, G> node,
                              ImageBlock image,
                              Rect boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              int x,
                              int y,
                              int address ) throws ValueError {

            this(node, (M) image, boundingBox);

            imageBlock.setX(x);
            imageBlock.setY(y);
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
        SipLayerClusterNode ( TreeNode <N, A, M, G> node,
                              SipImage<A> image,
                              Rect boundingBox,
                              int layerIndex,
                              int clusterIndex) {

            this(node, image, boundingBox,layerIndex, clusterIndex, 0, 0);
        }

        public
        SipLayerClusterNode ( TreeNode <N, A, M, G> node, SipImage<A> image, Rect boundingBox ) {
            this(node, image,boundingBox, 0,0,0,0);
        }

        /**
         * @param parent
         * @param quadrant
         * @param boundingBox
         * @return
         */
        public
        TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent,
                                        EDirection quadrant,


            throw new IllegalStateException("in SipLayerClusterNode::createNode()");
        }

        /**
         * @param imageBlock
         * @param layerIndex
         * @param clusterIndex
         * @param x
         * @param y
         * @return
         * @throws ValueError
         */
        public
        TreeNode <N, A, M, G> createChild ( ImageBlock<A> imageBlock, int layerIndex, int clusterIndex, int x, int y )
                throws ValueError {

            TreeNode <N, A, M, G> n = new SipLayerClusterNode <>(
                    this,
                    imageBlock,
                    BB,
                    layerIndex,
                    clusterIndex,
            //        x, y,
                    new SipAddress<>(x, y)
            );

            this.children.add(n);

            return n;
        }

        /**
         * @return
         */
        public
        List <SipImageBlock<A>> getBlocks () {
            return blocks;
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
            return imageBlock.getAddress().getY();
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
        M getMat () {
            return (M) imageBlock.image;
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
         * @param clusteIndex
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
}
