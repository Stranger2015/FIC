package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.core.codec.SipImage;

import java.util.ArrayList;
import java.util.List;

import static org.stranger2015.opencv.fic.core.SipTreeNodeBuilder.BB;

/**
 * @param <N>
 * @param <A>
 */
public
class SipTreeNode<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
        extends VsaTreeNode <N, A, M> {

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
    @Override
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
    static
    class SipLayerClusterNode<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
            extends VsaLayerClusterNode <N, A, M> {

        protected final List <SipImageBlock> blocks = new ArrayList <>();

        private int address;
        private int layerIndex;
        private int clusterIndex;

        /**
         * @param parent
         */
//        @SuppressWarnings("unchecked")
        public
        SipLayerClusterNode ( TreeNode <N, A, M> parent,
//                              M image,
//                              Rect boundingBox,
                              int address,
                              int layerIndex,
                              int clusterIndex ) {
            super(parent,
                    null,
                    BB,
                    address,
                    clusterIndex, layerIndex
            );
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         */
        public
        SipLayerClusterNode ( TreeNode <N, A, M> parent, SipImage image, Rect boundingBox ) {
            super(parent, image, boundingBox, 0, 0, 0);
        }

        /**
         * @param node
         * @param image
         * @param boundingBox
         * @param address
         * @param layerIndex
         * @param clusterIndex
         */
        public
        SipLayerClusterNode ( TreeNode <N, A, M> node,
                              SipImage image,
                              Rect boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              int address) {

            this(node, image, boundingBox);

            this.address = address;
            this.layerIndex = layerIndex;
            this.clusterIndex = clusterIndex;
        }

        /**
         * @param node
         * @param boundingBox
         * @param layerIndex
         * @param clusterIndex
         * @param address
         * @throws ValueError
         */
        public
        SipLayerClusterNode ( TreeNode <N, A, M> node,
                              Rect boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              int address ) throws ValueError {

            this(node, null, boundingBox, layerIndex, clusterIndex, address);
        }
/**
         * @param parent
         * @param quadrant
         * @param boundingBox
         * @return
         */
        public
        TreeNode <N, A, ? extends Image> createNode ( TreeNode <N, A, ? extends Image> parent,
                                                      EDirection quadrant,
                                                      Rect boundingBox )
                throws ValueError {
            throw new IllegalStateException("in SipLayerClusterNode::createNode()");
        }

        /**
         * @param address
         * @param layerIndex
         * @return
         */
//        @SuppressWarnings("unchecked")
        @Override
        public
        TreeNode <N, A, M> createChild ( int clusterIndex, int address, int layerIndex ) throws ValueError {
            TreeNode <N, A, M> n = new SipLayerClusterNode <>(
                    this,
                    BB,
                    clusterIndex, address, layerIndex
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
        public
        int getAddress () {
            return address;
        }

        /**
         * @return
         */
        @Override
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
    }
}
