package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class TilerLeafNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends LeafNode <N, A, G> {

    public final ITiler <N, A, G> tiler;

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @param tiler
     */
    public
    TilerLeafNode ( TreeNodeBase <N, A, G> parent, EDirection quadrant, Rectangle rect, ITiler<N, A, G> tiler)
            throws ValueError {

        super(parent, quadrant, rect);

        this.tiler = tiler;
    }
}
