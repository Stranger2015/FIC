package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.ITreeNodeBuilder;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class AlterBinTreePartitionProcessor<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends BinTreePartitionProcessor <N> {

    /**
     * @param tiler
     */
    public
    AlterBinTreePartitionProcessor ( ITiler <N> tiler,
                                     ImageBlockGenerator <N> imageBlockGenerator,
                                     ITreeNodeBuilder <N> nodeBuilder ) {

        super(tiler, imageBlockGenerator, nodeBuilder);
    }

    /**
     * @param tiler
     * @return
     */
    @Override
    public
    IPartitionProcessor <N> instance ( ITiler <N> tiler,
                                             ImageBlockGenerator <N> imageBlockGenerator,
                                             ITreeNodeBuilder <N> nodeBuilder ) {

        return new AlterBinTreePartitionProcessor <>(tiler,imageBlockGenerator,nodeBuilder);
    }
}
