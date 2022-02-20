package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Objects;

import static java.lang.String.format;
import static org.stranger2015.opencv.fic.core.TreeNodeBase.EType.*;

/**
 * @param <N>
 */
abstract public
class TreeNodeBase<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
        G extends BitBuffer>
        implements Comparable <N> {

    protected static int indexCounter;
    protected int index;

    protected Address<A> address;

    protected Rect boundingBox;

    protected TreeNodeBase <N, A, M, G> parent;

    protected EType type;

    private int distance;

    /**
     * @param parent
     * @param rect
     */
    public
    TreeNodeBase ( TreeNode <N, A, M, G> parent, Rect rect ) throws ValueError {
        this.index = indexCounter++;
//        this.address = 0;

        this.boundingBox = rect;
        this.parent = parent;

        setDistance(0);
        setType(WHITE);
    }

    /**
     * @param quadrant
     * @param boundingBox
     */
    protected
    TreeNodeBase ( EDirection quadrant, Rect boundingBox ) {
    }

    /**
     * @param parent
     * @param image
     * @param boundingBox
     */
    public
    TreeNodeBase ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox ) {

    }

    /**
     *
     */
    protected
    TreeNodeBase () {
    }

    /**
     * @return
     */
    public
    Address<A> getAddress () {
        return address;
    }

    /**
     * @return
     */
    public
    EType getType () {
        return type;
    }

    /**
     * @param type
     */
    public
    void setType ( EType type ) {
        this.type = type;
    }

    /**
     * @param address
     * @return
     * @throws ValueError
     */
    public abstract
    TreeNode <N, A, M, G> createChild ( int address ) throws ValueError;

    /**
     * @param point
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @return
     * @throws ValueError
     */
    public abstract
    TreeNode <N, A, M, G> createChild ( Point point, int layerIndex, int clusterIndex, Address<A> address )
            throws ValueError;

    /**
     *
     */
    enum EType {
        BLACK,
        GRAY,
        WHITE,
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
        return this.boundingBox.width - o.boundingBox.width;
    }

    /**
     * @return
     */
    public
    int getIndex () {
        return index;
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    public abstract
    TreeNodeBase <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, Rect boundingBox )
            throws ValueError;

    /**
     * @param o
     * @return
     */
    @Override
    public
    boolean equals ( Object o ) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TreeNodeBase)) {
            return false;
        }
        N treeNode = (N) o;
        return index == treeNode.index;
    }

    /**
     * @return
     */
    @Override
    public
    int hashCode () {
        return Objects.hash(index);
    }

    /**
     * @return
     */
    @Override
    public
    String toString () {
        return format("TreeNodeBase{index=%s}", index);
    }

    /**
     * @return
     */
    public final
    boolean isLeaf () {
        return this instanceof ILeaf;
    }

    /**
     * @return
     */
    public
    boolean isBlack () {
        return type == BLACK;
    }

    /**
     * @return
     */
    public
    int getDistance () {
        return distance;
    }

    /**
     * @param distance
     */
    public
    void setDistance ( int distance ) {
        this.distance = distance;
    }

    /**
     * @return
     */
    public
    boolean isWhite () {
        return type == WHITE;
    }

    /**
     * @return
     */
    public
    boolean isGray () {
        return type == GRAY;
    }

    /**
     * @return
     */
    public
    TreeNodeBase <N, A, M, G> getParent () {
        return parent;
    }

    /**
     * @param <N>
     * @param <A>
     */
    public abstract static
    class TreeNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>, G extends BitBuffer>
            extends TreeNodeBase <N, A, M, G> {

        protected final NodeList <N, A, M, G> children = new NodeList <>();
        protected EDirection quadrant;

        /**
         * @param parent
         * @param rect
         */
        public
        TreeNode ( TreeNode <N, A, M, G> parent, Rect rect ) throws ValueError {
            super(parent, rect);
        }

        /**
         * @param quadrant
         * @param boundingBox
         * @throws ValueError
         */
        public
        TreeNode ( EDirection quadrant, Rect boundingBox ) throws ValueError {
            super(quadrant, boundingBox);
        }

        /**
         * @param parent
         * @param image
         * @param boundingBox
         */
        public
        TreeNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox ) {
            super(parent, image, boundingBox);
        }

        /**
         *
         */
        protected
        TreeNode () {
        }

        /**
         * @return
         */
        public
        NodeList <N, A, M, G> getChildren () {
            return children;
        }

        /**
         * @param dir
         * @return
         */
        @SuppressWarnings("unchecked")
        public
        N getChild ( int dir ) {
            return (N) children.get(dir);
        }

        /**
         * @return
         */
        @Override
        public
        TreeNode <N, A, M, G> getParent () {
            return (TreeNode <N, A, M, G>) super.getParent();
        }

        /**
         * @param q1
         * @param o
         */
        public
        void setChild ( EDirection q1, N o ) {
            setChild(q1.getOrd(), o);
        }

        /**
         * @param index
         * @param o
         */
        public
        void setChild ( int index, N o ) {
            children.add(index, o);
        }

        /**
         * @return
         */
        public
        EDirection getQuadrant () {
            return quadrant;
        }

        public
        N createChild ( int ord, Rectangle rectangle ) {

            return null;
        }

        public
        N getChild ( EDirection dir ) {
            return null;
        }

        /**
         * @param <N>
         * @param <A>
         * @param <M>
         */
        public abstract static
        class LeafNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>,
                G extends BitBuffer>

                extends TreeNode <N, A, M, G>
                implements ILeaf <N, A, M, G> {

            protected /*final */Address <A> address;
            protected ImageBlock<A> imageBlock;

            public
            LeafNode ( Address<A> address ) {
                this.address = address;
            }

            protected
            LeafNode () {
                address = null;
            }

            public
            LeafNode ( TreeNode <N, A, M, G> parent, Point point, ImageBlock <A> image, Rect boundingBox ) {

            }

            /**
             * @return
             */
            @Override
            public
            Address <A> getAddress () {
                return address;
            }

            /**
             * @param parent
             * @param image
             * @param rect
             * @param address
             * @throws ValueError
             */
//            @SuppressWarnings("unchecked")
            protected
            LeafNode ( TreeNode <N, A, M, G> parent, Point point, ImageBlock <A> image, Rect rect, Address <A> address )
                    throws ValueError {

                super(parent, rect);

                this.imageBlock = image;
//                this.address = address;
                this.imageBlock.setAddress(point.getX(), point.getY());
            }

            /**
             * @param parent
             * @param image
             * @param width
             * @param height
             */
            @SuppressWarnings("unchecked")
            public
            LeafNode ( TreeNode <N, A, M, G> parent,
                       Point point,
                       M image,
                       int width,
                       int height ) throws ValueError {

                this(
                );
            }

            /**
             * @param parent
             * @param image
             * @param rect
             */
            public
            LeafNode ( TreeNode <N, A, M, G> parent, M image, Rect rect ) {
                this(parent, image, rect.width, rect.height);
            }

            /**
             * @param parent
             * @param image
             * @param width
             * @param height
             */
            protected
            LeafNode ( TreeNode <N, A, M, G> parent, M image, int width, int height ) {
            }

            /**
             * @return
             */
            @Override
            public
            TreeNode <N, A, M, G> createChild ( int address ) {
                throw new IllegalStateException("createChild() in LeafNode");
            }

            /**
             * @return
             */
            @Override
            public
            ImageBlock<A> getImageBlock () {
                return imageBlock;
            }

            /**
             * @param parent
             * @param image
             * @param boundingBox
             * @return
             * @throws ValueError
             */
            public abstract
            TreeNode <N, A, M, G> createNode ( TreeNode <N, A, M, G> parent, M image, Rect boundingBox )
                    throws ValueError;
        }
    }
}
