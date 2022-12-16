package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class BinTreePartitionProcessor extends PartitionProcessor {

    /**
     * @param tiler
     */
    public
    BinTreePartitionProcessor ( ITiler tiler,
                                ImageBlockGenerator <?> imageBlockGenerator,
                                ITreeNodeBuilder <?> nodeBuilder ) {

        super(tiler, imageBlockGenerator, nodeBuilder);
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 2;
    }

    /**
     * @return
     */
    @Override
    public
    IPartitionProcessor instance ( ITiler tiler,
                                   ImageBlockGenerator <?> imageBlockGenerator,
                                   ITreeNodeBuilder <?> nodeBuilder ) {

        return new BinTreePartitionProcessor(tiler, imageBlockGenerator, nodeBuilder);
    }
}
