package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.codec.ImageBlockGenerator;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class HvBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends ImageBlockGenerator <N, A, G> {
    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    HvBlockGenerator ( IPartitionProcessor <N, A, G> partitionProcessor,
                       EPartitionScheme scheme,
                       IEncoder <N, A, G> encoder,
                       IImage <A> image,
                       IIntSize rangeSize,
                       IIntSize domainSize ) {

        super(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
    }

    @Override
    public
    HvBlockGenerator <N, A, G> newInstance ( IPartitionProcessor <N, A, G> partitionProcessor,
                                                EPartitionScheme scheme,
                                                IEncoder <N, A, G> encoder,
                                                IImage <A> image,
                                                IIntSize rangeSize,
                                                IIntSize domainSize ) {
        return new HvBlockGenerator <>(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
    }
}
