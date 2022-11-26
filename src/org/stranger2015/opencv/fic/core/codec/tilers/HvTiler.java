package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 
 * @param <G>
 */
public
class HvTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tiler <N, A, G> {

//    private final RectangularTiler <N, A, G> rectangularTiler;

    public
    HvTiler ( IImage <A> image, int width, int height ) {
        super(image, width, height);
    }

    @Override
    public
    void addLeaf ( LeafNode <N, A, G> node ) {

    }

    @Override
    public
    Logger getLogger () {
        return null;
    }

    @Override
    public
    ITiler <N, A, G> instance () {
        return null;
    }

    @Override
    public
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        List.of();
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {

    }

    @Override
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
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
    void segmentPolygon ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

//    @Override
    public
    void segmentQuadrilateral ( IImageBlock <A> imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    void addLeafNode ( TreeNode <N, A, G> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock <A> roi, int blockWidth, int blockHeight ) {
        return null;
    }

    private
    List <IImageBlock <A>> generateInitialRangeBlocks ( IImageBlock <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        return null;
    }

    @Override
    public
    int successorAmount () {
        return 0;
    }
}
