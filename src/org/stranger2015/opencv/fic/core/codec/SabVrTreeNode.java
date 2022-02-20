package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <A>
 * @param <M>
 */
public
class SabVrTreeNode<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer>
        extends SaTreeNode <N, A, M, G> {
    /**
     * @param parent
     * @param hexant
     * @param rect
     */
    public
    SabVrTreeNode ( TreeNode <N, A, M, G> parent, EDirection hexant, Rect rect ) throws ValueError {
        super(parent, hexant, rect);
    }
}
