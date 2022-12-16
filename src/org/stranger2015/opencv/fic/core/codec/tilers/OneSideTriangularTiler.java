package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
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
class OneSideTriangularTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends TriangularTiler <N> {
    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    OneSideTriangularTiler ( IImage image,
                             IIntSize rangeSize,
                             IIntSize domainSize,
                             IEncoder <N> encoder,
                             ITreeNodeBuilder <N> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N> instance () {
        return new OneSideTriangularTiler<>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
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
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        List.of(imageBlock);
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
    void segmentPolygon ( TreeNodeBase<N,A,G> node,IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <N> node,IImageBlock  imageBlock ) throws ValueError {
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
    List <IImageBlock > generateInitialRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight ) throws ValueError {
        return List.of(roi.getSubImage());
    }

    @Override
    public
    int successorAmount () {
        return 0;
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
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight ) throws ValueError {
        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }
}
