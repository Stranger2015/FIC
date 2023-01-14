package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.Pool;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class AlterBinTreeImageBlockGenerator<N extends TreeNode <N>>
        extends BinTreeImageBlockGenerator <N> {

    /**
     * @param partitionProcessor
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    AlterBinTreeImageBlockGenerator ( IPartitionProcessor <N> partitionProcessor,
                                      EPartitionScheme scheme,
                                      IEncoder <N> encoder,
                                      IImage image,
                                      IIntSize rangeSize,
                                      IIntSize domainSize ) {

        super(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
    }

    /**
     * @param roi
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    Pool <IImageBlock> generateRangeBlocks ( IImageBlock  roi,
                                                 int rangeSize,
                                                 int domainSize ) throws ValueError {

        return partitionProcessor.generateRangeBlocks(roi, rangeSize, domainSize);
    }
}
