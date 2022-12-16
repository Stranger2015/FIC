package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class QuadTreePartitionProcessor
        extends BinTreePartitionProcessor  {

    /**
     *
     * @param tiler
     */
    public
    QuadTreePartitionProcessor ( ITiler tiler,
                                 ImageBlockGenerator<?> imageBlockGenerator,
                                 ITreeNodeBuilder <?> nodeBuilder ) {

        super(tiler, imageBlockGenerator, nodeBuilder);
    }
}
