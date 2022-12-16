package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IIntSize;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class SipImageBlockGenerator<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends SquareImageBlockGenerator <N> {

    /**
     * @param scheme
     * @param rangeSize
     * @param domainSize
     */
    public
    SipImageBlockGenerator ( IPartitionProcessor <N> partitionProcessor,
                             EPartitionScheme scheme,
                             IEncoder <N> encoder,
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
            IPartitionProcessor <N> partitionProcessor,
            EPartitionScheme scheme,
            IEncoder <N> encoder,
            IImage image,
            IIntSize rangeSize,
            IIntSize domainSize ) {

        return this;
    }
}
