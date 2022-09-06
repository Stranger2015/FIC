package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class SipImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends SquareImageBlockGenerator <N, A, G> {

    /**
     * @param scheme
     * @param rangeSize
     * @param domainSize
     */
    public
    SipImageBlockGenerator ( IPartitionProcessor <N, A, G> partitionProcessor,
                             EPartitionScheme scheme,
                             IEncoder <N, A, G> encoder,
                             IImage <A> image,
                             IIntSize rangeSize,
                             IIntSize domainSize ) {
        super(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
    }

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    HvBlockGenerator <N, A, G> newInstance ( IPartitionProcessor <N, A, G> partitionProcessor,
                                                   EPartitionScheme scheme,
                                                   IEncoder <N, A, G> encoder,
                                                   IImage <A> image,
                                                   IIntSize rangeSize,
                                                   IIntSize domainSize ) {
        return this;
    }
}
