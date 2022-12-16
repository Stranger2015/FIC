package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.BinTreeImageBlockGenerator;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class QuadTreeImageBlockGenerator<N extends TreeNode <N>, A extends IAdd>
        extends BinTreeImageBlockGenerator <N> {

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     */
    public
    QuadTreeImageBlockGenerator ( IPartitionProcessor <N> partitionProcessor,
                                  EPartitionScheme scheme,
                                  IEncoder <N> encoder,
                                  IImage image,
                                  IIntSize rangeSize,
                                  IIntSize domainSize ) {
        super(
                partitionProcessor,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }
}
