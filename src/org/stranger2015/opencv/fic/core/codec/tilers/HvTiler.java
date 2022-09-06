package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;
import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class HvTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>

        extends RectangularTiler <N, A, G> {

//    private final RectangularTiler <N, A, G> rectangularTiler;

    public
    HvTiler ( IImage <A> image, int width, int height ) {
        super(image, width, height);
    }

    @Override
    public
    List <IImageBlock <A>> segmentGeometry ( IImageBlock <A> imageBlock, IIntSize minRangeSize, Deque <IImageBlock <A>> queue ) throws ValueError {
        return super.segmentGeometry(imageBlock, minRangeSize, queue);
    }


    /**
     * @param image
     * @return
     */
//    @Override
    public
    List <IImageBlock <A>> tile ( IImage <A> image ) throws ValueError, MinimalRangeSizeReached {
    //    return rectangularTiler.tile(image, null, queue);//TODO;
        return null;
    }

    /**
     * @param rows
     * @param cols
     */
    public
    HvTiler ( IImage <A> image, IIntSize rangeSize, IIntSize domainSize, int rows, int cols ) {
        super(image, rangeSize, domainSize, rows, cols);
//        rectangularTiler = new RectangularTiler <>(image, rangeSize, domainSize);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    HvTiler (IImage <A> image, IIntSize rangeSize, IIntSize domainSize ) {
        this(image, rangeSize, domainSize, 0, 0);
    }

//    @Override
    public
    List <IImageBlock <A>> segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

//    @Override
    public
    List <IImageBlock <A>> segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }
}
