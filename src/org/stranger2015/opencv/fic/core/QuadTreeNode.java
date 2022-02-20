package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class QuadTreeNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
        extends BinTreeNode <N, A, M, G> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    QuadTreeNode ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect ) throws ValueError {
        super(parent, quadrant, rect);
    }

    /**
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    @SuppressWarnings("*")
    public
    TreeNode <N, A, M, G> createChild ( EDirection quadrant, Rect rect ) throws ValueError {
        return new QuadTreeNode <>(this, quadrant, rect);
    }

    /**
     * @param <N>
     * @param <A>
     */
    public static
    class QuadLeafNode<N extends LeafNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
            G extends BitBuffer>
            extends LeafNode <N, A, M, G> {
        /**
         * @param parent
         * @param image
         * @param rect
         */
        protected
        QuadLeafNode ( TreeNode <N, A, M, G> parent, M image, Rect rect ) {
            super(parent,
                    image,
                    rect);
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
        TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, Address <A> address )
                throws ValueError {

            return null;
        }

        public
        TreeNode <N, A, M, G> createChild ( int layerIndex, int clusteIndex, int address ) throws ValueError {
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
        TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox ) throws ValueError {
            return null;
        }

        /**
         * @param parent
         * @param quadrant
         * @param boundingBox
         * @return
         */
        public
        QuadLeafNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect boundingBox )
                throws ValueError {
            return new QuadLeafNode <>(parent, null, boundingBox);//fixme
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {
            return new QuadLeafNode <>(parent, null, boundingBox);
        }

        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         *
         * <p>The implementor must ensure
         * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
         * for all {@code x} and {@code y}.  (This
         * implies that {@code x.compareTo(y)} must throw an exception iff
         * {@code y.compareTo(x)} throws an exception.)
         *
         * <p>The implementor must also ensure that the relation is transitive:
         * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
         * {@code x.compareTo(z) > 0}.
         *
         * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
         * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
         * all {@code z}.
         *
         * <p>It is strongly recommended, but <i>not</i> strictly required that
         * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
         * class that implements the {@code Comparable} interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         *
         * <p>In the foregoing description, the notation
         * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
         * <i>signum</i> function, which is defined to return one of {@code -1},
         * {@code 0}, or {@code 1} according to whether the value of
         * <i>expression</i> is negative, zero, or positive, respectively.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public
        int compareTo ( @NotNull N o ) {
            return this.boundingBox.x - o.boundingBox.x;//todo
        }

        /**
         * @return
         */
        @Override
        public
        int getX () {
            return 0;
        }

        /**
         * @return
         */
        @Override
        public
        int getY () {
            return 0;
        }

        public
        M getMat () {
            return null;//todo
        }

        @Override
        public
        M getImage () {
            return null;
        }

        @Override
        public
        Rect getBoundingBox () {
            return/* this.getBoundingBox(*/null;
        }
    }
}