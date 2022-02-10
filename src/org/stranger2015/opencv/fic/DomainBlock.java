package org.stranger2015.opencv.fic;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.Pixel;
import org.stranger2015.opencv.fic.utils.Point;

/**
 * 8 X 8
 */
public
class DomainBlock<N extends DomainBlock <N, A, M>, A extends Address <A>, M extends IImage>
        extends LeafNode <N, A, M>
        implements IImage {

    public final static int W = 8;
    public final static int H = 8;

    /**
     * @param parent
     * @param image
     * @param rect
     */
    public
    DomainBlock ( TreeNode <N, A, M> parent, M image, Rect rect ) {
        super(parent, image, rect);
    }

    /**
     * @param address
     */
    public
    DomainBlock ( int address ) {
        super(address);
    }

    /**
     * @param point
     * @param layerIndex
     * @param clusteIndex
     * @param x
     * @param y
     * @param address
     * @return
     * @throws ValueError
     */
    @Override
    public
    TreeNode <N, A, M> createChild ( Point point,
                                     int layerIndex,
                                     int clusteIndex,
                                     int x,
                                     int y,
                                     int address )
            throws ValueError {

        return new DomainBlock <>(address);
    }

    /**
     * @param layerIndex
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
//    @Override
    public//todo fixme
    TreeNode <N, A, M> createChild ( int layerIndex, int imageBlock, int address ) throws ValueError {
        return new DomainBlock <>(null, null);//fixme
    }

    /**
     *
     * @param parent
     * @param boundingBox
     * @throws ValueError
     */
    public
    DomainBlock ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
        super(parent, null, boundingBox);
    }

    /**
     *
     *
     * @return
     */
    @Override
    public
    int getX () {
        return imageBlock.getX();
    }

    /**
     *
     *
     * @return
     */
    @Override
    public
    int getY () {
        return imageBlock.getY();
    }

    /**
     *
     *
     * @return
     */
    @Override
    public
    int getWidth () {
        return imageBlock.getWidth();
    }

    /**
     *
     *
     * @return
     */
    @Override
    public
    int getHeight () {
        return imageBlock.getHeight();
    }

    /**
     *
     *
     * @return
     */
    @Override
    public
    Size getSize () {
        return new Size();
    }//fixme

    /**
     * @param contractivity
     */
    @Override
    public
    IImage contract ( int contractivity ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int width () {
        return 0;
    }

    /**
     * @return
     */
    @Override
    public
    int height () {
        return 0;
    }

    /**
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     * @return
     */
    @Override
    public
    Mat submat ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return new Mat().submat(rowStart, rowEnd,colStart,colEnd);//fixme
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageWidth () {
        return 0;
    }

    /**
     * @param originalImageWidth
     */
    @Override
    public
    void setOriginalImageWidth ( int originalImageWidth ) {

    }

    /**
     * @param originalImageHeight
     */
    @Override
    public
    void setOriginalImageHeight ( int originalImageHeight ) {

    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return 0;
    }

    @Override
    public
    int get ( int x, int y, int[] ia ) {
        return 0;
    }

    @Override
    public
    void put ( int destX, int destY, int pixel ) {

    }

    @Override
    public
    void put ( Pixel[] pixels, double v, double v1, double v2, double v3 ) {

    }

    @Override
    public
    int cols () {
        return 0;
    }

    @Override
    public
    int rows () {
        return 0;
    }

    /**
     * @param parent
     * @param quadrant
     * @param boundingBox
     * @return
     */
//    @Override
    public
    LeafNode <N, A, M> createNode ( TreeNode<N, A, M> parent, EDirection quadrant, Rect boundingBox )
            throws ValueError {
        throw new IllegalStateException();
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
    public                              //todo
    int compareTo ( @NotNull N o ) {
        return super.compareTo(o);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N, A, M> createNode ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
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
}
