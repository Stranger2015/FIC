package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.BinTreeImageBlockGenerator;
import org.stranger2015.opencv.fic.core.codec.IEncoder;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class QuadTreeImageBlockGenerator<N extends TreeNode <N>>
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
    QuadTreeImageBlockGenerator ( IPartitionProcessor partitionProcessor,
                                  EPartitionScheme scheme,
                                  IEncoder encoder,
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
