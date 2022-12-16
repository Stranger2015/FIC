package org.stranger2015.opencv.fic.core;

import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.function.Consumer;

/**
 * @param <N>
 
 */
public
class QuadTree<N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
        extends BinTree <N> {

    /**
     * @param root
     * @param image
     * @param action
     */
    public
    QuadTree ( TreeNode <N> root, IImageBlock image, TreeNodeTask <N> action ) {
        super(root, image, action);
    }

    /**
     * @param w
     * @param h
     * @return
     */
//    protected static
//    <N extends TreeNode <N>, A extends IAddress , G extends BitBuffer>
//    List <IImageBlock> collectDomainBlocks ( List <LeafNode<N>> leaves, int w, int h ) {
//        List <IImageBlock> l = new ArrayList <> ();
//        if (!leaves.isEmpty()) {
//            for (LeafNode <N> leaf : leaves) {
//                IImageBlock image = leaf.getImageBlock();
//                if (image.getWidth() == w && image.getHeight() == h) {
//                    l.add(image);
//                }
//            }
//        }
//
//        return l;
//    }

    /**
     * @param parent
     * @param quadrant
     * @param rect
     * @return
     */
//    @Override
    public
    TreeNode <N> nodeInstance ( TreeNodeBase <N> parent, EDirection quadrant, Rectangle rect )
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
