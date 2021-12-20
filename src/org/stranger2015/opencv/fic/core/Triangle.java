package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.QuadTreeNode;

/**
 *
 */
public
class Triangle extends Polygon {
//    protected final Point p;

    /**
     * @param x
     * @param y
     * @param size
     */
    public
    Triangle ( int x, int y, int size ) {
        super(x, y,3, size);
    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Image image, Rect rect ) {

    }

    /**
     * @return
     */
    @Override
    public
    double area () {
        return 0;
    }

    /**
     * @param <N>
     */
    public static
    class TriangleTreeNode<N extends TriangleTreeNode <N,A>, A extends Address<A>>
            extends QuadTreeNode <N, A> {

        /**
         * @param parent
         * @param rect
         */
        public
        TriangleTreeNode (TriangleTreeNode<N, A> parent, Rect rect ) {
            super(parent, null, rect);
        }

//        public
//        TriangleTreeNode ( TreeNode <N, A> parent, Direction quadrant, Rect rect ) {
//            super(parent, quadrant, rect);
//        }

        /**
         * @param image
         * @param rect
         */
        @Override
        public
        void draw ( Image image, Rect rect ) {

        }
    }
}
