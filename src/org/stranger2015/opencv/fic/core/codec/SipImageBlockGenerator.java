package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class SipImageBlockGenerator<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer>
        extends SquareImageBlockGenerator <N, A, M, G> {

    /**
     * @param rangeSize
     * @param domainSize
     */
    public
    SipImageBlockGenerator ( IEncoder <N, A, M, G> encoder, IImage <A> image, Size rangeSize, Size domainSize ) {
        super(encoder, image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    SipImageBlockGenerator <N, A, M, G> newInstance () {
        return this;
    }
}
