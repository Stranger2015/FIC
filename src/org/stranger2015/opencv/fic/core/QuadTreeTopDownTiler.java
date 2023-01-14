package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.tilers.ClassificationScheme;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ITopDownTiler;
import org.stranger2015.opencv.fic.core.codec.tilers.ImageBlockInfo;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class QuadTreeTopDownTiler extends QuadTreeTiler implements ITopDownTiler {

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    QuadTreeTopDownTiler ( IImage image,
                           IIntSize rangeSize,
                           IIntSize domainSize,
                           IEncoder encoder,
                           ITreeNodeBuilder <?> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler instance () {
        return new QuadTreeTopDownTiler (
                getImage(),
                this.getCurrentRangeSize(),
                this.getCurrentDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeaf ( LeafNode <?> node ) {
        leaves.add(node);
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessors ( TreeNodeBase <?> node, IImageBlock  imageBlock ) {
    }

    /**
     * @param node
     * @param imageBlock
     */
    @Override
    public
    void onSuccessor ( TreeNodeBase <?> node, IImageBlock  imageBlock ) {
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        segmentPolygon(node, imageBlock);
    }

    /**
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock  imageBlock )
            throws ValueError {
    }

    /**
     * QUADRANTS *********************************************
     * <p>
     * 0  |  1
     * 2  |  3
     * OR
     * 0  |  1
     * 3  |  2
     *
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        int x = imageBlock.getX();
        int y = imageBlock.getY();

        int w = imageBlock.getWidth() / 2;

        IImageBlock  r1 = imageBlock.getSubImage(x, y, w, w);
        IImageBlock  r2 = imageBlock.getSubImage(x + w, y, w, w);
        IImageBlock  r3 = imageBlock.getSubImage(x, y + w, w, w);
        IImageBlock  r4 = imageBlock.getSubImage(x + w, y + w, w, w);

        getImageBlockDeque().push(r1);
        getImageBlockDeque().push(r2);
        getImageBlockDeque().push(r3);
        getImageBlockDeque().push(r4);

        getNodeDeque().push(node.createNode(node, r1.getAddress(r1.getX(), r1.getY())));
        getNodeDeque().push(node.createNode(node, r2.getAddress(r2.getX(), r2.getY())));
        getNodeDeque().push(node.createNode(node, r3.getAddress(r3.getX(), r3.getY())));
        getNodeDeque().push(node.createNode(node, r4.getAddress(r4.getX(), r4.getY())));
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
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
        segmentSquare(node, imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <?> node, IImageBlock  imageBlock ) throws ValueError {
    }

    /**
     * @return
     */
    @Override
    public
    String toString () {
        return "QuadTreeTopDownTiler{" + "imageBlock=" + imageBlock + '}';
    }
}
