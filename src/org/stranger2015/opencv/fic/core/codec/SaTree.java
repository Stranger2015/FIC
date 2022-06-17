package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 
 */
public
class SaTree<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tree <N, A, G> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    SaTree ( TreeNodeBase <N, A, G> root, IImage<A> image, TreeNodeTask <N, A, G> action ) {
        super(root, image, action);
    }

    /**
     * @param root
     * @param imageBlock
     */
    public
    SaTree ( TreeNode <N, A, G> root, IImageBlock <A> imageBlock ) {

    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A, G> nodeInstance ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect ) throws ValueError {
        return null;
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
//    @Override
    public
    TreeNode <N, A, G> nodeInstance ( TreeNodeBase <N, A, G> parent, EDirection quadrant, Rect rect ) throws ValueError {
        return null;
    }

    /**
     *
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public//fixme
    Class <? extends TreeNode <N, A, G>> getNodeClass ( TreeNode <N, A, G> clazz ) {
        return (Class <? extends TreeNode <N, A, G>>) clazz.getClass();
    }
}
