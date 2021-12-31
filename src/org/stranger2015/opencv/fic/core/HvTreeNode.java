package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.DomainBlock;

/**
 * @param <N>
 * @param <A>
 */
public
class HvTreeNode<N extends HvTreeNode <N, A>, A extends Address <A, ?>> extends TreeNodeBase <N, A> {
    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    HvTreeNode ( TreeNodeBase <N, A> parent, EDirection quadrant, Rect rect ) throws ValueError {
        super(parent,null, rect);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
    @Override
    public
    HvTreeNode <N, A> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new HvTreeNode <>(null, quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNodeBase <N, A> createNode ( TreeNodeBase <N, A> parent, Rect boundingBox ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param quadrant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    HvTreeNode <N, A> createNode ( TreeNodeBase <N, A> parent, EDirection quadrant, Rect boundingBox )
            throws ValueError {
        return new HvTreeNode <>(parent, quadrant, boundingBox);
    }

    /**
     * @param <N>
     * @param <A>
     */
    public static
    class HvLeafNode<N extends HvLeafNode <N, A, M>, A extends Address <A, ?>, M extends Image>
            extends LeafNode<N, A,M>
            implements ILeaf <N, A, M> {

//        private ImageBlock <M> imageBlock;

        /**
         * @param parent
         * @param image
         * @param rect
         * @throws ValueError
         */
        protected
        HvLeafNode ( TreeNodeBase <N, A> parent, Image image, Rect rect ) throws ValueError {
            super(parent, null, rect);//fixme
        }

        /**
         * @param parent
         * @param quadrant
         * @param boundingBox
         * @return
         */
//        @Override
        public
        HvLeafNode <N, A,M> createNode ( TreeNodeBase <N, A> parent, EDirection quadrant, Rect boundingBox ) throws ValueError {
            return new HvLeafNode <>(parent,null, boundingBox);//fixme
        }

//        /**
//         * @return
//         */
//        @Override
//        public
//        ImageBlock <M> getImageBlock () {
//            return imageBlock;
//        }

        @Override
        public
        TreeNodeBase <N, A> createNode ( TreeNodeBase <N, A> parent, M image, Rect boundingBox ) throws ValueError {
            return new HvLeafNode<>(parent,image,boundingBox);
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNodeBase <N, A> createNode ( TreeNodeBase <N, A> parent, Rect boundingBox ) throws ValueError {
            return null;
        }
    }
}
