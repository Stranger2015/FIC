package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class SaImageBlockGenerator<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends ImageBlockGenerator <N> {

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    SaImageBlockGenerator ( IPartitionProcessor <N> partitionProcessor,
                            EPartitionScheme scheme,
                            IEncoder <N> encoder,
                            IImage image,
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
    HvBlockGenerator <N> newInstance ( IPartitionProcessor <N> partitionProcessor,
                                                EPartitionScheme scheme,
                                                IEncoder <N> encoder,
                                                IImage image,
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
