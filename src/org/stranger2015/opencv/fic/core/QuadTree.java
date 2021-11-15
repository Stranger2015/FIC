package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.DomainBlock;

import java.util.function.Consumer;

/**
 * @param <N>
 * @param <M>
 */
public
class QuadTree<N extends QuadTreeNode<N>, M extends Mat> extends BinTree<N,M> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    QuadTree ( TreeNode<N> root, M image, TreeNodeAction <N> action) {
        super( root, image, action );
    }

    /**
     * @param w
     * @param h
     * @return
     */
    protected static
    <N extends DomainBlock<N>>
    NodeList <N> getDomainBlocks ( NodeList<N> leaves, int w, int h ) {
        NodeList<N> l = new NodeList <>();
        if (!leaves.isEmpty()) {
            for (Leaf<N> leaf : leaves) {
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
    QuadTreeNode<N> nodeInstance (QuadTreeNode<N> parent, CornerDirection quadrant, Rect rect ) {
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
