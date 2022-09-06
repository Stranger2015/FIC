package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class AlterBinTreePartitionProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreePartitionProcessor <N, A, G> {

    /**
     * @param tiler
     */
    public
    AlterBinTreePartitionProcessor ( ITiler <N, A, G> tiler ) {
        super(tiler);
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor <N, A, G> instance ( ITiler <N, A, G> tiler ) {
        return new AlterBinTreePartitionProcessor <>(tiler);
    }
}
