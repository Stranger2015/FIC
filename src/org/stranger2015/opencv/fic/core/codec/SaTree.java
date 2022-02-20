package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

/**
 * @param <N>
 * @param <M>
 */
public
class SaTree<N extends TreeNode <N, A, M, G>, A extends SaAddress <A>, M extends IImage>
        extends Tree <N, A, M, G> {


    public
    SaTree ( TreeNode <N, A, M, G> root, M image, TreeNodeAction <N, A, M, G> action ) {
        super(root, image, action);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A, M, G> nodeInstance ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect ) {
        return new SaTreeNode <>();
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public//fixme
    Class <? extends TreeNode <N, A, M, G>> getNodeClass ( TreeNode <N, A, M, G> clazz ) {
        return (Class <? extends TreeNode <N, A, M, G>>) clazz.getClass();
    }
}
