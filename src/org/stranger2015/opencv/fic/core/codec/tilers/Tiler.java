package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.codec.ESplitKind.VERTICAL;

/*
 * fractal tiler todo
 */

/**
 * @param <A>
 */
public abstract
class Tiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements ITiler <N, A, G> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    public static final int MAX_DEPTH = 32;

    protected final IImage <A> image;

    protected final Deque <IImageBlock <A>> imageBlockDeque = new ArrayDeque <>();

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

    protected final IEncoder <N, A, G> encoder;

    protected final ITreeNodeBuilder <N, A, G> builder;

    protected IImageBlock <A> imageBlock;

    protected final Deque <TreeNodeBase <N, A, G>> nodeDeque = new ArrayDeque <>();

    protected ETilerState state;


    protected final List <IImageBlock <A>> rangeBlocks = new ArrayList <>();

    protected final List <IImageBlock <A>> domainBlocks = new ArrayList <>();


    protected final List <LeafNode <N, A, G>> leaves = new ArrayList <>();

    protected final List <TreeNodeBase <N, A, G>> nodes = new ArrayList <>();

    protected int depth;

    /**
     * @param imageBlock
     */
    @Override
    public
    void addRangeBlock ( IImageBlock <A> imageBlock ) {
        rangeBlocks.add(imageBlock);
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeaf ( LeafNode <N, A, G> node ) {
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
    Tiler ( IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) {

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
    IImage <A> getImage () {
        return image;
    }

    /**
     * @return
     */
    public abstract
    ITiler <N, A, G> instance ();

    /**
     * @param imageBlockShape
     * @param node
     * @param imageBlock
     * @throws ValueError
     */
    public abstract
    void segmentGeometry ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param node
     */
    @Override
    public
    void addNode ( TreeNodeBase <N, A, G> node ) {
        nodes.add(node);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public abstract
    void segmentRectangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError;

    /**
     * @param image
     * @return
     */
    @Contract(pure = true)
    protected
    ESplitKind chooseDirection ( IImage <A> image ) {
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
    @Contract(pure = true)
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
    @Contract(pure = true)
    @Override
    public final
    Deque <IImageBlock <A>> getImageBlockDeque () {
        return imageBlockDeque;
    }

    /**
     * @return
     */
    @Override
    public final
    Deque <TreeNodeBase <N, A, G>> getNodeDeque () {
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
    void onSuccessors ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
    }

    @Override
    public
    void onSuccessor ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) {
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
    protected
    IIntSize getMinDomainSize ( int i ) {
        return minDomainSizes[i];
    }

    /**
     * @return
     */
    public
    IEncoder <N, A, G> getEncoder () {
        return encoder;
    }

    /**
     * @return
     */
    @Override
    public
    ITreeNodeBuilder <N, A, G> getBuilder () {
        return builder;
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentPolygon ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentQuadrilateral ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N, A, G> node ) {
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
    List <Vertex> generateVerticesSet ( IImageBlock <A> roi, int blockWidth, int blockHeight ) {
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
    void segmentSquare ( TreeNodeBase <N, A, G> node, IImageBlock <A> imageBlock ) throws ValueError {
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
    List <IImageBlock <A>> getRangeBlocks () {
        return rangeBlocks;
    }

    /**
     * @return
     */
    List <IImageBlock <A>> getDomainBlocks () {
        return domainBlocks;
    }
}