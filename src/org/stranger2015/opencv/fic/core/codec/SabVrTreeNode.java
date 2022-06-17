package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 
 */
public
class SabVrTreeNode<N extends TreeNode <N, A, G>, A extends IAddress <A>, M extends IImage<A>, G extends BitBuffer>
        extends SaTreeNode <N, A, G> {
    /**
     * @param parent
     * @param hexant
     * @param rect
     */
    public
    SabVrTreeNode ( TreeNode <N, A, G> parent, EDirection hexant, Rect rect ) throws ValueError {
        super(parent, hexant, rect);
    }
}
