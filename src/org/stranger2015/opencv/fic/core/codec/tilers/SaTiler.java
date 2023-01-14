package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.List;

public
class SaTiler extends Tiler {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    SaTiler ( IImage image,
              IIntSize rangeSize,
              IIntSize domainSize,
              IEncoder encoder,
              ITreeNodeBuilder <?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    @Override
    public
    ITiler instance () {
        return new SaTiler(
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

    @Override
    public
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentTriangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentPolygon ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {
    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock roi, int blockWidth, int blockHeight ) {
        return List.of();
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
    Pool <IImageBlock> generateInitialRangeBlocks (
            IImageBlock roi,
            int blockWidth,
            int blockHeight ) throws ValueError {

        return List.of();
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;
    }

    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError {

        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }
}
