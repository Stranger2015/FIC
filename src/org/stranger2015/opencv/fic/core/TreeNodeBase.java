package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static org.stranger2015.opencv.fic.core.TreeNodeBase.EType.*;

/**
 * @param <N>
 */
abstract public
class TreeNodeBase<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        implements Comparable <N> {

    protected static int indexCounter;
    protected int index;

    //    protected IAddress <A/*, ?*/> address;
    protected int address;

    protected Rect boundingBox;

    protected TreeNodeBase <N, A, M> parent;

    protected EType type;

    private int distance;

    /**
     * @param parent
     * @param rect
     */
    public
    TreeNodeBase ( TreeNode<N, A, M> parent, Rect rect ) throws ValueError {
        this.index = indexCounter++;
        this.address = 0;//fixme newAddress(index);

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

    public
//    <A extends Address <A>, M extends Image, N extends TreeNode <N, A, M>>
    TreeNodeBase ( TreeNode<N,A,M> parent, M image, Rect boundingBox ) {

    }

    protected
    TreeNodeBase () {
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
     * @param quadrant
     * @param boundingBox
     * @return
     */
    public abstract
    TreeNodeBase <N, A, M> createChild ( EDirection quadrant, Rect boundingBox ) throws ValueError;

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
    TreeNodeBase <N, A, M> createNode ( TreeNode <N, A, M> parent, Rect boundingBox )
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
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    protected
    A newAddress ( int index ) throws ValueError {
        return (A) new Address <>(index);
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
    TreeNodeBase <N, A, M> getParent () {
        return parent;
    }

    /**
     * @param <N>
     * @param <A>
     */
    public abstract static
    class TreeNode<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
            extends TreeNodeBase <N, A, M> {

        protected final List <N> children = new ArrayList <>();
        protected EDirection quadrant;

        /**
         * @param parent
         * @param rect
         */
        public
        TreeNode ( TreeNode <N, A, M> parent, Rect rect ) throws ValueError {
            super(parent, rect);
//            this.quadrant = quadrant;
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

        public
//        <A e?xtends SaAddress <A>, M extends Image, N extends TreeNode <N, A, M>>
        TreeNode ( TreeNode<N, A, M> parent, M image, Rect boundingBox ) {
            super(parent, image, boundingBox);
        }

        protected
        TreeNode () {
        }
//
//        public//fixme
////        <A extends SaAddress <A/*, ?*/>, N extends TreeNode <N, A, M>, M extends Image>
//        TreeNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox ) {
//            this.parent = parent;
//          //  this.image = image;
//            this.boundingBox = boundingBox;
//        }

        /**
         * @return
         */
        public
        List <N> getChildren () {
            return children;
        }

        /**
         * @param dir
         * @return
         */
        public
        N getChild ( EDirection dir ) {
            return children.get(dir.getOrd());
        }

        /**
         * @return
         */
        @Override
        public
        TreeNode <N, A, M> getParent () {
            return (TreeNode <N, A, M>) super.getParent();
        }

        /**
         * @param q1
         * @param o
         */
        public
        void setChild ( EDirection q1, N o ) {
            children.add(q1.getOrd(), o);
        }

        public
        EDirection getQuadrant () {
            return quadrant;
        }

        /**
         * @param <N>
         * @param <A>
         * @param <M>
         */
        public abstract static
        class LeafNode<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
                extends TreeNode <N, A, M>
                implements ILeaf <N, A, M> {

            protected final ImageBlock <M> imageBlock;

            /**
             * @param parent
             * @param image
             * @param rect
             * @throws ValueError
             */
            protected
            LeafNode ( TreeNode <N, A, M> parent, M image, Rect rect ) throws ValueError {
                super(parent, rect);
                this.imageBlock = createImageBlock(image, rect);
            }

            @SuppressWarnings("unchecked")
            protected
            ImageBlock <M> createImageBlock ( M image, Rect rect ) {
                return (ImageBlock <M>) image.submat(rect);
            }

            /**
             * @param parent
             * @param image
             * @param x
             * @param y
             * @param width
             * @param height
             */
            public
            LeafNode ( TreeNode <N, A, M> parent, M image, int x, int y, int width, int height )
                    throws ValueError {
                this(parent, image, new Rect(x, y, width, height));
            }

            /**
             * @param parent
             * @param image
             * @param rect
             * @param address
             * @throws ValueError
             */
            public
            LeafNode ( TreeNode <N, A, M> parent, M image, Rect rect, /*Address <A/*, ?*/int address )
                    throws ValueError {

                this(parent, image, rect.x, rect.y, rect.width, rect.height);
                this.address = address;
            }

            /**
             * @param quadrant
             * @param boundingBox
             * @return
             */
            @Override
            public
            TreeNode <N, A, M> createChild ( EDirection quadrant, Rect boundingBox ) {
                throw new IllegalStateException("createChild() in LeafNode");
            }

            /**
             * @return
             */
            @Override
            public
            ImageBlock <M> getImageBlock () {
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
            TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, M image, Rect boundingBox )
                    throws ValueError;
        }
    }
}
