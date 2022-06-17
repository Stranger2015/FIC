package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
@Deprecated
public
class SquareTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer>
        extends RectangularTiler <N, A, G> {

    private RectangularTiler <N, A, G> rectangularTiler;

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param rows
     * @param cols
     */
    public
    SquareTiler ( GrayScaleImage <A> image, IntSize rangeSize, IntSize domainSize, int rows, int cols ) {
        super(image, rangeSize, domainSize, rows, cols);

        rectangularTiler = new RectangularTiler <>(image, rangeSize, domainSize);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    SquareTiler ( GrayScaleImage<A> image, IntSize rangeSize, IntSize domainSize ) {
        super(image, rangeSize, domainSize);
    }
}
