package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.List;

/**
 *
 */
public
class SquareImageBlockGenerator<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage <A>,
        G extends BitBuffer>

        extends ImageBlockGenerator <N, A, M, G> {

    /**
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    SquareImageBlockGenerator ( ITiler <M, A> tiler,
                                IEncoder <N, A, M, G> encoder,
                                IImage <A> image,
                                Size rangeSize,
                                Size domainSize ) {

        super(tiler, encoder, image, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    SquareImageBlockGenerator <N, A, M, G> newInstance () {
        return this;
    }
}
