package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.BinTreeEncoder;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.SplitAndMergeEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.Deque;

import static java.lang.String.format;
import static org.stranger2015.opencv.fic.core.IShape.EShape;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BushTreeTiler<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends CompositeTiler <N, A, G> {

    protected final BinTreeEncoder <N, A, G> binTreeEncoder;
    protected final SplitAndMergeEncoder <N, A, G> splitAndMergeEncoder;

    protected BinTreeTiler <N, A, G> binTreeTiler;
    protected TriangularTiler <N, A, G> triangularTiler;


    /**
     * @param image
     * @param rangeSize
     * @param domainSize
     * @param encoder
     * @param builder
     */
    public
    BushTreeTiler (
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize,
            IEncoder <N, A, G> encoder,
            ITreeNodeBuilder <N, A, G> builder ) {

        super(image, rangeSize, domainSize, encoder, builder);
        binTreeEncoder = null;//new BinTreeEncoder <>(
        splitAndMergeEncoder = new SplitAndMergeEncoder <N, A, G>(
//                scheme,
//        nodeBuilder,
//        searchProcessor,
//        scaleTransform,
//        imageBlockGenerator,
//        comparator,
//        transforms,
//        filters,
//        fractalModel
//        );
//        quadTreeEncoder = null;// new QuadTreeEncoder <N, A, G>();

                binTreeTiler = new BinTreeTiler <>(image, rangeSize, domainSize, binTreeEncoder, builder);
        triangularTiler = new DelaunayTriangularTiler <>(
                image, rangeSize, domainSize, splitAndMergeEncoder, builder);

        tilersPipeline.add(binTreeTiler);
        tilersPipeline.add(triangularTiler);
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> instance () {
        return new BushTreeTiler <>(
                getImage(),
                getRangeSize(),
                getDomainSize(),
                getEncoder(),
                getBuilder()
        );
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return super.successorAmount();
    }

    /**
     * @param imageBlockShape
     * @param imageBlock
     * @param minRangeSize
     * @param queue
     * @throws ValueError
     */
    @Override
    public
    void segmentShape ( EShape imageBlockShape,
                        IImageBlock <A> imageBlock,
                        IIntSize minRangeSize,
                        Deque <IImageBlock <A>> queue ) throws ValueError {

        super.segmentShape(imageBlockShape, imageBlock, minRangeSize, queue);

        for (ITiler <N, A, G> tiler : tilersPipeline) {
            tiler.segmentShape(imageBlockShape, imageBlock, minRangeSize, queue);
        }
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentRectangle ( IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentRectangle(imageBlock);
    }

    /**
     * @param imageBlock
     * @throws ValueError
     */
    @Override
    public
    void segmentTriangle ( IImageBlock <A> imageBlock ) throws ValueError {
        super.segmentTriangle(imageBlock);
    }

    /**
     * @return
     */
    @Override
    public
    String toString () {
        return format("BushTreeTiler{binTreeTiler=%s}", binTreeTiler);
    }
}
