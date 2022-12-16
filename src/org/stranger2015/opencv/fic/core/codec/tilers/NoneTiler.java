package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
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
class NoneTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Tiler<N> {

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
                IEncoder <N> encoder,
                ITreeNodeBuilder<N> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    @Override
    public
    ITiler <N> instance () {
        return new NoneTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
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
    void segmentPolygon (TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
    }

    @Override
    public
    void addLeafNode ( LeafNode <N> node ) {

    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock  roi, int blockWidth, int blockHeight ) {
        return List.of();
    }

    public
    List <IImageBlock > generateInitialRangeBlocks ( @NotNull IImageBlock  roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {
        return List.of(roi.getSubImage());
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
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {
        return super.generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock
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
    void segmentRectangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

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
    void addLeaf ( LeafNode <N> node ) {

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
