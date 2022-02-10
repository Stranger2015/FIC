package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.SipTreeNodeBuilder.BB;

/**
 * @param <N>
 * @param <A>
 */
public
class SipTreeNode<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage>
        extends SaTreeNode <N, A, M> {

    /**
     * @param parent
     * @param boundingBox
     */
    public
    SipTreeNode ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    SipTreeNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox ) {
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
    SipTreeNode ( TreeNode <N, A, M> parent, EDirection hexant, Rect boundingBox ) throws ValueError {
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
    @SuppressWarnings("unchecked")
    public
    TreeNode <N, A, M> createChild ( Rect boundingBox ) throws ValueError {
        return new SipTreeNode <>(this, boundingBox);
    }

    /**
     * @param <N>
     * @param <A>
     */
    public static
    class SipLayerClusterNode<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends IImage>
            extends SaLayerClusterNode <N, A, M> {

        protected static final List <SipImageBlock> blocks = new ArrayList <>();

        private int address;
        private int layerIndex;
        private int clusterIndex;

        /**
         * @param parent
         * @param imageBlock
         * @param boundingBox
         */
//        @SuppressWarnings("unchecked")
        public
        SipLayerClusterNode ( TreeNode <N, A, M> parent,
                              SipImage imageBlock, Rect boundingBox, int layerIndex,
                              int clusterIndex,
                              int x,
                              int y,
                              int address ) {
            super(parent, layerIndex, clusterIndex, x, y, address);
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         */
        public
        SipLayerClusterNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox )
                throws ValueError {
            super(parent, image, boundingBox, 0, 0);
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
        SipLayerClusterNode ( TreeNode <N, A, M> node,
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
         * @param imageBlock
         * @param boundingBox
         * @param layerIndex
         * @param clusterIndex
         * @param address
         */
        public
        SipLayerClusterNode ( TreeNode <N, A, M> node,
                              SipImage imageBlock,
                              Rect boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              int address ) {

            this(node, imageBlock, boundingBox,layerIndex, clusterIndex, 0, 0, address);
        }

        public
        SipLayerClusterNode ( TreeNode <N, A, M> node, SipImage image, Rect boundingBox ) {
            this(node, image,boundingBox, 0,0,0,0,0);
        }

        /**
         * @param parent
         * @param quadrant
         * @param boundingBox
         * @return
         */
        public
        TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent,
                                        EDirection quadrant,
                                        Rect boundingBox )
                throws ValueError {
            throw new IllegalStateException("in SipLayerClusterNode::createNode()");
        }

        /**
         * @param imageBlock
         * @param layerIndex
         * @param clusterIndex
         * @param x
         * @param y
         * @param address
         * @return
         * @throws ValueError
         */
        public
        TreeNode <N, A, M> createChild ( ImageBlock imageBlock, int layerIndex, int clusterIndex, int x, int y, int address )
                throws ValueError {
            TreeNode <N, A, M> n = new SipLayerClusterNode <>(
                    this,
                    imageBlock,
                    BB,
                    layerIndex,
                    clusterIndex,
                    x, y,
                    address

            );

            this.children.add(n);

            return n;
        }

        /**
         * @return
         */
        public
        List <SipImageBlock> getBlocks () {
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
            return imageBlock.getY();
        }

        /**
         * @return
         */
        @Override
        public
        int getAddress () {
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
        TreeNode <N, A, M> createChild ( Point point, int layerIndex, int clusteIndex, int x, int y, int address ) throws ValueError {
            return null;
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNodeBase <N, A, M> createNode ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
            return null;
        }
    }
}
