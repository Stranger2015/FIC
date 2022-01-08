package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;

/**
 * @param <N>
 * @param <A>
 */
public
class HvTreeNode<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends TreeNodeBase <N, A, M> {
    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    HvTreeNode ( TreeNode <N, A, M> parent, EDirection quadrant, Rect rect ) throws ValueError {
        super(parent, null, rect);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
    public
    HvTreeNode <N, A, M> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new HvTreeNode <>(null, quadrant, boundingBox);
    }


    /**
     * @param parent
     * @param quadrant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    HvTreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, EDirection quadrant, Rect boundingBox )
            throws ValueError {
        return new HvTreeNode <>(parent, quadrant, boundingBox);
    }

    @Override
    public
    TreeNode <N, A, M> createChild ( int address ) throws ValueError {
      throw new UnsupportedOperationException();//  return new HvTreeNode<N, A,M>();
    }

    @Override
    public
    TreeNode <N, A, M> createChild ( int layerIndex, int imageBlock, int address ) throws ValueError {
        throw new UnsupportedOperationException();
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode<N, A, M> createNode ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
        return null;
    }

    /**
     * @param <N>
     * @param <A>
     */
    public static
    class HvLeafNode<N extends HvLeafNode <N, A, M>, A extends Address <A>, M extends Image>
            extends LeafNode <N, A, M>
            implements ILeaf <N, A, M> {

        /**
         * @param parent
         * @param image
         * @param rect
         * @throws ValueError
         */
        protected
        HvLeafNode ( TreeNode <N, A, M> parent, M image, Rect rect ) throws ValueError {
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
        HvLeafNode <N, A, M> createNode ( TreeNode<N, A, M> parent, EDirection quadrant, Rect boundingBox )
                throws ValueError {
            return new HvLeafNode <>(parent, null, boundingBox);//fixme
        }

        //        @Override
        public
        HvLeafNode <N, A, M> createNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox )
                throws ValueError {
            return new HvLeafNode <>(parent, image, boundingBox);
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNodeBase <N, A, M> createNode ( TreeNode <N, A, M> parent, Rect boundingBox )
                throws ValueError {
            return null;
        }

        /**
         * @return
         */
        @Override
        public
        M getMat () {
            return null;
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

        @Override
        public
        TreeNode <N, A, M> createChild ( int layerIndex, int imageBlock, int address ) throws ValueError {
            return null;
        }
    }
}
