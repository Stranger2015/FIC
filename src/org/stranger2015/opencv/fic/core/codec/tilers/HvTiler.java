package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class HvTiler extends Tiler {
    public
    HvTiler ( IImage image, int width, int height ) {
        super(image, width, height);
    }

    @Override
    public
    void addLeaf ( LeafNode <?> node ) {

    }

    @Override
    public
    Logger getLogger () {
        return null;
    }

    @Override
    public
    ITiler instance () {
        return null;
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {

    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {

    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
//    @Override
    public
    void segmentRectangle ( IImageBlock imageBlock ) throws ValueError {
    }


    /**
     * @param image
     * @return
     */
//    @Override
    public
    List <IImageBlock> tile ( IImage image ) throws ValueError, MinimalRangeSizeReached {
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
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    HvTiler ( IImage image, IIntSize rangeSize, IIntSize domainSize ) {
        this(image, rangeSize, domainSize, 0, 0);
    }

    //    @Override
    public
    void segmentPolygon ( IImageBlock imageBlock ) throws ValueError {
    }

    //    @Override
    public
    void segmentQuadrilateral ( IImageBlock imageBlock ) throws ValueError {
    }

    public
    void addLeafNode ( TreeNode <?> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock roi, int blockWidth, int blockHeight ) {
        return null;
    }

    public
    Pool <IImageBlock> generateInitialRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError {

        return null;
    }

    @Override
    public
    int successorAmount () {
        return 0;
    }
}
