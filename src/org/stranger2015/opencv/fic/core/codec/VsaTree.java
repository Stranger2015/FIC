package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.*;

/**
 * @param <N>
 * @param <M>
 */
public
class VsaTree<N extends VsaTreeNode <N, A>, M extends Image, A extends SaAddress <A>>
        extends Tree <N, M, A> {
    /**
     * @param parent
     * @param hexant
     * @param boundingBox
     * @return
     */
    @Override
    public
    TreeNode <N, A> nodeInstance ( TreeNode <N, A> parent, Direction hexant, Rect boundingBox ) throws ValueError {
        return new VsaTreeNode <>(parent, hexant, boundingBox);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) VsaTreeNode.class;
    }
}
