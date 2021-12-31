package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 */
public
class TriangleTreeNode<N extends TriangleTreeNode<N, A>, A extends Address<A,?>> extends TreeNode<N,A>{
    /**
     * @param parent
     * @param quadrant
     * @param boundingBox
     * @throws ValueError
     */
    public
    TriangleTreeNode ( TriangleTreeNode<N,A> parent,EDirection quadrant, Rect boundingBox ) throws ValueError {
        super(parent, boundingBox);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @throws ValueError
     */
    public
    TriangleTreeNode ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        super((EDirection) null, boundingBox);
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNodeBase <N, A> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new TriangleTreeNode <>(quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNodeBase <N, A> createNode ( TreeNodeBase <N, A> parent, Rect boundingBox ) throws ValueError {
        return null;//todo
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNodeBase <N, A> createNode ( TriangleTreeNode<N, A> parent, Rect boundingBox ) throws ValueError {
        return /*new TriangleTreeNode <N, A>(parent,boundingBox);*/null;
    }

    public static
    class TriangleLeafNode<N extends TriangleLeafNode<N, A, M>,A extends Address<A,?>, M extends Image>
    extends LeafNode<N,A,M>{

        /**
         * @param parent
         * @param image
         * @param rect
         * @throws ValueError
         */
        protected
        TriangleLeafNode ( TreeNode <N, A> parent, M image, Rect rect ) throws ValueError {
            super(parent, image, rect);
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
         * @param image
         * @param boundingBox
         * @return
         * @throws ValueError
         */
//        @Override
        public
        TreeNodeBase <N, A> createNode ( TreeNodeBase <N, A> parent, M image, Rect boundingBox ) throws ValueError {
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
        TreeNode <N, A> createNode ( TreeNode <N, A> parent, M image, Rect boundingBox ) throws ValueError {
            return null;
        }
    }
}
