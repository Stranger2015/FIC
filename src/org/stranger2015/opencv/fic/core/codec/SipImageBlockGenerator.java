package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 *
 */
public
class SipImageBlockGenerator<N extends TreeNode <N>>
        extends SquareImageBlockGenerator <N> {

    /**
     * @param scheme
     * @param rangeSize
     * @param domainSize
     */
    public
    SipImageBlockGenerator ( IPartitionProcessor partitionProcessor,
                             EPartitionScheme scheme,
                             IEncoder encoder,
                             IImage image,
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
    ImageBlockGenerator <N> newInstance (
            IPartitionProcessor partitionProcessor,
            EPartitionScheme scheme,
            IEncoder encoder,
            IImage image,
            IIntSize rangeSize,
            IIntSize domainSize ) {

        return this;
    }
}
