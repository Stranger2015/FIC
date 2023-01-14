package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class ThreeSideTriangularTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends TriangularTiler <N> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    ThreeSideTriangularTiler ( IImage image,
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
        return new ThreeSideTriangularTiler<>(
                getImage(),
                getCurrentRangeSize(),
                this.getCurrentDomainSize(),
                getEncoder(),
                getBuilder());
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
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        List.of();
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <N> node,  IImageBlock  imageBlock ) throws ValueError {
        List.of();
    }

    @Override
    public
    List <Vertex> generateVerticesSet ( IImageBlock  roi,
                                   int blockWidth,
                                   int blockHeight ) {
        return new ArrayList <>();
    }

    public
    Pool <IImageBlock> generateInitialRangeBlocks ( IImageBlock  roi,
                                                        int blockWidth,
                                                        int blockHeight ) throws ValueError {
        return List.of();
    }

    @Override
    public
    int successorAmount () {
        return 1;
    }

    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock  roi,
                                                 int blockWidth,
                                                 int blockHeight ) throws ValueError {
        return List.of();
    }
}
