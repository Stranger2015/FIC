package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public abstract
class PartitionProcessor
        implements IPartitionProcessor{

    protected ITiler tiler;
    protected ImageBlockGenerator <?> imageBlockGenerator;
    protected ITreeNodeBuilder <?> nodeBuilder;

    /**
     * @param tiler
     * @param imageBlockGenerator
     * @param nodeBuilder
     */
    @Contract(pure = true)
    protected
    PartitionProcessor ( ITiler tiler,
                         ImageBlockGenerator <?> imageBlockGenerator,
                         ITreeNodeBuilder<?> nodeBuilder ) {

        this.tiler = tiler;
        this.imageBlockGenerator = imageBlockGenerator;
        this.nodeBuilder = nodeBuilder;
    }

    public
    PartitionProcessor ( ITiler tiler ) {

        this.tiler = tiler;
    }

    /**
     * @return
     */
    @Override
    public
    ITiler getTiler () {
        return tiler;
    }

    /**
     * @return
     */
    public
    ITreeNodeBuilder <?> getNodeBuilder () {
        return nodeBuilder;
    }

    /**
     * @return
     */
    public
    ImageBlockGenerator <?> getImageBlockGenerator () {
        return imageBlockGenerator;
    }
}
