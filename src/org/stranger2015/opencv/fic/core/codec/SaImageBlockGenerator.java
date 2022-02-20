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
class SaImageBlockGenerator<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage<A>  <A>,
        G extends BitBuffer>
        extends ImageBlockGenerator <N, A, M, G> {
    /**
     * @param rangeSize
     * @param domainSize
     */
    public
    SaImageBlockGenerator ( IEncoder <N, A, M, G> encoder, IImage <A> image, Size rangeSize, Size domainSize ) {
        super(encoder, image, rangeSize, domainSize);
    }
}
