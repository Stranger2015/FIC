package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.stranger2015.opencv.fic.core.ETilerOperation.*;
import static org.stranger2015.opencv.fic.core.IShape.EShape;
import static org.stranger2015.opencv.fic.core.IShape.EShape.*;
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

    protected IShape[] r;

    protected final IImage <A> image;

//    private final int width;
//    private final int height;

    public final static IntSize[] minRangeSizes = {
            new IntSize(4, 4),
            new IntSize(8, 8),
            new IntSize(16, 16),
            };

    public final static IntSize[] minDomainSizes = {
            new IntSize(8, 8),
            new IntSize(16, 16),
            new IntSize(32, 32),
            };

    public IIntSize rangeSize;
    public IIntSize domainSize;

    private final IEncoder <N, A, G> encoder;
    private final ITreeNodeBuilder <N, A, G> builder;

    protected List <TreeNodeBase <N, A, G>> successors;

    protected int succIndex;

    protected IImageBlock <A> imageBlock;

    protected final Deque <IImageBlock <A>> queue = new ArrayDeque <>();

    /**
     *
     */
    @Contract(pure = true)
    protected
    Tiler ( IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) {

        this(image,/* image.getWidth(), image.getHeight(),*/ encoder, builder);

        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
    }

    /**
     * @param image
     */
    public
    Tiler ( IImage <A> image,
//            int width,
//            int height,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) {

        this.image = image;
//        this.width = width;
//        this.height = height;
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
     * @param image
     * @param minRangeSize
     * @param queue
     * @return
     */
    @Override
    public final
    List <IImageBlock <A>> tile ( IImage <A> image,
                                  IIntSize minRangeSize,
                                  Deque <IImageBlock <A>> queue

    ) throws ValueError, MinimalRangeSizeReached {

        IImageBlock <A> imageBlock = image.getSubImage();

        queue.push(imageBlock);
        doTile(minRangeSize, queue);

        return List.of();
    }

    /**
     * @param queue
     */
//    @SuppressWarnings("unchecked")
    protected
    void doTile ( IIntSize minRangeSize, Deque <IImageBlock <A>> queue ) throws ValueError {
        for (ETilerOperation operation = TILE_START; operation != TILE_FINISH; ) {
            pop();//todo here??
            switch (operation) {
                case TILE_START:
                    if (imageBlock.getSize().compareTo(minRangeSize) <= 0) {
                        operation = TILE_FINISH;
                        break;
                    }
                    operation = TILE_SEGMENT_SHAPE;
                    break;
                case TILE_SEGMENT_SHAPE:
                    EShape imageBlockShape;
                    switch (imageBlock.getShape().getShapeKind()) {
                        case SQUARE:
                            imageBlockShape = SQUARE;
                            break;
                        case RECTANGLE:
                            imageBlockShape = RECTANGLE;
                            break;
                        case TRIANGLE:
                            imageBlockShape = TRIANGLE;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + imageBlock.getShape());
                    }

                    segmentShape(imageBlockShape, imageBlock, minRangeSize, queue);
                    operation = TILE_SUCCESSORS;
                    break;
                case TILE_SUCCESSORS:
                    successors = builder.getSuccessors();
                    TreeNodeBase <N, A, G> node = successors.get(succIndex);
                    builder.add(node);

                    if (node.isLeaf()) {
                        builder.addLeafNode((LeafNode <N, A, G>) node);
//                        imageBlock = ((ILeaf <N, A, G>) node).getImageBlock();//todo push??
                        operation = TILE_LEAF;
                    }
                    else {
                        EDirection q = node.getQuadrant();
                        operation = TILE_SUCCESSOR;
                    }
                    break;
                case TILE_SUCCESSOR:
                    push(imageBlock);
                    break;
                case TILE_LEAF:
                    imageBlock = pop();//fixme
                    break;
                case TILE_FINISH:
                    onFinish();
                    continue;
                default:
                    throw new IllegalStateException("Unexpected value: " + operation);
            }
        }
    }

    private
    void push ( IImageBlock <A> imageBlock ) {
        succIndex++;
        queue.push(imageBlock);
    }

    private
    IImageBlock <A> pop () {
        succIndex--;
        return queue.pop();
    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @throws ValueError
     */
    public abstract
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock <A>> queue
    ) throws ValueError;

    /**
     *
     */
    protected abstract
    void onFinish ();

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
    IIntSize getRangeSize () {
        return getRangeSize(0);
    }

    private
    IIntSize getRangeSize ( int i ) {
        return minRangeSizes[i];
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getDomainSize () {
        return getDomainSize(0);
    }

    private
    IIntSize getDomainSize ( int i ) {
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
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {

    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentSquare ( IImageBlock <A> imageBlock ) throws ValueError {

    }
}