package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 *
 * @param <N>
 * @param <A>
 * @param <G>
 */
public abstract
class PartitionProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        implements IPartitionProcessor <N, A, G> {

    protected ITiler <N, A, G> tiler;

    /**
     * @param tiler
     */
    @Contract(pure = true)
    protected
    PartitionProcessor ( ITiler <N, A, G> tiler ) {
        this.tiler = tiler;
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> getTiler () {
        return tiler;
    }
}
