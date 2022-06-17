package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.Point;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static org.stranger2015.opencv.fic.core.TreeNodeBase.EType.*;

/**
 * @param <N>
 */
abstract public
class TreeNodeBase<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    protected static int indexCounter;
    protected int index;//ID

    protected EDirection quadrant;
    protected IAddress <A> address;
    protected IIntSize boundingBox;
    protected EType type;
    protected int distance;

    protected TreeNodeBase <N, A, G> parent;

    /**
     * @param rect
     */
    public
    TreeNodeBase ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect ) throws ValueError {
        this.parent = parent;
        this.index = indexCounter++;

        this.boundingBox = rect;

        setDistance(0);
        setType(WHITE);

        this.quadrant = quadrant;
    }

    /**
     * @param parent
     * @param rect
     */
    @Contract(pure = true)
    public
    TreeNodeBase ( TreeNodeBase <N, A, G> parent, IIntSize rect ) {
        this.parent = parent;
        this.boundingBox = rect;
    }

    public
    TreeNodeBase ( TreeNodeBase <N, A, G> parent, int addr ) throws ValueError {
        this(parent, IAddress.valueOf(addr));
    }

    public
    TreeNodeBase ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) {
        this.parent = parent;
        this.address = address;
    }

    /**
     * @return
     */
    public
    IAddress <A> getAddress () {
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
     * @param addr
     * @return
     * @throws ValueError
     */
    public abstract
    TreeNodeBase <N, A, G> createChild ( int addr ) throws ValueError;

    /**
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
    public abstract
    TreeNodeBase <N, A, G> createChild ( IImageBlock <A> imageBlock,
//                                        int layerIndex,
//                                        int clusterIndex,
                                         IAddress <A> address ) throws ValueError;

    /**
     * @return
     */
    public
    IIntSize getBoundingBox () {
        return boundingBox;
    }

    /**
     * @param boundingBox
     */
    public
    void setBoundingBox ( IIntSize boundingBox ) {
        this.boundingBox = boundingBox;
    }

    /**
     * @return
     */
    public
    IIntSize getRect () {
        return boundingBox;
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
    public
    int compareTo ( @NotNull TreeNodeBase <N, A, G> o ) {
        return index - o.index;
    }

    public
    EDirection getQuadrant () {
        return quadrant;
    }

    public
    void setQuadrant ( EDirection quadrant ) {
        this.quadrant = quadrant;
    }

    public abstract
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError;

    /**
     *
     */
    enum EType {
        BLACK,
        GRAY,
        WHITE,
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
     * @param address
     * @return
     */
    public abstract
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IAddress <A> address )
            throws ValueError;

    /**
     * @param o
     * @return
     */
    @SuppressWarnings("unchecked")
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
        return format("TreeNodeBase{index=%d}", index);
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
    TreeNodeBase <N, A, G> getParent () {
        return parent;
    }

    /**
     * @param <N>
     * @param <A>
     */
    public abstract static
    class TreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
            extends TreeNodeBase <N, A, G> {

        protected final List <TreeNodeBase <N, A, G>> children = new ArrayList <>();

        /**
         * Creates a root node
         *
         * @param quadrant
         * @param boundingBox
         * @throws ValueError
         */
        public
        TreeNode ( EDirection quadrant, IIntSize boundingBox ) throws ValueError {
            super(null, quadrant, boundingBox);
        }

        /**
         * @param parent
         * @param width
         * @param height
         */
        public
        TreeNode ( TreeNodeBase <N, A, G> parent, int width, int height ) {
            super(parent, new IntSize(width, height));
        }

        /**
         * @param parent
         * @param direction
         * @param rect
         * @throws ValueError
         */
        public
        TreeNode ( TreeNodeBase <N, A, G> parent, EDirection direction, IIntSize rect ) throws ValueError {
            super(parent, direction, rect);
        }

        /**
         * @param parent
         * @param address
         */
        public
        TreeNode ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) {
            super(parent, address);
        }

        /**
         * @param parent
         * @param boundingBox
         * @throws ValueError
         */
        public
        TreeNode ( TreeNodeBase <N, A, G> parent, IIntSize boundingBox ) throws ValueError {
            super(parent, null, boundingBox);
        }

        /**
         * @return
         */
        public
        List <TreeNodeBase <N, A, G>> getChildren () {
            return children;
        }

        /**
         * @param dir
         * @return
         */
        @SuppressWarnings("unchecked")
        public
        TreeNodeBase <N, A, G> getChild ( int dir ) {
            return children.get(dir);
        }

        /**
         * @param q1
         * @param child
         */
        public
        void setChild ( EDirection q1, TreeNodeBase <N, A, G> child ) {
            setChild(q1.getOrd(), child);
        }

        /**
         * @param index
         * @param child
         */
        public
        void setChild ( int index, TreeNodeBase <N, A, G> child ) {
            children.add(index, child);
        }

        /**
         * @return
         */
        public
        EDirection getQuadrant () {
            return quadrant;
        }

        /**
         * @param ord
         * @param size
         * @return
         */
        public
        TreeNodeBase <N, A, G> createChild ( int ord, IIntSize size ) {
            return null;
        }

        /**
         * @param <N>
         * @param <A>
         */
        public static
        class LeafNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

                extends TreeNodeBase <N, A, G>
                implements ILeaf <N, A, G> {

            protected IImageBlock <A> imageBlock;//todo final

            /**
             * @param parent
             * @param quadrant
             * @param rect
             */
            public
            LeafNode ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect ) throws ValueError {
                super(parent, quadrant, rect);
            }

            /**
             * @param parent
             * @param rect
             */
            public
            LeafNode ( TreeNodeBase <N, A, G> parent, IIntSize rect ) {
                super(parent, rect);
            }

            public
            LeafNode ( TreeNodeBase <N, A, G> parent, int addr ) throws ValueError {
                this(parent, IAddress.valueOf(addr));
            }

            public
            LeafNode ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) {
                super(parent, address);
            }

            /**
             * @param addr
             * @return
             * @throws ValueError
             */
            @Override
            public
            TreeNodeBase <N, A, G> createChild ( int addr ) throws ValueError {
                return null;
            }

            /**
             * @param imageBlock
             * @param address
             * @return
             * @throws ValueError
             */
            @Override
            public
            TreeNodeBase <N, A, G> createChild ( IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
                if (imageBlock == null) {
                    return null;
                }


                return null;
            }

            /**
             * @param parent
             * @param address
             * @return
             */
            @Override
            public
            TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
                return new LeafNode <>(parent, imageBlock, address);
            }

            public
            LeafNode ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IAddress <A> address ) throws ValueError {
                this(parent, address);
                if (imageBlock == null) {
                    throw new ValueError("ib == 0???");
                }
                this.imageBlock = imageBlock;
            }

            public
            LeafNode ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock ) throws ValueError {
                this(parent, imageBlock, IAddress.valueOf(0));
            }

            public
            LeafNode ( TreeNodeBase <N, A, G> parent, IImageBlock <A> imageBlock, IIntSize rect ) throws ValueError {
                super(parent, rect);
                if (imageBlock == null) {
                    throw new ValueError("ib == 0???");
                }

                this.imageBlock = imageBlock;
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

            /**
             * @return
             */
            @Override
            public
            IImageBlock <A> getImageBlock () {
                return imageBlock;
            }

            /**
             * @return
             */
            @Override
            public
            MatOfInt getMat () {
                return null;
            }

            /**
             * @return
             */
            @Override
            public
            IImage <A> getImage () {
                return null;
            }
        }

        protected IIntSize size;
        protected Point point;
        protected List <ImageTransform <A, ?>> transforms;

        /**
         * @param contractivity
         */
