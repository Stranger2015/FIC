package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.DomainBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * @param <N>
 * @param <M>
 */
public
class QuadTree<N extends QuadTreeNode<N>, M extends Mat> extends BinTree<N,M> {

    public
    QuadTree ( N parent, M image, Consumer <N> action ) {
        super(parent, image, action);
    }

    protected
    List <DomainBlock> getDomainBlocks ( int w, int h ) {
        List <DomainBlock> l = new ArrayList <>();
        if (!leaves.isEmpty()) {
            for (Leaf leaf : leaves) {
                Mat image = leaf.getImage();
                if (image.width() == w && image.height() == h) {
                    l.add(new DomainBlock(leaf.parent, leaf.image, leaf.boundingBox));
                }
            }
        }

        return l;
    }

    @Override
    public final
    QuadTreeNode<N> nodeInstance (N parent, CornerDirection quadrant, Rect rect, List<N> nodes ) {
        return new QuadTreeNode <>(parent, quadrant, rect, nodes);
    }

    public
    Consumer <N> getAction () {
        return action;
    }
}
