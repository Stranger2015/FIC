package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IImageBlock;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

public
class SaTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends Tiler <N> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    protected
    SaTiler ( IImage image,
              IIntSize rangeSize,
              IIntSize domainSize,
              IEncoder <N> encoder,
              ITreeNodeBuilder <N> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    @Override
    public
    ITiler <N> instance () {
        return new SaTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
    }

    @Override
    public
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

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
    void segmentSquare (TreeNodeBase <N> node,  IImageBlock  imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentTriangle (TreeNodeBase <N> node,  IImageBlock  imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentPolygon (TreeNodeBase <N> node,  IImageBlock  imageBlock ) throws ValueError {
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

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    @Override
    public
    List <IImageBlock > generateInitialRangeBlocks (
            IImageBlock  roi,
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
    List <IImageBlock > generateRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight )
            throws ValueError {

        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }
}
