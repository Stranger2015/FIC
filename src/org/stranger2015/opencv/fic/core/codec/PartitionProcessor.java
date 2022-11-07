package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class PartitionProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements IPartitionProcessor <N, A, G> {

    protected ITiler <N, A, G> tiler;
    protected ImageBlockGenerator <N, A, G> imageBlockGenerator;
    protected ITreeNodeBuilder <N, A, G> nodeBuilder;

    /**
     * @param tiler
     * @param imageBlockGenerator
     * @param nodeBuilder
     */
    @Contract(pure = true)
    protected
    PartitionProcessor ( ITiler <N, A, G> tiler,
                         ImageBlockGenerator <N, A, G> imageBlockGenerator,
                         ITreeNodeBuilder<N,A,G> nodeBuilder ) {

        this.tiler = tiler;
        this.imageBlockGenerator = imageBlockGenerator;
        this.nodeBuilder = nodeBuilder;
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> getTiler () {
        return tiler;
    }

    /**
     * @return
     */
    public
    ITreeNodeBuilder <N, A, G> getNodeBuilder () {
        return nodeBuilder;
    }

    /**
     * @return
     */
    public
    ImageBlockGenerator <N, A, G> getImageBlockGenerator () {
        return imageBlockGenerator;
    }
}
