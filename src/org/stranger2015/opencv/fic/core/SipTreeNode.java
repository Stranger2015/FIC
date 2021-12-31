package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.SipAddress;
import org.stranger2015.opencv.fic.utils.ImageUtils;

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
    public static
    class SipLayerClusterNode<N extends TreeNode <N, A, M>, A extends SipAddress <A>, M extends Image>
            extends VsaLayerClusterNode <N, A, M> {

        /**
         * @param parent
         * @param image
         * @param boundingBox
         */
        @SuppressWarnings("unchecked")
        public
        SipLayerClusterNode ( TreeNode <N, A, M> parent,
                              M image,
                              Rect boundingBox,
                              int address,
                              int layerno )
                throws ValueError {
            super(parent,
                    image,
                    boundingBox,
                    address,
                    layerno);
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         * @throws ValueError
         */
        public
        SipLayerClusterNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox ) throws ValueError {
            this(parent, image, boundingBox, 0, 0);
        }

        /**
         * @param index
         * @return
         */
        @SuppressWarnings("unchecked")
        @Override
        public
        A newAddress ( int index ) throws ValueError {
            return (A) new SipAddress <A>(index);
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

        @SuppressWarnings("unchecked")
        @Override
        protected
        ImageBlock <M> createImageBlock ( M image, Rect rect ) {
            ImageUtils.imageToSipImage(image);
            return new ImageBlock3x3 <M>(image, rect);
        }
    }
}
