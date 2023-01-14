package org.stranger2015.opencv.fic.core.codec.tilers;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class SipTiler extends SaTiler {

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
            IEncoder encoder,
            ITreeNodeBuilder <?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @param imageBlock
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) {
        throw new UnsupportedOperationException("SipTiler#segmentRectangle()");
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
        if (imageBlock.getSize().getWidth() <= 1 || imageBlock.getSize().getWidth() != 3) {
            throw new ValueError("SIP Image block size must be equal to 3 x 3");
        }

        super.segmentSquare(node, imageBlock);

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
    void segmentPolygon ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {

    }

    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {

    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     */
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
    Pool <IImageBlock> generateInitialRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError {

        return List.of();
    }

    /**
     * @return
     */
    @Override
    public
    ITiler instance () {
        return new SipTiler(
                getImage(),
                getCurrentRangeSize(),
                this.getCurrentDomainSize(),
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
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
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
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock imageBlock ) {
    }
}