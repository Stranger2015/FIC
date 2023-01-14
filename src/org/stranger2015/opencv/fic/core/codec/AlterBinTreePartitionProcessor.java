package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class AlterBinTreePartitionProcessor
        extends BinTreePartitionProcessor {

    /**
     * @param tiler
     */
    public
    AlterBinTreePartitionProcessor ( ITiler tiler,
                                     ImageBlockGenerator <?> imageBlockGenerator,
                                     ITreeNodeBuilder <?> nodeBuilder ) {

        super(tiler, imageBlockGenerator, nodeBuilder);
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor instance ( ITiler tiler,
                                             ImageBlockGenerator <?> imageBlockGenerator,
                                             ITreeNodeBuilder <?> nodeBuilder ) {

        return new AlterBinTreePartitionProcessor (tiler,imageBlockGenerator,nodeBuilder);
    }
}
