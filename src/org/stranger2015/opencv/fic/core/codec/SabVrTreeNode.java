package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param
 
 */
public
class SabVrTreeNode<N extends TreeNode <N>>
        extends SaTreeNode<N> {
    /**
     * @param parent
     * @param hexant
     * @param rect
     */
    public
    SabVrTreeNode ( TreeNode <N> parent, EDirection hexant, Rect rect ) throws ValueError {
        super(parent, hexant, (IIntSize) rect);
    }
}
