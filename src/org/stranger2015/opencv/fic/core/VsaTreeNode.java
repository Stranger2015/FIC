package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 */
@SuppressWarnings("unchecked")
public
class VsaTreeNode<N extends TreeNode <N, A, M>, A extends SaAddress <A>, M extends Image>
        extends TreeNode <N, A, M> {

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @throws ValueError
     */
    public
    VsaTreeNode ( TreeNode <N, A, M> parent, EDirection hexant, Rect boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @throws ValueError
     */
    public
    VsaTreeNode ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    VsaTreeNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox ) {
        super(parent, image, boundingBox);
    }

    /**
     *
     */
    public
    VsaTreeNode () {
    }

    @Override
    public
    TreeNode <N, A, M> createChild ( int address ) throws ValueError {
        return new VsaLayerClusterNode <>();
    }

    @Override
    public
    TreeNode <N, A, M> createChild ( int clusterIndex, int address, int layerIndex ) throws ValueError {
        TreeNode <N, A, M> n = createChild(address);
        return n;
    }

    /**
     * @param index
     * @return
     */
    @Deprecated
    @Override
    protected
    A newAddress ( int index ) throws ValueError {
        return (A) new SaAddress <>(index);
    }

    /**
     * @param hexant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNodeBase <N, A, M> createChild ( EDirection hexant, Rect boundingBox ) throws ValueError {
        return new VsaTreeNode <>(this, hexant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
        return new VsaTreeNode <>(parent, boundingBox);
    }

    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @return
     */
    public
    TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, EDirection hexant, Rect boundingBox )
            throws ValueError {
        return new VsaTreeNode <>(parent, hexant, boundingBox);
    }

    /**
     * @param <N>
     * @param <A>
     * @param <M>
     */
    public static
    class VsaLayerClusterNode<N extends TreeNode <N, A, M>, A extends SaAddress <A>, M extends Image>
            extends LeafNode <N, A, M> {

        protected int layerIndex;

        /**
         * @param parent
         * @param image
         * @param rect
         * @throws ValueError
         */
        protected
        VsaLayerClusterNode ( TreeNode <N, A, M> parent, M image, Rect rect, int address, int layerno )
                throws ValueError {
            super(parent, image, rect, address);

            this.layerIndex = layerno;
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         * @throws ValueError
         */
        public
        VsaLayerClusterNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox ) throws ValueError {
            super(parent, (ImageBlock) image, boundingBox);
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         * @param layerIndex
         * @param clusterIndex
         * @param address
         */
        public
        VsaLayerClusterNode ( TreeNode <N, A, M> parent,
                              Image image,
                              Rect boundingBox,
                              int layerIndex,
                              int clusterIndex,
                              int address ) {
            super(parent, (M) image, boundingBox, layerIndex, clusterIndex, address);
        }

        /**
         *
         */
        public
        VsaLayerClusterNode () {
        }

        /**
         * @param layerIndex
         * @param clusterIndex
         * @param address
         * @return
         * @throws ValueError
         */
        @Override
        public
        TreeNode <N, A, M> createChild ( int layerIndex, int clusterIndex, int address ) throws ValueError {
            return new VsaLayerClusterNode<>();//fixme
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         * @return
         */
        public
        TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox )
                throws ValueError {
            return new VsaLayerClusterNode <>(parent, image, boundingBox);
        }

        /**
         * @return
         */
        public
        int getLayerIndex () {
            return layerIndex;
        }

        /**
         *
         *
         *
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
            return new VsaTreeNode <>(parent, boundingBox);
        }

        @Override
        public
        M getMat () {
            return null;
        }

        @Override
        public
        M getImage () {
            return null;
        }

        @Override
        public
        Rect getBoundingBox () {
            return null;
        }
    }
}
