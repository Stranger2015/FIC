package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IImageBlock;
import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;

import java.util.List;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class DelaunayPartitionProcessor
        extends PartitionProcessor {

    /**
     * @param tiler
     * @param nodeBuilder
     */
    public
    DelaunayPartitionProcessor (
            ITiler tiler,
                                 ImageBlockGenerator <?> imageBlockGenerator,
                                 ITreeNodeBuilder <?> nodeBuilder ) {

        super(tiler, imageBlockGenerator);
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;
    }

    /**
     * @param tiler
     * @param imageBlockGenerator
     * @param nodeBuilder
     * @return
     */
    @Override
    public
    IPartitionProcessor instance ( ITiler tiler,
                                   ImageBlockGenerator <?> imageBlockGenerator,
                                   ITreeNodeBuilder <?> nodeBuilder ) {

        return new DelaunayPartitionProcessor(tiler, imageBlockGenerator, nodeBuilder);
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
    List <IImageBlock> generateRangeBlocks ( IImageBlock roi, int blockWidth, int blockHeight )
            throws ValueError {
        return super.generateRangeBlocks(roi, blockWidth, blockHeight);
    }

    /**
     * @param rangeBlock
     * @param blockWidth
     * @param blockHeight
     * @return
     * @throws ValueError
     */
    @Override
    public
    Pool <IImageBlock> generateDomainBlocks (
            IImageBlock rangeBlock,
            int blockWidth,
            int blockHeight ) throws ValueError {

        return super.generateDomainBlocks(rangeBlock, blockWidth, blockHeight);
    }
}
