package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.ESplitKind.VERTICAL;

/**
 * fractal tiler
 */
public abstract
class Tiler implements ITiler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @return
     */
    @Override
    public
    Logger getLogger () {
        return logger;
    }


    public static final int MAX_DEPTH = 32;

    protected final IImage image;
    protected final Deque <IImageBlock> imageBlockDeque = new ArrayDeque <>();

    public IIntSize currentRangeSize;

    public IIntSize currentDomainSize;

    protected final IEncoder encoder;

    protected final ITreeNodeBuilder <?> builder;

    protected IImageBlock imageBlock;

    protected final Deque <TreeNodeBase <?>> nodeDeque = new ArrayDeque <>();

    protected ETilerState state;

    protected Pool <IImageBlock> rangeBlocks; //new Pool <>(minRangeSize);

    protected Pool <IImageBlock> domainBlocks; //new Pool <>(minDomainSize);

    protected final List <LeafNode <?>> leaves = new ArrayList <>();

    protected final List <TreeNodeBase <?>> nodes = new ArrayList <>();

    protected int depth;

    /**
     * @param imageBlock
     */
    @Override
    public
    void addRangeBlock ( IImageBlock imageBlock ) {
        rangeBlocks.add(imageBlock);
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
     *
     */
    @Override
    public
    void incrementAndCheckDepth () throws DepthLimitExceeded {
        if (++depth == getMaxDepth()) {
            throw new DepthLimitExceeded();
        }
    }

    /**
     * @return
     */
    @Override
    public
    int getMaxDepth () {
        return MAX_DEPTH;
    }

    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    protected
    Tiler ( IImage image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder encoder,
            ITreeNodeBuilder <?> builder ) {

        this.image = image;
        this.currentRangeSize = rangeSize;
        this.currentDomainSize = domainSize;
        this.encoder = encoder;
        this.builder = builder;
    }

    /**
     * @return
     */
    public
    IImage getImage () {
        return image;
    }

    /**
     * @return
     */
    public abstract
    ITiler instance ();

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    public abstract
    void segmentGeometry ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param node
     */
    @Override
    public
    void addNode ( TreeNodeBase <?> node ) {
        nodes.add(node);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public abstract
    void segmentRectangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError;

    /**
     * @param image
     * @return
     */
    @Contract(pure = true)
    protected
    ESplitKind chooseDirection ( IImage image ) {
        return VERTICAL;
    }

    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight ) throws ValueError {
        return ITiler.super.generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param i
     * @return
     */

    public
    IIntSize getMinRangeSize () {
        return minRangeSize;
    }

    /**
     * @return
     */
    @Contract(pure = true)
    @Override
    public final
    Deque <IImageBlock> getImageBlockDeque () {
        return imageBlockDeque;
    }

    /**
     * @return
     */
    @Override
    public final
    Deque <TreeNodeBase <?>> getNodeDeque () {
        return nodeDeque;
    }

    /**
     * @return
     */
    @Override
    public final
    ETilerState getState () {
        return state;
    }

    /**
     * @param state
     */
    @Override
    public
    void setState ( ETilerState state ) {
        this.state = state;
    }

    @Override
    public
    void onSuccessors ( TreeNodeBase <?> node, IImageBlock imageBlock ) {
    }

    @Override
    public
    void onSuccessor ( TreeNodeBase <?> node, IImageBlock imageBlock ) {
    }

    /**
     *
     */
    @Override
    public
    void onFinish () {
        ITiler.super.onFinish();
    }

    /**
     * @param i
     * @return
     */
    @Contract(pure = true)
    public
    IIntSize getMinDomainSize () {
        return minDomainSize;
    }

    /**
     * @return
     */
    public
    IEncoder getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public
    ITreeNodeBuilder <?> getBuilder () {
        return builder;
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    /**
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

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <?> node ) {
        leaves.add(node);
    }

    /**
     * @return
     */
    @Override
    public final
    IIntSize getCurrentDomainSize () {
        return currentDomainSize;
    }

    /**
     * @return
     */
    @Override
    public final
    IIntSize getCurrentRangeSize () {
        return currentRangeSize;
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
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 0;
    }

    /**
     * @param block
     * @return
     */
    @Override
    public
    ClassificationScheme createQuadrants ( ImageBlockInfo blockInfo, IImageBlock imageBlock ) throws ValueError {
        return new ClassificationScheme(blockInfo, imageBlock);
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
    Pool <IImageBlock> generateInitialRangeBlocks (
            IImageBlock roi,
            int blockWidth,
            int blockHeight ) throws ValueError, IOException {

        Pool <IImageBlock> rangeBlocks = new Pool <>(minRangeSizes);

        int w = roi.getSize().getWidth();
        int h = roi.getSize().getHeight();

        int numBlocksPerWidth = w / blockWidth + 1;
        int numBlocksPerHeight = h / blockHeight + 1;
        int level1 = 1;
        int channels = roi.getChannelsAmount();
        for (int ch = 0; ch < channels; ch++) {
            for (int i = 0, x = 0; i < numBlocksPerWidth; i++, x += blockWidth) {
                for (int j = 0, y = 0; j < numBlocksPerHeight; j++, y += blockHeight) {
                    IImageBlock block = roi.getSubImage(x, y, blockWidth, blockHeight);
                    ClassificationScheme quadrants = new ClassificationScheme(new ImageBlockInfo(colorType, block), block);

                    rangeBlocks.add(block);
                }
            }
        }

        return rangeBlocks;
    }

//    public
//    void iteratePixels ( IImage image, Consumer <double[]> action ) {
//        for (int i = 0; i < image.getWidth(); i++) {
//            for (int j = 0; j < image.getHeight(); j++) {
//
//            }
//        }
//    }
//

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( TreeNodeBase <?> node, IImageBlock imageBlock ) throws ValueError {
    }

    /**
     * @return
     */
    public
    Pool <IImageBlock> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    Pool <IImageBlock> getDomainBlocks () {
        return domainBlocks;
    }
}