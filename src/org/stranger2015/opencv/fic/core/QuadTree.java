package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @param <N>
 
 */
public
class QuadTree<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends BinTree <N, A, G> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    QuadTree ( TreeNode <N, A, G> root, IImageBlock<A> image, TreeNodeTask <N, A, G> action ) {
        super(root, image, action);
    }

    /**
     * @param w
     * @param h
     * @return
     */
    protected static
    <N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
    List <IImageBlock<A>> collectDomainBlocks ( List <LeafNode<N, A, G>> leaves, int w, int h ) {
        List <IImageBlock<A>> l = new ArrayList <> ();
        if (!leaves.isEmpty()) {
            for (LeafNode <N, A, G> leaf : leaves) {
                IImageBlock<A> image = leaf.getImageBlock();
                if (image.getWidth() == w && image.getHeight() == h) {
                    l.add(image);
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
    @Override
    public
    TreeNode <N, A, G> nodeInstance ( TreeNodeBase <N, A, G> parent, EDirection quadrant, IIntSize rect )
            throws ValueError {

        return new QuadTreeNode <>(parent, quadrant, rect);
    }

    /**
     * @return
     */
    @SuppressWarnings("unchecked")
    public
    Consumer <N> getAction () {
        return (Consumer <N>) action;
    }
}
