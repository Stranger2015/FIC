package org.stranger2015.opencv.fic.core.codec;

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
public
class QuadTreePartitionProcessor<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTreePartitionProcessor <N, A, G>  {

    /**
     *
     * @param tiler
     */
    public
    QuadTreePartitionProcessor ( ITiler <N, A, G> tiler,
                                 ImageBlockGenerator <N, A, G> imageBlockGenerator,
                                 ITreeNodeBuilder <N, A, G> nodeBuilder ) {

        super(tiler, imageBlockGenerator, nodeBuilder);
    }
}
