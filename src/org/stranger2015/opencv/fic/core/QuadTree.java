package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.DomainBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;

import java.util.function.Consumer;

/**
 * @param <N>
 * @param <M>
 */
public
class QuadTree<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends BinTree <N, A, M> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    QuadTree ( TreeNode <N, A, M> root, M image, TreeNodeAction <N> action ) {
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
    <N extends DomainBlock <N, A, M>, A extends Address <A>, M extends Image>
    NodeList <N, A, M> collectDomainBlocks ( NodeList <N, A, M> leaves, int w, int h ) throws ValueError {
        NodeList <N, A, M> l = new NodeList <>();
        if (!leaves.isEmpty()) {
            for (ILeaf <N, A, M> leaf : leaves) {
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
    TreeNode <N, A, M> nodeInstance ( TreeNode <N, A, M> parent, EDirection quadrant, Rect rect )
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
