package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.DomainBlock;

import java.util.function.Consumer;

/**
 * @param <N>
 * @param <M>
 */
public
class QuadTree<N extends QuadTreeNode <N, A>, M extends Image, A extends Address <A, ?>>
        extends BinTree <N, M, A> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    QuadTree ( TreeNodeBase <N, A> root, M image, TreeNodeAction <N> action ) {
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
    <N extends DomainBlock <N, A, M>, A extends Address <A, ?>, M extends Image>
    NodeList <N,A> getDomainBlocks ( NodeList <N, A> leaves, int w, int h ) throws ValueError {
        NodeList <N, A> l = new NodeList <>();
        if (!leaves.isEmpty()) {
            for (ILeaf <N, A, M> leaf : leaves) {
                M image = leaf.getImage();
                if (image.width() == w && image.height() == h) {
                    l.add(new DomainBlock <>(null, image, leaf.getBoundingBox()));//fixme
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
    TreeNodeBase <N, A> nodeInstance ( QuadTreeNode <N, A> parent, EDirection quadrant, Rect rect ) throws ValueError {
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
