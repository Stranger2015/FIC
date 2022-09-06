package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class SaImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
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
    SaImageBlockGenerator ( IPartitionProcessor <N, A, G> partitionProcessor,
                            EPartitionScheme scheme,
                            IEncoder <N, A, G> encoder,
                            IImage <A> image,
                            IIntSize rangeSize,
                            IIntSize domainSize ) {

        super(partitionProcessor, scheme, encoder, image, rangeSize, domainSize);
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
    @Override
    public
    HvBlockGenerator <N, A, G> newInstance ( IPartitionProcessor <N, A, G> partitionProcessor,
                                                EPartitionScheme scheme,
                                                IEncoder <N, A, G> encoder,
                                                IImage <A> image,
                                                IIntSize rangeSize,
                                                IIntSize domainSize ) {
        return new SaImageBlockGenerator <>(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize);
    }
}
