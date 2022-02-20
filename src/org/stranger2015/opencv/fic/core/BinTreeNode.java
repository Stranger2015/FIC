package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.HashMap;

/**
 * @param <N>
 * @param <A>
 */
public
class BinTreeNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer>
        extends TreeNode <N, A, M, G> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    BinTreeNode ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect )
            throws ValueError {

        super(parent, rect);
    }

    public
    BinTreeNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {

        super(parent, boundingBox);
    }

    @Override
    public
    TreeNode <N, A, M, G> createChild ( int address ) throws ValueError {
        return null;
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
    TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, Address<A> address )
            throws ValueError {
        return null;
    }

//    @Override
    public
    TreeNode <N, A, M, G> createChild ( int layerIndex, int clusterIndex, Address<A> address )
            throws ValueError {
        return null;
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the {@code hashCode} method
     *     must consistently return the same integer, provided no information
     *     used in {@code equals} comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     *     method, then calling the {@code hashCode} method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link Object#equals(Object)}
     *     method, then calling the {@code hashCode} method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined
     * by class {@code Object} does return distinct integers for
     * distinct objects. (The hashCode may or may not be implemented
     * as some function of an object's memory address at some point
     * in time.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public
    int hashCode () {
        return super.hashCode();
    }

//    @Override
    public
    TreeNode <N, A, M, G> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError {
        return new BinTreeNode <>(this, quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
//    @Override
    public
    TreeNodeBase <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox ) throws ValueError {
        return new BinTreeNode <>(parent, boundingBox);
    }

    public
    BinTreeNode <N, A, M, G> createNode ( BinTreeNode <N, A, M, G> parent,
                                       EDirection quadrant, Rect boundingBox )
            throws ValueError {
        return new BinTreeNode <>(parent, quadrant, boundingBox);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public
    boolean equals ( Object obj ) {
        return super.equals(obj);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public
    String toString () {
        return super.toString();
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
        return 0;
    }

    /**
     * @param <N>
     * @param <A>
     * @param <M>
     * @param <G>
     */
    public static
    class BinLeafNode<N extends LeafNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
            G extends BitBuffer>
            extends LeafNode <N, A, M, G> {

        /**
         * @param parent
         * @param image
         * @param rect
         */
        protected
        BinLeafNode ( TreeNode <N, A, M, G> parent, M image, Rect rect ) {
            super(parent, image, rect);
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

            return new BinLeafNode <N, A, M, G>(this, null);
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
        TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, Address<A> address )
                throws ValueError {

            return null;
        }

        /**
         * @param parent
         * @param boundingBox
         * @return
         */
        @Override
        public
        TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox )
                throws ValueError {

            return null;
        }

        @Override
        public
        TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox ) throws ValueError {
            return new BinLeafNode <>(parent, image, boundingBox);
        }

        /**
         * @param parent
         * @param quadrant
         * @param boundingBox
         * @return
         */
//        @Override
        public
        TreeNode<N, A, M, G> createNode ( TreeNode <N, A, M, G> parent,
                                       EDirection quadrant,
                                       Rect boundingBox )
                throws ValueError {

            return null;//todo
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
