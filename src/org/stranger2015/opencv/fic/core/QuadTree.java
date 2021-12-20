package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.DomainBlock;
import org.stranger2015.opencv.fic.core.codec.IAddress;

import java.util.function.Consumer;

/**
 * @param <N>
 * @param <M>
 */
public
class QuadTree<N extends QuadTreeNode <N,?>, M extends Image, A extends IAddress <A>> extends BinTree <N, M,         A> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    QuadTree ( TreeNode <N,?> root, M image, TreeNodeAction <N> action ) {
        super(root, image, action);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    Class <N> getNodeClass () {
        return (Class <N>) QuadTreeNode.class;
    }

    /**
     * @param w
     * @param h
     * @return
     */
    protected static
    <N extends DomainBlock <N>>
    NodeList <N> getDomainBlocks ( NodeList <N> leaves, int w, int h ) {
        NodeList <N> l = new NodeList <>();
        if (!leaves.isEmpty()) {
            for (Leaf <N> leaf : leaves) {
                Mat image = leaf.getImage();
                if (image.width() == w && image.height() == h) {
                    l.add(new DomainBlock <N>(null, leaf.image, leaf.boundingBox));//fixme
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
    TreeNode <N,?> nodeInstance ( QuadTreeNode<N,?> parent, Direction quadrant, Rect rect ) {
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
