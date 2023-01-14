package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class NoneTiler extends Tiler {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    NoneTiler ( IImage image,
                IIntSize rangeSize,
                IIntSize domainSize,
                IEncoder encoder,
                ITreeNodeBuilder<?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    @Override
    public
    ITiler instance () {
        return new NoneTiler (
                getImage(),
                this.getCurrentRangeSize(),
                this.getCurrentDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon (TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
    }

    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock  roi, int blockWidth, int blockHeight ) {
        return List.of();
    }

    public
    Pool <IImageBlock> generateInitialRangeBlocks ( @NotNull IImageBlock  roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {

        return super.generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */

    @Override
    public
    int successorAmount () {
        return 0;
    }

    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock  roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {

        return super.generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param block
     * @return
     * @throws ValueError
     */
    @Override
    public
    ClassificationScheme createQuadrants ( ImageBlockInfo block ) throws ValueError {
        return null;
    }

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock  imageBlock
    ) throws ValueError {
        logger.info("Segmenting geometry ...");
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

    /**
     * @param imageBlock
     * @throws ValueError
     */
    public
    void segmentRectangle ( IImageBlock  imageBlock ) throws ValueError {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeaf ( LeafNode <?> node ) {

    }

    /**
     *
     */
    @Override
    public
    void onFinish () {
        super.onFinish();
    }

}
