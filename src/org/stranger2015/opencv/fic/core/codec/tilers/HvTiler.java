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
 * @param
 
 * @param <G>
 */
public
class HvTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Tiler <N> {

//    private final RectangularTiler <N> rectangularTiler;

    public
    HvTiler ( IImage image, int width, int height ) {
        super(image, width, height);
    }

    @Override
    public
    void addLeaf ( LeafNode <N> node ) {

    }

    @Override
    public
    Logger getLogger () {
        return null;
    }

    @Override
    public
    ITiler <N> instance () {
        return null;
    }

    @Override
    public
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        List.of();
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

    }

    @Override
    public
    void segmentRectangle ( IImageBlock  imageBlock ) throws ValueError {
        return null;
    }


    /**
     * @param image
     * @return
     */
//    @Override
    public
    List <IImageBlock > tile ( IImage image ) throws ValueError, MinimalRangeSizeReached {
    //    return rectangularTiler.tile(image, null, queue);//TODO;
        return null;
    }

    /**
     * @param rows
     * @param cols
     */
    public
    HvTiler ( IImage image, IIntSize rangeSize, IIntSize domainSize, int rows, int cols ) {
        super(image, rangeSize, domainSize, rows, cols);
//        rectangularTiler = new RectangularTiler <>(image, rangeSize, domainSize);
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    HvTiler (IImage  image, IIntSize rangeSize, IIntSize domainSize ) {
        this(image, rangeSize, domainSize, 0, 0);
    }

//    @Override
    public
    void segmentPolygon ( IImageBlock  imageBlock ) throws ValueError {
        return null;
    }

//    @Override
    public
    void segmentQuadrilateral ( IImageBlock  imageBlock ) throws ValueError {
        return null;
    }

    @Override
    public
    void addLeafNode ( TreeNode <N> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock  roi, int blockWidth, int blockHeight ) {
        return null;
    }

    private
    List <IImageBlock > generateInitialRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight )
            throws ValueError {

        return null;
    }

    @Override
    public
    int successorAmount () {
        return 0;
    }
}
