package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 
 */
public
class SaTree<N extends TreeNode <N>>
        extends Tree <N> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    SaTree ( TreeNodeBase <N> root, IImage image, TreeNodeTask <N> action ) {
        super(root, image, action);
    }

    /**
     * @param root
     * @param imageBlock
     */
    public
    SaTree ( TreeNode <N> root, IImageBlock  imageBlock ) {

    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N> nodeInstance ( TreeNodeBase <N> parent, EDirection quadrant, IIntSize rect ) throws ValueError {
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
    TreeNode <N> nodeInstance ( TreeNodeBase <N> parent, EDirection quadrant, Rect rect ) throws ValueError {
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
    Class <? extends TreeNode <N>> getNodeClass ( TreeNode <N> clazz ) {
        return (Class <? extends TreeNode <N>>) clazz.getClass();
    }
}
