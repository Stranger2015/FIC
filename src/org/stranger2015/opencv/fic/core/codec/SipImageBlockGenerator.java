package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class SipImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */,
        G extends BitBuffer>
        extends SquareImageBlockGenerator <N, A, G> {

    /**
     * @param scheme
     * @param rangeSize
     * @param domainSize
     */
    public
    SipImageBlockGenerator ( ITiler <N, A, G> tiler,
                             EPartitionScheme scheme,
                             IEncoder <N, A, G> encoder,
                             IImage <A> image,
                             IIntSize rangeSize,
                             IIntSize domainSize ) {
        super(tiler, encoder, image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    SipImageBlockGenerator <N, A, G> newInstance () {
        return this;
    }
}
