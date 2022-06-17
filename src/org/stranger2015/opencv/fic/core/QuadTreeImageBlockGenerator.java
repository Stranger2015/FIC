package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.BinTreeImageBlockGenerator;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class QuadTreeImageBlockGenerator<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreeImageBlockGenerator <N, A, G> {
    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    QuadTreeImageBlockGenerator ( ITiler <N, A, G> tiler, EPartitionScheme scheme, IEncoder <N, A, G> encoder, IImage <A> image, IIntSize rangeSize, IIntSize domainSize ) {
        super(tiler, scheme, encoder, image, rangeSize, domainSize);
    }
}
