package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;

import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param
 
 */
public
class SabVrTreeNode<N extends TreeNode <N>, A extends IAddress , IImage extends IImage, G extends BitBuffer>
        extends SaTreeNode <N> {
    /**
     * @param parent
     * @param hexant
     * @param rect
     */
    public
    SabVrTreeNode ( TreeNode <N> parent, EDirection hexant, Rect rect ) throws ValueError {
        super(parent, hexant, rect);
    }
}
