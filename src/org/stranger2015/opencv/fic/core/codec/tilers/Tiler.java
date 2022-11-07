package org.stranger2015.opencv.fic.core.codec.tilers;

import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.ESplitKind;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayDeque;
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

    protected final IImage <A> image;
    protected final Deque<A> deque=new ArrayDeque <>();

    public final static IIntSize[] minRangeSizes = {
            new IntSize(16, 16),
            new IntSize(8, 8),
            new IntSize(4, 4),
            };

    public final static IIntSize[] minDomainSizes = {
            new IntSize(32, 32),
            new IntSize(16, 16),
            new IntSize(8, 8),
            };

    public IIntSize rangeSize;
    public IIntSize domainSize;

    protected final IEncoder <N, A, G> encoder;

    protected final ITreeNodeBuilder <N, A, G> builder;
    protected List <TreeNodeBase <N, A, G>> successors;

    protected int succIndex;

    protected IImageBlock <A> imageBlock;

    protected final Deque <IImageBlock <A>> queue = new ArrayDeque <>();

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
     * @param imageBlock
     * @throws ValueError
     */
    public abstract
    void segmentGeometry ( IImageBlock <A> imageBlock ) throws ValueError;

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
     * @param i
     * @return
     */
    @Contract(pure = true)
    private
    IIntSize getMinDomainSize ( int i ) {
        return minDomainSizes[i];
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getRangeSize () {
        return rangeSize;
    }

    /**
     * @return
     */
    @Override
    public
    IIntSize getDomainSize () {
        return domainSize;
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

    /**
     * @return
     */
    @Override
    public
    Logger getLogger () {
        return logger;
    }
}