//            @Override
        public
        IImage <A> contract ( int contractivity ) {
            return null;
        }

        /**
         * @param rowStart
         * @param rowEnd
         * @param colStart
         * @param colEnd
         * @return
         */
//            @Override
        public
        Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd ) {
            return subImage(rowStart, rowEnd, colStart, colEnd).getMat();
        }

        /**
         * @param address
         * @param pixel
         */
//            @Override
        public
        int putPixel ( IAddress <A> address, int[] pixel ) throws ValueError {
            return 0;
        }

        /**
         *
         */
//            @Override
        public
        List <IImage <A>> split () {
            return null;
        }

        /**
         * @param layers
         * @param inputImage
         * @return
         */
//            @Override
        public
        IImage <A> merge ( List <IImage <A>> layers, IImage <A> inputImage ) {
            return null;
        }

        /**
         *
         */
//            @Override
        public
        void release () {

        }

        /**
         * @return
         */
//            @Override
        public
        String dump () {
            return null;
        }

        /**
         * @param x
         * @param y
         * @param width
         * @param height
         * @return
         */
//            @Override
        public
        IImageBlock <A> getSubImage ( int x, int y, int width, int height ) throws ValueError {
            return null;
        }

        /**
         * @return
         */
//            @Override
        public
        List <ImageTransform <A, ?>> getTransforms () throws ValueError {
            return transforms;
        }

        /**
         * @param transforms
         */
//            @Override
        public
        void setTransforms ( List <ImageTransform <A, ?>> transforms ) throws ValueError {
            this.transforms = transforms;
        }

        /**
         * @return
         */
//            @Override
        public
        int[] getPixels () {
            return new int[0];
        }

        /**
         * @return
         */
//            @Override
        public
        boolean isGrayScale () {
            return false;
        }

        /**
         * @return
         */
//            @Override
        public
        List <IImage <A>> getComponents () {
            return null;
        }

        /**
         * @return
         */
