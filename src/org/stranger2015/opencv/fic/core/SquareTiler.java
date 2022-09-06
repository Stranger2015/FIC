package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
@Deprecated
public
class SquareTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
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
    SquareTiler ( IImage<A> image,IIntSize rangeSize, IIntSize domainSize, int rows, int cols ) {
        super(image, rangeSize, domainSize, rows, cols);

        rectangularTiler = new RectangularTiler <>(image, rangeSize, domainSize);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    SquareTiler (IImage<A> image, IntSize rangeSize, IntSize domainSize ) {
        super(image, rangeSize, domainSize);
    }

    @Override
    public
    List <IImageBlock <A>> segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {

        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    List <IImageBlock <A>> segmentGeometry ( IImageBlock <A> imageBlock, IIntSize minRangeSize, Deque <IImageBlock <A>> queue ) throws ValueError {
        return null;
    }

    public
    RectangularTiler <N, A, G> getRectangularTiler () {
        return rectangularTiler;
    }
}
