package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class TriangularTopDownTiler
        extends TriangularTiler
        implements ITopDownTiler {
    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    TriangularTopDownTiler ( IImage image,
                             IIntSize rangeSize,
                             IIntSize domainSize,
                             IEncoder encoder,
                             ITreeNodeBuilder<?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon (TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        List.of(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        List.of(imageBlock);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock  roi, int blockWidth, int blockHeight ) {
        return List.of();
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    public
    Pool <IImageBlock> generateInitialRangeBlocks ( IImageBlock  roi,
                                                     int blockWidth,
                                                     int blockHeight ) throws ValueError {

        return List.of(roi.getSubImage(roi.getX(), roi.getY(), blockWidth, blockHeight));
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;
    }//fixme

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight )
            throws ValueError {

        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler instance () {
        return new TriangularTopDownTiler(
                getImage(),
                getCurrentRangeSize(),
                this.getCurrentDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {

    }
}
