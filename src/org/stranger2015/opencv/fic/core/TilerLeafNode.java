package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.codec.tilers.ITiler;

/**
 * @param <N>
 * @param
 * @param <G>
 */
public
class TilerLeafNode<N extends TreeNode <N>>
        extends LeafNode <N> {

    public final ITiler tiler;

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @param tiler
     */
    public
    TilerLeafNode ( TreeNodeBase <N> parent, EDirection quadrant, Rectangle rect, ITiler tiler )
            throws ValueError {

        super(parent, quadrant, rect);

        this.tiler = tiler;
    }
}