//            @Override
        public//fixme here??
        List <RegionOfInterest <A>> getRegions () {
            return null;
        }

        /**
         * @param regions
         */
//            @Override
        public
        void setRegions ( List <RegionOfInterest <A>> regions ) {

        }

        /**
         * @param addr
         * @return
         */
        //    @Override
        public
        int[] get ( int addr ) {
            return new int[0];
        }

        /**
         * @param addr
         * @return
         */
        public
        int[] getPixels ( IAddress <A> addr ) {
            return new int[0];
        }

        /**
         * @return
         */
        public
        int getWidth () {
            return 0;
        }

        /**
         * @return
         */
//            @Override
        public
        int getHeight () {
            return 0;
        }

        /**
         * @return
         */
//            @Override
        public
        double getBeta () {
            return 0;
        }

        /**
         * @param address
         */
//            @Override
        public
        void setAddress ( IAddress <A> address ) {
            this.address = address;
        }

        /**
         * @param n
         * @return
         */
//            @Override
        public
        int plus ( int... n ) {
            return 0;
        }

        /**
         * @param rowStart
         * @param rowEnd
         * @param colStart
         * @param colEnd
         * @return
         */
//            @Override
        public
        IImageBlock <A> subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
            return new ImageBlock <>();
        }

        /**
         * @return
         */
//            @Override
        public
        IIntSize getSize () {
            return size;
        }

        /**
         * Sets a sample in the specified band for the pixel located at (x,y)
         * in the DataBuffer using an int for input.
         * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
         * not in bounds.
         *
         * @param address
         * @param b       The band to set.
         * @param s       The input sample as an int.
         * @throws NullPointerException           if data is null.
         * @throws ArrayIndexOutOfBoundsException if the coordinates or
         *                                        the band index are not in bounds.
         */
//            @Override
        public
        void setSample ( IAddress <A> address, int b, int s ) {

        }

        /**
         * Returns the sample in a specified band for the pixel located
         * at (x,y) as an int.
         * ArrayIndexOutOfBoundsException may be thrown if the coordinates are
         * not in bounds.
         *
         * @param address
         * @param b       The band to return.
         * @return the sample in a specified band for the specified pixel.
         * @throws NullPointerException           if data is null.
         * @throws ArrayIndexOutOfBoundsException if the coordinates or
         *                                        the band index are not in bounds.
         */
//            @Override
        public
        int getSample ( IAddress <A> address, int b ) {
            return 0;
        }

        /**
         * @param i
         * @param i1
         * @param sideSize
         * @param img1pixels
         * @param i2
         * @param i3
         */
//            @Override
        public
        void getRGB ( int i, int i1, int sideSize, int[] img1pixels, int i2, int i3 ) {

        }

        /**
         * @param x
         * @param y
         * @return
         */
//            @Override
        public
        int pixelValues ( int x, int y ) {
            return 0;
        }

        /**
         * @param x
         * @param i
         */
//            @Override
        public
        void put ( int x, int i ) {

        }

        /**
         * @return
         */
        //@Override
        public
        int getMeanPixelValue () {
            return 0;
        }

        /**
         * @return
         */
        //@Override
        public
        int getX () {
            return 0;
        }

        /**
         * @return
         */
//        @Override
        public
        int getY () {
            return 0;
        }
//
//        /**
//         * @return
//         */
//        @Override
//        public
//        IImageBlock <A> getImageBlock () {
//            return imageBlock;
//        }

//        /**
//         * @return
//         */
//        @Override
//        public
//        MatOfInt getMat () {
//            return null;
//        }

//        /**
//         * @return
//         */
//        @Override
//        public
//        IImage <A> getImage () {
//            return null;
//        }

        /**
         * @param addr
         * @return
         * @throws ValueError
         */
        @Override
        public
        TreeNodeBase <N, A, G> createChild ( int addr ) throws ValueError {
            return null;
        }

//        /**
//         * @param imageBlock
//         * @param address
//         * @return
//         * @throws ValueError
//         */
//            @Override
//        public
//        QuadTreeNode.QuadLeafNode <N, A, G> createChild ( TreeNodeBase <N, A, G> parent,
//                                                          IImageBlock <A> imageBlock,
//                                                int layerIndex,
//                                                int clusterIndex,
//                                                          IAddress <A> address ) throws ValueError {
//            return new QuadLeafNode <>(parent, imageBlock, -1, -1, address);
//        }
//
//        /**
//         * @param parent
//         * @param boundingBox
//         * @return
//         */
//        @Override
//        public
//        TreeNode <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IIntSize boundingBox )
//                throws ValueError {
//
//            return new QuadTreeNode <>(parent, boundingBox);
//        }

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
//            @Override
        public
        int compareTo ( @NotNull IImage <A> o ) {
            return getWidth() * getWidth() + getHeight() * getHeight() - 0;
            //o.getWidth() * o.getWidth() + o.getHeight() * o.getHeight();
        }
    }
}

