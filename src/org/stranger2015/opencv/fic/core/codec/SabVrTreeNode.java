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
class SabVrTreeNode<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends SaTreeNode <N, A, M> {
    /**
     * @param parent
     * @param hexant
     * @param rect
     */
    public
    SabVrTreeNode ( TreeNode <N, A, M> parent, EDirection hexant, Rect rect ) throws ValueError {
        super(parent, hexant, rect);
    }
}
