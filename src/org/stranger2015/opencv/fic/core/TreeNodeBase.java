package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.utils.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static org.stranger2015.opencv.fic.core.TreeNodeBase.EType.*;

/**
 * @param <N>
 */
abstract public
class TreeNodeBase<N extends TreeNode <N>> {
    protected static int indexCounter;
    protected int id;

    protected EDirection quadrant;
    protected IAddress address;
    protected Rectangle boundingBox;
    protected EType type;
    protected int distance;

    protected TreeNodeBase <N> parent;
    protected Class <?> treeClass;

    /**
     * @param rect
     */
    public
    TreeNodeBase ( TreeNodeBase <N> parent, EDirection quadrant, Rectangle rect ) throws ValueError {
        this.parent = parent;
        this.id = indexCounter++;

        this.boundingBox = rect;

        setDistance(0);
        setType(WHITE);

        this.quadrant = quadrant;
        treeClass = parent.getTreeClass();
    }

    /**
     * @param parent
     * @param rect
     */
    @Contract(pure = true)
    public
    TreeNodeBase ( TreeNodeBase <N> parent, Rectangle rect ) {
        this.parent = parent;
        this.boundingBox = rect;
        treeClass = parent.getTreeClass();
    }

    /**
     * @param parent
     * @param addr
     * @param treeClass
     * @throws ValueError
     */
    public
    TreeNodeBase ( TreeNodeBase <N> parent, IAddress address, Class <?> treeClass ) throws ValueError {
        this(parent, address);
        this.treeClass = treeClass;
    }

    /**
     * @param parent
     * @param quadrant
     * @param address
     */
    public
    TreeNodeBase ( TreeNodeBase <N> parent, IAddress address ) {
        this.parent = parent;
        this.address = address;
        treeClass = parent.getTreeClass();
    }

    /**
     * @param parent
     * @param layerIndex
     * @param treeClass
     */
    @Contract(pure = true)
    public
    TreeNodeBase ( TreeNodeBase <N> parent,
                   int layerIndex,
                   Class <?> treeClass ) {

        this.parent = parent;
        this.treeClass = treeClass;
    }

