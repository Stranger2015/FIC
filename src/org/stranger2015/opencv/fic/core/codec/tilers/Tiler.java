package org.stranger2015.opencv.fic.core.codec.tilers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.ESplitKind.VERTICAL;

/*
 * fractal tiler todo
 */

/**
 * @param
 */
public abstract
class Tiler implements ITiler {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    public static final int MAX_DEPTH = 32;

    protected final IImage image;

    protected final Deque <IImageBlock> imageBlockDeque = new ArrayDeque <>();

    public final static IIntSize[] minRangeSizes = {
            new IntSize(4, 4),
            new IntSize(8, 8),
            new IntSize(16, 16),
            };

    public final static IIntSize[] minDomainSizes = {
            new IntSize(8, 8),
            new IntSize(16, 16),
            new IntSize(32, 32),
            };

    public IIntSize rangeSize;
    public IIntSize domainSize;

    protected final IEncoder encoder;

    protected final ITreeNodeBuilder <?> builder;

    protected IImageBlock imageBlock;

    protected final Deque <TreeNodeBase <?>> nodeDeque = new ArrayDeque <>();

    protected ETilerState state;


    protected final List <IImageBlock> rangeBlocks = new ArrayList <>();

    protected final List <IImageBlock> domainBlocks = new ArrayList <>();

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
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
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
//    @Contract(pure = true)
    protected
    ESplitKind chooseDirection ( IImage image ) {
        return VERTICAL;
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getMinRangeSize () {
        return getMinRangeSize(0);
    }

    /**
     * @param i
     * @return
     */
//    @Contract(pure = true)
    private
    IIntSize getMinRangeSize ( int i ) {
        return minRangeSizes[i];
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getMinDomainSize () {
        return getMinDomainSize(0);
    }

    /**
     * @return
     */
//    @Contract(pure = true)
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
//    @Contract(pure = true)
    protected
    IIntSize getMinDomainSize ( int i ) {
        return minDomainSizes[i];
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
    IIntSize getDomainSize () {
        return domainSize;
    }

    /**
     * @return
     */
    @Override
    public final
    IIntSize getRangeSize () {
        return rangeSize;
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
    @Override
    public
    Logger getLogger () {
        return logger;
    }

    /**
     * @return
     */
    public
    List <IImageBlock> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    List <IImageBlock> getDomainBlocks () {
        return domainBlocks;
    }
}