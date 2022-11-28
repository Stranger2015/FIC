package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class ImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer> {

    protected IPartitionProcessor <N, A, G> partitionProcessor;

    protected final EPartitionScheme scheme;
    protected final IEncoder <N, A, G> encoder;
    protected final IImage <A> image;

    protected IIntSize rangeSize;
    protected IIntSize domainSize;
    protected IIntSize codebookSize;

    /**
     * @param tiler
     * @param topDownTiler
     * @param bottomUpTiler
     * @param encoder
     * @param rangeSize
     * @param domainSize
     */
    @Contract(pure = true)
    protected
    ImageBlockGenerator (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize
    ) {
        this.partitionProcessor = partitionProcessor;
        this.scheme = scheme;
        this.encoder = encoder;
        this.image = image;
        this.rangeSize = rangeSize;
        this.domainSize = domainSize;
        this.codebookSize = domainSize;
    }

    /**
     * @param partitionProcessor
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    public abstract
    ImageBlockGenerator <N, A, G> newInstance (
            IPartitionProcessor <N, A, G> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N, A, G> encoder,
            IImage <A> image,
            IIntSize rangeSize,
            IIntSize domainSize
    );

    /**
     * @param roi
     * @return
     */
    public
    List <Vertex> generateVerticesSet ( IImageBlock <A> roi, int blockWidth, int blockHeight ) {
        return partitionProcessor.generateVerticesSet(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param rangeSize
     * @param domainSize
     * @return
     */
    public
    List <IImageBlock <A>> generateRangeBlocks ( IImageBlock <A> roi, int rangeSize, int domainSize )
            throws ValueError {

        return partitionProcessor.generateRangeBlocks(roi, rangeSize, domainSize);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    protected
    List <IImageBlock <A>> generateInitialRangeBlocks ( IImageBlock <A> roi, int blockWidth, int blockHeight )
            throws ValueError {

        return partitionProcessor.generateInitialRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param partitionProcessor
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    public
    ImageBlockGenerator <N, A, G> create ( IPartitionProcessor <N, A, G> partitionProcessor,
                                           EPartitionScheme scheme,
                                           IEncoder <N, A, G> encoder,
                                           IImage <A> image,
                                           IIntSize rangeSize,
                                           IIntSize domainSize ) {
        return newInstance(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

    /**
     * @param blocks
     * @param indices
     * @return
     */
    public
    IImageBlock <A> mergeBlocks ( @NotNull IImageBlock <A> roi,
                                  List <IImageBlock <A>> blocks,
                                  List <IImageBlock <A>> blocksToMerge ) {

        return (IImageBlock <A>) roi.merge(blocks, blocksToMerge);
    }

    /**
     * @param roi
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    public
    List <IImageBlock <A>> generateDomainBlocks ( IImageBlock <A> roi, int blockWidth, int blockHeight)
            throws ValueError {

        return partitionProcessor.generateDomainBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param roi
     * @param domainBlocks
     * @return
     */
    public
    List <IImageBlock <A>> createCodebookBlocks (IImageBlock <A> roi,
                                                 List <IImageBlock <A>> domainBlocks ) throws ValueError {
        return domainBlocks;
    }
}