    /**
     * @return
     */
    public
    IAddress getAddress () {
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
    TreeNodeBase <N> createChild ( IAddress address ) throws ValueError;

    /**
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
    public abstract
    TreeNodeBase <N> createChild ( IImageBlock  imageBlock, IAddress  address ) throws ValueError;

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
    void setBoundingBox ( Rectangle boundingBox ) {
        this.boundingBox = boundingBox;
    }

    /**
     * @return
     */
    public
    Rectangle getRect () {
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
    int compareTo ( @NotNull TreeNodeBase <N> o ) {
        return id - o.id;
    }

    /**
     * @return
     */
    public
    EDirection getQuadrant () {
        return quadrant;
    }

    /**
     * @param quadrant
     */
    public
    void setQuadrant ( EDirection quadrant ) {
        this.quadrant = quadrant;
    }

    /**
     * @param parent
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
    public abstract
    TreeNodeBase <N> createNode ( TreeNodeBase <N> parent,
                                        IImageBlock  imageBlock,
                                        IAddress  address ) throws ValueError;

    /**
     * @return
     */
    public
    Class <?> getTreeClass () {
        return treeClass;
    }

    public
    LeafNode <N> createChild ( int ord, Rectangle rectangle ) {
        return null;//todo
    }

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
    int getId () {
        return id;
    }

    /**
     * @param parent
     * @param address
     * @return
     */
    public abstract
    TreeNodeBase <N> createNode ( TreeNodeBase <N> parent, IAddress  address )
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

        return id == treeNode.id;
    }

    /**
     * @return
     */
    @Override
    public
    int hashCode () {
        return Objects.hash(id);
    }

    /**
     * @return
     */
    @Override
    public
    String toString () {
        return format("TreeNodeBase{ index = %d}", id);
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
    TreeNodeBase <N> getParent () {
        return parent;
    }

    /**
     * @param <N>
     * @param
     */
    public abstract static
    class TreeNode<N extends TreeNode <N>>
            extends TreeNodeBase <N> {

        protected final List <TreeNodeBase <N>> children = new ArrayList <>();

        /**
         * Creates a root node
         *
         * @param quadrant
         * @param boundingBox
         * @throws ValueError
         */
        public
        TreeNode ( EDirection quadrant, Rectangle boundingBox ) throws ValueError {
            super(null, quadrant, boundingBox);
        }

        /**
         * @param parent
         * @param width
         * @param height
         */
        public
        TreeNode ( TreeNodeBase <N> parent, int width, int height ) throws ValueError {
            super(parent, new Rectangle(width, height));
        }

        /**
         * @param parent
         * @param direction
         * @param rect
         * @throws ValueError
         */
        public
        TreeNode ( TreeNodeBase <N> parent, EDirection direction, Rectangle rect ) throws ValueError {
            super(parent, direction, rect);
        }

        /**
         * @param parent
         * @param address
         */
        public
        TreeNode ( TreeNodeBase <N> parent, IAddress  address ) {
            super(parent, address);
        }

        /**
         * @param parent
         * @param boundingBox
         * @throws ValueError
         */
        public
        TreeNode ( TreeNodeBase <N> parent, Rectangle boundingBox ) throws ValueError {
            super(parent, null, boundingBox);
        }

        /**
         * @param parent
         * @param quadrant
         * @param address
         */
        public
        TreeNode ( TreeNodeBase <N> parent, EDirection quadrant, IAddress  address ) {
            super(parent, address);
        }

        /**
         * @param parent
         * @throws ValueError
         */
        public
        TreeNode ( TreeNodeBase <N> parent ) throws ValueError {
            super(parent, 0, parent.treeClass);
        }

        /**
         * @param parent
         * @param addr
         * @throws ValueError
         */
//        public
//        TreeNode ( TreeNodeBase <N> parent, IAddress addr ) throws ValueError {
//            super(parent, addr, parent.treeClass);
//        }

        /**
         * @return
         */
        public
        List <TreeNodeBase <N>> getChildren () {
            return children;
        }

        /**
         * @param dir
         * @return
         */
        @SuppressWarnings("unchecked")
        public
        TreeNodeBase <N> getChild ( EDirection dir ) {
            return children.get(dir.getOrd());
        }

        /**
         * @param q1
         * @param child
         */
        public
        void setChild ( EDirection q1, TreeNodeBase <N> child ) {
            setChild(q1.getOrd(), child);
        }

        /**
         * @param index
         * @param child
         */
        public
        void setChild ( int index, TreeNodeBase <N> child ) {
            children.add(index, child);
        }

        /**
         * @return
         */
        @Override
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
        LeafNode <N> createChild ( int ord, Rectangle size ) {
            return null;
        }

        /**
         * @param <N>
         * @param
         */
        public static class
        LeafNode<N extends TreeNode <N>>
                extends TreeNodeBase <N>
                implements ILeaf <N> {

            protected IImageBlock  imageBlock;

            /**
             * @param parent
             * @param quadrant
             * @param rect
             */
            public
            LeafNode ( TreeNodeBase <N> parent,
                       EDirection quadrant,
                       Rectangle rect ) throws ValueError {

                super(parent, quadrant, rect);
            }

            /**
             * @param parent
             * @param rect
             */
            public
            LeafNode ( TreeNodeBase <N> parent, Rectangle rect ) {
                super(parent, rect);
            }

            /**
             * @param address@return
             * @throws ValueError
             */
            @Override
            public
            TreeNodeBase <N> createChild ( IAddress  address ) throws ValueError {
                return createNode(this, address);
            }

            /**
             * @param imageBlock
             * @param address
             * @return
             * @throws ValueError
             */
            @Override
            public
            TreeNodeBase <N> createChild ( IImageBlock  imageBlock, IAddress  address ) throws ValueError {
                return createLeafNode(this, imageBlock, address);
            }

            /**
             * @param parent
             * @param imageBlock
             * @param address
             * @return
             */
            public
            LeafNode <N> createLeafNode ( TreeNodeBase <N> parent,
                                                IImageBlock  imageBlock,
                                                IAddress  address ) throws ValueError {
                LeafNode <N> leaf = (LeafNode <N>) createNode(parent, address);
                leaf.imageBlock = imageBlock;

                return leaf;
            }

            /**
             * @param parent
             * @param imageBlock
             * @param address
             * @return
             * @throws ValueError
             */
            @Override
            public
            TreeNodeBase <N> createNode ( TreeNodeBase <N> parent,
                                                IImageBlock  imageBlock,
                                                IAddress  address )
                    throws ValueError {

                return createLeafNode(parent, imageBlock, address);
            }

            /**
             * @param parent
             * @param address
             * @return
             */
            @Override
            public
            TreeNodeBase <N> createNode ( TreeNodeBase <N> parent, IAddress  address ) throws ValueError {
                return null;
            }

            /**
             * @param parent
             * @param imageBlock
             * @throws ValueError
             */
            public
            LeafNode ( TreeNodeBase <N> parent, IImageBlock  imageBlock ) throws ValueError {
                this(parent, imageBlock, new Rectangle(imageBlock.getSize()));
            }

            /**
             * @param parent
             * @param imageBlock
             * @param rect
             * @throws ValueError
             */
            public
            LeafNode ( TreeNodeBase <N> parent, IImageBlock  imageBlock, Rectangle rect ) throws ValueError {
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
            IImageBlock  getImageBlock () {
                return imageBlock;
            }

            /**
             * @param imageBlock
             */
            public
            void setImageBlock ( IImageBlock  imageBlock ) {
                this.imageBlock = imageBlock;
            }

            /**
             * @return
             */
            @Override
            public
            MatOfInt getMat () {
                return (MatOfInt) getImageBlock().getMat();
            }

            /**
             * @return
             */
            @Override
            public
            IImage  getImage () {
                return null/*image*/;
            }
        }

        protected IIntSize size;
        protected Point point;
        protected List <ImageTransform > transforms;

        /**
         * @param contractivity
         */
//            @Override
        public
        IImage  contract ( int contractivity ) {
            return null;
        }

//        /**
//         * @param rowStart
//         * @param rowEnd
//         * @param colStart
//         * @param colEnd
//         * @return
//         */
//            @Override
        public
        Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd ) throws ValueError {
            return subImage(rowStart, rowEnd, colStart, colEnd).getMat();
        }

        /**
         * @param address
         * @param pixel
         */
//            @Override
        public
        void putPixel ( IAddress  address, double[] pixel ) throws ValueError {
            put(address.getX(), address.getY(), pixel);
        }

        /**
         *
         */
//            @Override
        public
        List <IImage > split () {
            return null;
        }

        /**
         * @param layers
         * @param inputImage
         * @return
         */
//            @Override
        public
        IImage merge ( List <IImage > layers, IImage inputImage ) {
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
        IImageBlock  getSubImage ( int x, int y, int width, int height ) throws ValueError {
            return null;
        }

        /**
         * @return
         */
//            @Override
        public
        List <ImageTransform > getTransforms () throws ValueError {
            return transforms;
        }

        /**
         * @param transforms
         */
//            @Override
        public
        void setTransforms ( List <ImageTransform> transforms ) throws ValueError {
            this.transforms = transforms;
        }

        /**
         * @return
         */
//            @Override
        public
        double[] getPixels () {
            return null;
        }//fixme

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
        List <IImage > getComponents () {
            return List.of();
        }

        /**
         * @return
         */
//            @Override
        public//fixme here??
        List <IImageBlock > getRegions () {
            return null;
        }

        /**
         * @param regions
         */
//            @Override
        public
        void setRegions ( List <IImageBlock > regions ) {

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
        double[] getPixels ( IAddress  addr ) {
            return null;
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
        void setAddress ( IAddress  address ) {
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
        IImageBlock  subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) throws ValueError {
            return new ImageBlock (null, rowStart, rowEnd, colStart, colEnd, null);
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
        void setSample ( IAddress  address, int b, int s ) {

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
        int getSample ( IAddress  address, int b ) {
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
         * @param pixel
         */
//            @Override
        public
        void put ( int x, int i, double[] pixel ) {

        }

        /**
         * @return
         */
        //@Override
        public
        double[] getMeanPixelValue () {
            return new double[]{255, 255, 255, 255};//fixme
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
        int compareTo ( @NotNull IImage o ) {
            return getWidth() * getWidth() + getHeight() * getHeight();//fixme
        }
    }
}

