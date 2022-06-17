package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BushTree<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Tree<N, A, G> {

    /**
     * Constructs a new object.
     *
     * @param root
     * @param image
     * @param action
     * @param area
     * @param depth
     */
    public
    BushTree ( TreeNodeBase <N, A, G> root,
               IImageBlock <A> image,
               TreeNodeTask <N, A, G> action,
               IRectangle area,
               int depth ) {

        super(root, image, action, area, depth);
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    @Override
    public
    TreeNode <N, A, G> nodeInstance ( TreeNodeBase <N, A, G> parent,
                                      EDirection quadrant,
                                      IIntSize rect ) throws ValueError {

        TreeNode <N, A, G> node = chooseNodeType(getImage());

        return node;
    }

    private
    TreeNode <N, A, G> chooseNodeType ( IImage <A> image ) {
        return null;
    }
}