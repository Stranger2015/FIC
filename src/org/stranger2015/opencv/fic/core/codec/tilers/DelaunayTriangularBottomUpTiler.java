package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class DelaunayTriangularBottomUpTiler
        extends TriangularTiler
        implements IBottomUpTiler {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    DelaunayTriangularBottomUpTiler ( IImage image,
                                      IIntSize rangeSize,
                                      IIntSize domainSize,
                                      IEncoder encoder,
                                      ITreeNodeBuilder <?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    @Override
    public
    void segmentTriangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
        super.segmentTriangle(node, imageBlock);
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 0;
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeaf ( LeafNode <?> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addNode ( TreeNodeBase <?> node ) {

    }

    /**
     * @return
     */
    @Override
    public
    Logger getLogger () {
        return logger;
    }

    @Override
    public
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {

    }

    /**
     * @return
     */
    @Override
    public
    ITiler instance () {
        return new DelaunayTriangularBottomUpTiler(
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
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {

    }
}
