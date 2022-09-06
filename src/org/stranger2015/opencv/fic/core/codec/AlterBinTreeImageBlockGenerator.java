package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class AlterBinTreeImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeImageBlockGenerator <N, A, G> {

    /**
     * @param partitionProcessor
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    AlterBinTreeImageBlockGenerator ( IPartitionProcessor <N, A, G> partitionProcessor,
                                      EPartitionScheme scheme,
                                      IEncoder <N, A, G> encoder,
                                      IImage <A> image,
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
    List <IImageBlock <A>> generateRangeBlocks ( RegionOfInterest <A> roi,
                                                 int rangeSize,
                                                 int domainSize ) throws ValueError {

        return partitionProcessor.generateRangeBlocks(roi, rangeSize, domainSize);
    }
}
