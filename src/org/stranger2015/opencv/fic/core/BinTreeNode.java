package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.RegionOfInterest;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.HashMap;
import java.util.List;

/**
 * @param <N>
 * @param <A>
 */
public
class BinTreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends TreeNode <N, A, G> {

    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    BinTreeNode ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect ) throws ValueError {
        super(parent, quadrant, rect);
    }

    public
    BinTreeNode ( TreeNodeBase <N, A, G> parent, IAddress <A> address ) {
        super(parent, address);
    }

    /**
     * @param imageBlock
     * @param address
     * @return
     * @throws ValueError
     */
    public
    TreeNodeBase <N, A, G> createChild ( TreeNodeBase <N, A, G> parent,
                                         IImageBlock <A> imageBlock,
                                         IAddress <A> address )
            throws ValueError {

        return imageBlock == null ?
                new BinTreeNode <>(parent, address) :
                new LeafNode <>(parent, imageBlock);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNodeBase <N, A, G> createNode ( TreeNodeBase <N, A, G> parent, IIntSize boundingBox )
            throws ValueError {

        return new BinTreeNode<>(parent, boundingBox);
    }

    /**
     * @param node
     * @param bb
     */
    public
    BinTreeNode ( TreeNodeBase <N, A, G> node, IIntSize bb ) throws ValueError {
        super(node, bb);
    }


    /**
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @return
     * @throws ValueError
     */
    public
    TreeNodeBase <N, A, G> createChild ( int layerIndex, int clusterIndex, IAddress <A> address )
            throws ValueError {

        //  return createChild(new Point(0, 0), layerIndex, clusterIndex, address);
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

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     * @throws ValueError
     */
    public
    TreeNodeBase <N, A, G> createChild ( EDirection quadrant, IIntSize boundingBox ) throws ValueError {
        return new BinTreeNode <>(this, quadrant, boundingBox);
    }

    /**
     * @param parent
     * @param boundingBox
     * @return
     */
    public
    TreeNodeBase <N, A, G> createNode ( TreeNode <N, A, G> parent, IIntSize boundingBox ) throws ValueError {
        return new BinTreeNode <>(parent, boundingBox);
    }

    public
    BinTreeNode <N, A, G> createNode ( BinTreeNode <N, A, G> parent,
                                       EDirection quadrant,
                                       IIntSize boundingBox )
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
//    @Override
    public
    int compareTo ( @NotNull N o ) {
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
//    @Override
    public
    void getRGB ( int i, int i1, int sideSize, int[] img1pixels, int i2, int i3 ) {

    }

    /**
     * @param address
     * @return
     */
//    @Override
    public
    int[] get ( int address ) {
        return new int[0];
    }

    //    @Override
    public
    int[] getPixels ( IAddress <A> address ) {
        return new int[0];
    }

    //    @Override
    public
    int pixelValues ( int addr, int i ) {
        return 0;
    }

    /**
     * @return
     */
        @Override
    public
    int getWidth () {
        return 0;
    }

    /**
     * @return
     */
        @Override
    public
    int getHeight () {
        return 0;
    }

    /**
     * @return
     */
        @Override
    public
    double getBeta () {
        return 0;
    }

    /**
     * @return
     */
//        @Override
    public
    int pixelAmount () {
        return 0;
    }

    /**
     * @param address
     */
//        @Override
    public
    void setAddress ( IAddress <A> address ) {

    }

    /**
     * @param n
     * @return
     */
//        @Override
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
    @Override
    public
    IImageBlock <A> subImage ( int rowStart, int rowEnd, int colStart, int colEnd ) {
        return null;
    }

    /**
     * @return
     */
//        @Override
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
//        @Override
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
//        @Override
    public
    int getSample ( IAddress <A> address, int b ) {
        return 0;
    }

    /**
     * @param x
     * @param y
     */
//        @Override
    public
    void setAddress ( int x, int y ) throws ValueError {

    }

    /**
     *
     */
//        @Override
    public
    List <IImage <A>> split () {
        return null;
    }

    /**
     * @param layers
     * @param inputImage
     * @return
     */
//        @Override
    public
    IImage <A> merge ( List <IImage <A>> layers, IImage <A> inputImage ) {
        return null;
    }

    /**
     *
     */
//    @Override
    public
    void release () {

    }

    /**
     * @return
     */
//    @Override
    public
    String dump () {
        return null;
    }

    //    @Override
    public
    MatOfInt getMat () {
        return getImage().getMat();
    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
//    @Override
    public
    IImageBlock <A> getSubImage ( int x, int y, int width, int height ) throws ValueError {
        return null;
    }

    /**
     * @return
     */
//    @Override
    public
    List <ImageTransform <A, ?>> getTransforms () throws ValueError {
        return null;
    }

    /**
     * @param imageTransforms
     */
//    @Override
    public
    void setTransforms ( List <ImageTransform <A, ?>> imageTransforms ) throws ValueError {

    }

    /**
     * @return
     */
//    @Override
    public
    int[] getPixels () {
        return new int[0];
    }

    /**
     * @return
     */
//    @Override
    public
    boolean isGrayScale () {
        return false;
    }

    /**
     * @return
     */
//    @Override
    public
    List <IImage <A>> getComponents () {
        return null;
    }

    /**
     * @return
     */
//    @Override
    public
    List <RegionOfInterest <A>> getRegions () {
        return null;
    }

    /**
     * @param regions
     */
//    @Override
    public
    void setRegions ( List <RegionOfInterest <A>> regions ) {

    }

    public
    void put ( int x, int i ) {

    }

    //        @Override
    public
    IImage <A> getImage () {
        return null;
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
        return null;
    }

    /**
     * @param image
     * @param layerIndex
     * @param clusterIndex
     * @param address
     * @return
     * @throws ValueError
     */
//        @Override
    public
    TreeNode <N, A, G> createChild ( IImage <A> image,
                                     int layerIndex,
                                     int clusterIndex,
                                     IAddress <A> address ) throws ValueError {
        return null;
    }

    @Override
    public
    IIntSize getBoundingBox () {
        return boundingBox;
    }
}
