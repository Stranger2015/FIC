package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.codec.ImageBlockGenerator;
import org.stranger2015.opencv.fic.core.codec.PartitionProcessor;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class HvPartitionProcessor
        extends PartitionProcessor {

    /**
     * @param tiler
     */
    public
    HvPartitionProcessor ( ITiler tiler ) {
        super(tiler);
    }

    /**
     * @return
     */
    @Override
    public
    int successorAmount () {
        return 1;//todo
    }


    /**
     * @param tiler
     * @param imageBlockGenerator
     * @param nodeBuilder
     * @return
     */
    @Override
    public
    IPartitionProcessor instance ( ITiler tiler,
                                   ImageBlockGenerator <?> imageBlockGenerator,
                                   ITreeNodeBuilder<?> nodeBuilder ) {
        return null;
    }

    /**
     * @param tiler
     * @return
     */
//    @Override
    public
    IPartitionProcessor instance ( ITiler tiler ) {
        return new HvPartitionProcessor(tiler);
    }
}
