package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.io.IOException;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.ETilerState.*;

/**
 * Tiler & ImageBlkGenerator
 *
 * @param
 */
public
interface ITiler {

    IIntSize minRangeSize = new IntSize(4, 4);

    IIntSize[] minRangeSizes = {
            new IntSize(4, 4),
            new IntSize(8, 8),
            new IntSize(16, 16),
            };

    IIntSize minDomainSize = new IntSize(8, 8);

    IIntSize[] minDomainSizes = {
            new IntSize(8, 8),
            new IntSize(16, 16),
            new IntSize(32, 32),
            };


    /**
     * @param <N>
     * @param
     * @param <G>
     * @return
     */
    static
    IPartitionProcessor create ( IEncoder encoder, ITiler tiler ) {
        return encoder.createPartitionProcessor(tiler);
    }

    /**
     * @return
     */
    default
    void tile ( IImageBlock imageBlock ) throws Throwable {
        Tree <?> tree = getBuilder().buildTree(imageBlock);
        getNodeDeque().push(tree.getRoot());
        getImageBlockDeque().push(imageBlock);

        switch (compareRangeSize(imageBlock.getSize(), getMinRangeSize())) {
            case 0:
                setState(TILE_LEAF);
                break;
            case 1:
                setState(TILE_SUCCESSORS);
                break;
            default:
                setState(TILE_FINISH);
                throw new IllegalStateException("Unexpected value: -1");
        }
        while (true) {
            TreeNodeBase <?> node = getNodeDeque().pop();
            imageBlock = getImageBlockDeque().pop();
            switch (getState()) {
                case TILE_SUCCESSORS:
                    onSuccessors(node, imageBlock);
                    break;
                case TILE_SUCCESSOR:
                    onSuccessor(node, imageBlock);
                    break;
                case TILE_LEAF:
                    onLeaf((LeafNode <?>) node, imageBlock);
                    break;
                case TILE_FINISH:
                    onFinish();
                    return;
                default:
                    throw new IllegalStateException("Unexpected value: " + getState());
            }
        }
    }

    /**
     * @return
     */
    IIntSize getMinRangeSize ();

    /**
     * @return
     */
    IIntSize getMinDomainSize ();

    /**
     * @return
     */
    Deque <IImageBlock> getImageBlockDeque ();

    /**
     * @return
     */
    Deque <TreeNodeBase <?>> getNodeDeque ();

    /**
     * @param node
     */
    default
    void onLeaf ( LeafNode <?> node, IImageBlock imageBlock ) throws ValueError {
        node.setImageBlock(imageBlock);
        addRangeBlock(imageBlock);
        addLeaf(node);
        addNode(node);//fixme????

        setState(TILE_SUCCESSORS);
    }

    /**
     * @param imageBlock
     */
    void addRangeBlock ( IImageBlock imageBlock );

    /**
     * @param node
     */
    void addLeaf ( LeafNode <?> node );

    /**
     * @return
     */
    ETilerState getState ();

    /**
     * @param operation
     */
    void setState ( ETilerState state );

    /**
     *
     */
    default
    void onSuccessors ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
        List <TreeNodeBase <?>> successors = getBuilder().getSuccessors();
        segmentGeometry(node, imageBlock);
        for (int succIndex = 0; succIndex < successorAmount(); succIndex++) {
            node = successors.get(succIndex);
            getBuilder().add(node);
            if (node.isLeaf()) {
                getBuilder().addLeafNode((LeafNode <?>) node);
                setState(TILE_LEAF);
            }
            else {
//                setQuadrant(node.getQuadrant());//todo nextQuadrant()
                setState(TILE_SUCCESSOR);
            }
        }
    }

    /**
     *
     */
    default
    void onSuccessor ( TreeNodeBase <?> node, IImageBlock imageBlock ) {
        try {
            incrementAndCheckDepth();
        } catch (DepthLimitExceeded e) {
            setState(TILE_FINISH);
        }
        getImageBlockDeque().push(imageBlock);
        getNodeDeque().push(node);

        addNode(node);
        setState(TILE_SUCCESSORS);
    }

    /**
     *
     */
    void incrementAndCheckDepth () throws DepthLimitExceeded;

    /**
     * @return
     */
    int getMaxDepth ();

    /**
     * @param rangeSize
     * @param minRangeSize
     * @return
     */
    default
    int compareRangeSize ( IIntSize rangeSize, IIntSize minRangeSize ) {
        return rangeSize.compareTo(minRangeSize);
    }

    /**
     * @param node
     */
    void addNode ( TreeNodeBase <?> node );

    /**
     *
     */
    default
    void onFinish () {
        getLogger().info("On finishing tile() ...");
    }

    /**
     * @return
     */
    Logger getLogger ();

    /**
     * @return
     */
    ITreeNodeBuilder <?> getBuilder ();

    /**
     * @return
     */
    IIntSize getCurrentRangeSize ();

    /**
     * @return +
     */
    IIntSize getCurrentDomainSize ();

//=============== segment

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     */
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentTriangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentPolygon ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentQuadrilateral ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param node
     */
    void addLeafNode ( LeafNode <?> node );

    /**
     * @param roi
     * @return
     */
    List <Vertex> generateVerticesSet ( IImageBlock roi, int blockWidth, int blockHeight );

    /**
     * @return
     */
    int successorAmount ();

    ClassificationScheme createQuadrants ( ImageBlockInfo blockInfo, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    default
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError, IOException {

        return generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param block
     * @return
     * @throws ValueError
     */
    ClassificationScheme createQuadrants ( ImageBlockInfo block ) throws ValueError;

    /**
     * @param rangeBlock
     * @param blockWidth
     * @param blockHeight
     * @param colorType
     * @return
     * @throws ValueError
     */
    default
    Pool <IImageBlock> generateDomainBlocks ( IImageBlock rangeBlock, int blockWidth, int blockHeight, IImage.EColorType colorType )
            throws ValueError, IOException {

        int numOfBlocksPerRow = rangeBlock.getWidth() - blockWidth + 1;
        int numOfBlocksPerCol = rangeBlock.getHeight() - blockHeight + 1;

        final Pool <IImageBlock> domainBlocks = new Pool <>(minDomainSizes);
        int channels = rangeBlock.getChannelsAmount();
        for (int channelIndex = 0; channelIndex < channels; channelIndex++) {
            double sumOfPixelValues = 0;
            for (int i = 0, x = 0; i < numOfBlocksPerRow; i++, x += blockWidth) {
                for (int j = 0, y = 0; j < numOfBlocksPerCol; j++, y += blockHeight) {
                    IImageBlock block = rangeBlock.getSubImage(x, y, blockWidth, blockHeight);
                    ClassificationScheme scheme = new ClassificationScheme(new ImageBlockInfo(colorType, block), block);

                    sumOfPixelValues = 0;
                    sumOfPixelValues += rangeBlock.pixelValues(i + x, j + y);//fixme
                    block.setMeanPixelValues(sumOfPixelValues / (double) (blockWidth * blockHeight));
                    block.setBeta(0);
                    block.setBeta(block.getBeta() + Math.pow(block.pixelValues(x, y) - block.getMeanPixelValue(), 2));
                    block.resize(2);
                    domainBlocks.add(block);
                }
            }
        }

        return domainBlocks;
    }

    Pool <IImageBlock> generateInitialRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError, IOException;
}
