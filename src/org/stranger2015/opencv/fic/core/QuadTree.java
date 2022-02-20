package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.DomainBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.function.Consumer;

/**
 * @param <N>
 * @param <M>
 */
public
class QuadTree<N extends TreeNode <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer>
        extends BinTree <N, A, M, G> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    QuadTree ( TreeNode <N, A, M, G> root, M image, TreeNodeAction <N, A, M, G> action ) {
        super(root, image, action);
    }

    /**
     * @param w
     * @param h
     * @return
     */
    protected static
    <N extends DomainBlock <N, A, M, G>, A extends Address <A>, M extends IImage, G extends BitBuffer>
    NodeList <N, A, M, G> collectDomainBlocks ( NodeList <N, A, M, G> leaves, int w, int h ) {
        NodeList <N, A, M, G> l = new NodeList <>();
        if (!leaves.isEmpty()) {
            for (ILeaf <N, A, M, G> leaf : leaves) {
                M image = leaf.getImage();
                if (image.width() == w && image.height() == h) {
                    l.add(new DomainBlock <>(null, image, leaf.getBoundingBox()));
                }
            }
        }

        return l;
    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
    public
    TreeNode <N, A, M, G> nodeInstance ( TreeNode <N, A, M, G> parent, EDirection quadrant, Rect rect )
            throws ValueError {
        return new QuadTreeNode <>(parent, quadrant, rect);
    }

    /**
     * @return
     */
    public
    Consumer <N> getAction () {
        return action;
    }
}
