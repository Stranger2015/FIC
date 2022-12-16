package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
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
class SipTiler<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends SaTiler <N> {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    SipTiler (
            IImage image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N> encoder,
            ITreeNodeBuilder <N> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <N> node, IImageBlock  imageBlock ) {
        throw new UnsupportedOperationException("SipTiler#segmentRectangle()");
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {
        if (imageBlock.getSize().getWidth() <= 1 || imageBlock.getSize().getWidth() != 3) {
            throw new ValueError("SIP Image block size must be equal to 3 x 3");
        }

        super.segmentSquare(node,imageBlock);

        ESplitKind dir = chooseDirection(imageBlock);
        int sideSize = imageBlock.getWidth();

//        if (sideSize == imageBlock.getWidth() / 2) {
//            r[0] = new Square(0, 0, sideSize);
//            r[1] = new Square(sideSize, 0, sideSize);
//        }
//        else {
//            sideSize = imageBlock.getHeight() / 2;
//            r[0] = new Square(0, 0, sideSize);
//            r[1] = new Square(0, sideSize, sideSize);
//        }

//        return result;
    }

    @Override
    public
    void segmentPolygon ( TreeNodeBase <N> node, IImageBlock  imageBlock ) throws ValueError {

    }

    @Override
    public
    void segmentQuadrilateral (TreeNodeBase <N> node,  IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N> node ) {

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
    @Override
    public
    List <IImageBlock > generateInitialRangeBlocks ( IImageBlock  roi, int blockWidth, int blockHeight )
            throws ValueError {

        return List.of();
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N> instance () {
        return new SipTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder());
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

        return List.of();
    }

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <N> node, IImageBlock  imageBlock ) {
    }
}