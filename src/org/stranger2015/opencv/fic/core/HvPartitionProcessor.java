package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.IPartitionProcessor;
import org.stranger2015.opencv.fic.core.codec.PartitionProcessor;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class HvPartitionProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends PartitionProcessor <N, A, G> {

    /**
     * @param tiler
     */
    public
    HvPartitionProcessor ( ITiler<N, A, G> tiler ) {
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
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> instance ( ITiler <N, A, G> tiler ) {
        return new HvPartitionProcessor<>(tiler);
    }
}
