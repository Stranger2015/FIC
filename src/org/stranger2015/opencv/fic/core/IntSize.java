package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;

import static java.lang.String.format;

/**
 *
 */
public
class IntSize implements IIntSize {

    public final int width;
    public final int height;
    private IIntSize originalSize;

    public
    IntSize ( Mat mat) {
        this(mat.width(), mat.height());
    }


    public
    IIntSize getOriginalSize () {
        return originalSize;
    }

    public
    IIntSize getSize () {//todo
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageWidth () {
        return originalImageWidth;
    }

    /**
     * @return
     */
    @Override
    public
    int getOriginalImageHeight () {
        return originalImageHeight;
    }

    /**
     *
     */
    protected final int originalImageWidth;
    protected final int originalImageHeight;

    /**
     * @return
     */
    @Override
    public
    String toString () {
        return width == originalImageWidth && height == originalImageHeight ?
                format("w%d x h%d", width, height) :
                format("w%d x h%d, originally w%d x h%d",
                        width,
                        height,
                        originalImageWidth,
                        originalImageHeight
                );
    }

    /**
     * @param width
     * @param height
     * @param originalImageWidth
     * @param originalImageHeight
     */
    @Contract(pure = true)
    public
    IntSize ( int width, int height, int originalImageWidth, int originalImageHeight ) {
        this.width = width;
        this.height = height;

        this.originalImageWidth = originalImageWidth;
        this.originalImageHeight = originalImageHeight;
    }

    /**
     * @param width
     * @param height
     */
    @Contract(pure = true)
    public
    IntSize ( int width, int height ) {
        this.width = width;
        this.height = height;

        this.originalImageWidth = width;
        this.originalImageHeight = height;
    }

    /**
     * @return
     */
    public
    int getWidth () {
        return width;
    }

    /**
     * @return
     */
    public
    int getHeight () {
        return height;
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
    int compareTo ( @NotNull IIntSize o ) {
        return (width * width + height * height) -
                (o.getWidth() * o.getWidth() + o.getHeight() * o.getHeight());
    }
}
