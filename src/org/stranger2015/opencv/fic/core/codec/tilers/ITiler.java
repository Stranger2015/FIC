package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.ETilerState.*;

/**
 * Tiler & ImageBlkGenerator
 *
 * @param <A>
 */
public
interface ITiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {
    /**
     * @param <N>
     * @param <A>
     * @param <G>
     * @return
     */
    @Contract(pure = true)
    static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    @NotNull IPartitionProcessor <N, A, G> create ( IEncoder <N, A, G> encoder, ITiler <N, A, G> tiler ) {
        @NotNull IPartitionProcessor <N, A, G> partitionProcessor = encoder.createPartitionProcessor(tiler);

        return partitionProcessor;
    }

//    static
//    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
//    ITiler <N, A, G> create ( String encoderClassName) throws ReflectiveOperationException {
//
//        Class <?> clazz = getTilerClass(encoderClassName);
//
//        return (ITiler <N, A, G>) clazz.getConstructor().newInstance();
//    }

//    static
//    Class <?> getTilerClass ( IEncoder<        mapper.put();
//> encoderClassName ) {
//Class<?>.forname(encoderClassName);
//    }

    /**
     * @return
     */
    default
    void tile ( IImageBlock <A> imageBlock ) throws ValueError, DepthLimitExceeded {
        Tree <N, A, G> tree = getBuilder().buildTree(imageBlock);
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
            TreeNodeBase <N, A, G> node = getNodeDeque().pop();
            imageBlock = getImageBlockDeque().pop();
            switch (getState()) {
                case TILE_SUCCESSORS:
                    onSuccessors(node, imageBlock);
                    break;
                case TILE_SUCCESSOR:
                    onSuccessor(node, imageBlock);
                    break;
                case TILE_LEAF:
                    onLeaf((LeafNode <N, A, G>) node, imageBlock);
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
    Deque <IImageBlock <A>> getImageBlockDeque ();

    /**
     * @return
     */
    Deque <TreeNodeBase <N, A, G>> getNodeDeque ();

   /**
     * @param node
     */
    default
    void onLeaf ( LeafNode <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        node.setImageBlock(imageBlock);
        addRangeBlock(imageBlock);
        addLeaf(node);
        addNode(node);//fixme????

        setState(TILE_SUCCESSORS);
    }

    /**
     * @param imageBlock
     */
    void addRangeBlock ( IImageBlock <A> imageBlock );

    /**
     * @param node
     */
    void addLeaf ( LeafNode <N, A, G> node );

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
    void onSuccessors ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
        List <TreeNodeBase <N, A, G>> successors = getBuilder().getSuccessors();
        segmentGeometry(node, imageBlock);
        for (int succIndex = 0; succIndex < successorAmount(); succIndex++) {
            node = successors.get(succIndex);
            getBuilder().add(node);
            if (node.isLeaf()) {
                getBuilder().addLeafNode((LeafNode <N, A, G>) node);
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
    void onSuccessor ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
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
    int compareRangeSize ( @NotNull IIntSize rangeSize, IIntSize minRangeSize ) {
        return rangeSize.compareTo(minRangeSize);
    }

    /**
     * @param node
     */
    void addNode ( TreeNodeBase <N, A, G> node );

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
    ITreeNodeBuilder <N, A, G> getBuilder ();

    /**
     * @return
     */
    IIntSize getRangeSize ();

    /**
     * @return +
     */
    IIntSize getDomainSize ();

//=============== segment

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     */
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentSquare ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentTriangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentPolygon ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param imageBlock
     * @throws ValueError
     */
    void segmentQuadrilateral ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param node
     */
    void addLeafNode ( LeafNode <N, A, G> node );

    /**
     * @param roi
     * @return
     */
    List <Vertex> generateVerticesSet ( IImageBlock <A> roi, int blockWidth, int blockHeight );

    int successorAmount ();

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
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        int numOfBlocksPerRow = roi.getWidth() / blockWidth;
        int numOfBlocksPerCol = roi.getHeight() / blockHeight;

        List <IImageBlock <A>> blocks = new ArrayList <>();
        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                IImageBlock <A> block = new ImageBlock <>(
                        i * blockWidth,
                        j * blockHeight,
                        blockWidth,
                        blockHeight);
                int channels = roi.getChannelsAmount();
                double[] sumOfPixelValues = new double[channels];
                for (int c = 0; c < channels; c++) {
                    sumOfPixelValues[c] = 0;
                    for (int x = 0; x < blockWidth; x++) {
                        for (int y = 0; y < blockHeight; y++) {
                            block.pixelValues(x, y)[c] = roi.pixelValues(
                                    i * blockWidth + x,
                                    j * blockHeight + y)[c];
                            sumOfPixelValues[c] += block.pixelValues(x, y)[c];
                        }
                    }
                    block.setMeanPixelValuesLayer(c, sumOfPixelValues[c] / (double) (blockWidth * blockHeight));
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

    default
    List <IImageBlock <A>> generateDomainBlocks ( IImageBlock <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        int numOfBlocksPerRow = roi.getWidth() - blockWidth + 1;
        int numOfBlocksPerCol = roi.getHeight() - blockHeight + 1;

        final List <IImageBlock <A>> blocks = new ArrayList <>();
        int channels = roi.getChannelsAmount();
        for (int i = 0; i < numOfBlocksPerRow; i++) {
            for (int j = 0; j < numOfBlocksPerCol; j++) {
                IImageBlock <A> block = new ImageBlock <>(i, j, blockWidth, blockHeight);
                double[] sumOfPixelValues = new double[channels];
                for (int c = 0; c < channels; c++) {
                    sumOfPixelValues[c] = 0;
                    for (int x = 0; x < blockWidth; x++) {
                        for (int y = 0; y < blockHeight; y++) {
                            block.pixelValues(x, y)[c] = roi.pixelValues(i + x, j + y)[c];
                            sumOfPixelValues[c] += block.pixelValues(x, y)[c];
                        }
                    }
                    block.setMeanPixelValuesLayer(c, sumOfPixelValues[c] / (double) (blockWidth * blockHeight));
                    block.setBeta(0);
                    for (int x = 0; x < blockWidth; x++) {
                        for (int y = 0; y < blockHeight; y++) {
                            block.setBeta(block.getBeta() +
                                    Math.pow(block.pixelValues(x, y)[c] - block.getMeanPixelValue()[c], 2)
                            );
                        }
                    }
                    block.resize(2);
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

    List <IImageBlock <A>> generateInitialRangeBlocks (IImageBlock <A> roi, int blockWidth, int blockHeight )
            throws ValueError;
}
