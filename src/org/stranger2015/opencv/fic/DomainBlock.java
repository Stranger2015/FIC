package org.stranger2015.opencv.fic;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;

/**
 * 8 X 8
 */
public
class DomainBlock<N extends DomainBlock <N, A, M>, A extends Address <A>, M extends Image>
        extends LeafNode <N, A, M>
        implements IImageBlock {

    public final static int W = 8;
    public final static int H = 8;

    /**
     * @param parent
     * @param image
     * @param rect
     */
    public
    DomainBlock ( TreeNode <N, A, M> parent, M image, Rect rect ) throws ValueError {
        super(parent, (ImageBlock) image, rect);
    }

    /**
     * @param imageBlock
     * @param address      * @param layerno
     * @return
     * @throws ValueError
     */
    @Override
    public
    TreeNode <N, A, M> createChild ( int layerIndex, int imageBlock, int address ) throws ValueError {
        return new DomainBlock <>(null, null);//fixme
    }

    /**
     * @param parent
     * @param boundingBox
     * @throws ValueError
     */
    public
    DomainBlock ( TreeNode <N, A, M> parent, Rect boundingBox ) throws ValueError {
        super(parent, null, boundingBox);
    }

    /**
     * @return
     */
    @Override
    public
    Size getSize () {
        return new Size();
    }//fixme

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
