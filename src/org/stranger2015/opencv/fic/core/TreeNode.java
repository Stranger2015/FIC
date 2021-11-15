package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.IDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;
import static org.stranger2015.opencv.fic.core.TreeNode.Type.*;

/**
 * @param <N>
 */
abstract public
class TreeNode<N extends TreeNode <N>> implements IDrawable, Comparable <N> {

    protected static int indexCounter;
    protected short index;

    protected Rect boundingBox;

    /**
     *
     */
    protected final TreeNode <N> parent;

    protected final List <N> children = new ArrayList <>();
    protected Type type;

    protected Direction quadrant;

    private int distance;

    /**
     * @return
     */
    public
    Direction getQuadrant () {
        return quadrant;
    }

    /**
     * @param quadrant
     */
    public
    void setQuadrant ( Direction quadrant ) {
        this.quadrant = quadrant;
    }

    /**
     * @return
     */
    public
    Type getType () {
        return type;
    }

    /**
     * @param type
     */
    public
    void setType ( Type type ) {
        this.type = type;
    }

    /**
     * @param quadrant
     * @param boundingBox
     * @return
     */
    public abstract
    TreeNode <N> createChild ( Direction quadrant, Rect boundingBox );

    /**
     *
     */
    enum Type {
        BLACK,
        GRAY,
        WHITE,
    }

    /**
     * @param quadrant
     * @param node
     */
    void setChild ( Direction quadrant, N node ) {
        if (quadrant.ordinal() < children.size()) {
            children.set(quadrant.ordinal(), node);
        }
        else {
            children.add(quadrant.ordinal(), node);
        }
    }

    /**
     * @param quadrant
     * @return
     */
    N getChild ( Direction quadrant ) {
        return children.get(quadrant.ordinal());
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
    short getIndex () {
        return index;
    }

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
        if (!(o instanceof TreeNode)) {
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
        return format("TreeNode{index=%s}", index);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     */
    public
    TreeNode ( TreeNode <N> parent, Direction quadrant, Rect rect ) {
        this.quadrant = quadrant;

        this.index = (short) indexCounter++;

        this.boundingBox = rect;
        this.parent = parent;

        setDistance(0);
        setType(WHITE);
    }

    /**
     * @return
     */
    public
    List <N> getChildren () {
        return children;
    }

    /**
     * @return
     */
    public
    boolean isLeaf () {
        return getChildren().size() == 0;
    }

    /**
     * @return
     */
    abstract public
    N merge ();

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
    TreeNode <N> getParent () {
        return parent;
    }
}
