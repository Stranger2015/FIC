package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 */
public
class TriangleTreeNode<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends TreeNode <N, A, M> {
    /**
     * @param parent
     * @param quadrant
     * @param boundingBox
     * @throws ValueError
     */
    public
    TriangleTreeNode ( TreeNode <N, A, M> parent, EDirection quadrant, Rect boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @throws ValueError
     */
    public
    TriangleTreeNode ( TreeNode <N, A, M> quadrant, Rect boundingBox ) throws ValueError {
        super((EDirection) null, boundingBox);
    }

    public
    TriangleTreeNode ( EDirection quadrant, Rect boundingBox ) {

    }

    @Override
    public
    TreeNode <N, A, M> createChild ( int address ) throws ValueError {
        return null;
    }

    @Override
    public
    TreeNode <N, A, M> createChild ( int layerIndex, int clusteIndex, int address )
            throws ValueError {
        return null;
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N, A, M> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new TriangleTreeNode <>(quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
        return new TriangleTreeNode <>(parent, boundingBox);
    }

    public static
    class TriangleLeafNode<N extends LeafNode <N, A, M>, A extends Address <A>, M extends Image>
            extends LeafNode <N, A, M> {

        /**
         * @param parent
         * @param image
         * @param rect
         */
        protected
        TriangleLeafNode ( TreeNode <N, A, M> parent, M image, Rect rect ) throws ValueError {
            super(parent, (ImageBlock) image, rect);
        }

        /**
         * @param layerIndex
         * @param clusteIndex
         * @param address
         * @return
         * @throws ValueError
         */
        @Override
        public
        TreeNode <N, A, M> createChild ( int layerIndex, int clusteIndex, int address ) throws ValueError {
            return null;
        }

        /**
         * @return
         */
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

        /**
         * @param parent
         * @param image
         * @param boundingBox
         * @return
         * @throws ValueError
         */
        @Override
        public
        TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox ) throws ValueError {
            return null;
        }
    }
}
