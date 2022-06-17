package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ITiler;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 */
public
class SaImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, M extends IImage<A>,
        G extends BitBuffer>
        extends ImageBlockGenerator <N, A, G> {

    public
    SaImageBlockGenerator ( ITiler <M, A> tiler,
                            EPartitionScheme scheme,
                            IEncoder <N, A, G> encoder,
                            GrayScaleImage <A> image,
                            IntSize rangeSize,
                            IntSize domainSize ) {

        super(tiler, scheme, encoder, image, rangeSize, domainSize);
    }
}
