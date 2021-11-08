package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.NotNull;
import org.opencv.core.Mat;

import java.util.Collections;

/**
 * @param <N>
 * @param <M>
 */
public
class TreeBuilder<N extends TreeNode <N>, M extends Mat> extends TreeTraverser <N> {

    public
    TreeBuilder ( @NotNull Tree <N, M> tree ) {
        super((Tree <N, M>) tree,
                tree.getTraverser(tree,
                        new NeighborVector <>(Collections.emptyList()),
                        1024,
                        (TreeNodeAction) tree.action));
    }

//    public
//    TreeNode build ( Mat image, int x, int y, int width, int height ) {
//        TreeNode node;
//        List <TreeNode> children = new ArrayList <>();
//
//        if (width == DomainBlock.W && height == DomainBlock.H) {
//            node = new DomainBlock(image, x, y, width, height);
//        }
//        else {
//            node = new Leaf(image, x, y, width, height);
//        }
//        int halfWidth = width / 2;
//        int halfHeight = height / 2;
//        for (int i = 0; i < children.size(); i++) {
////            build(image);
//        }
////         = build(image, x, y, width / 2, height / 2);
////        children.addAll(build(image, x+width / 2, y, x + width / 2, y, w, h));
////        children.addAll(build(image,  x, y + height / 2, w, h));
////        children.addAll(build(image, width / 2, height / 2, x + width / 2, y + height / 2, w, h));
////
////        TreeNode qTree = instance();
////
////        return node;
////    }
//        return node;
//    }
//
//    protected
//    N buildChild ( int i ) {
//        N node = null;
//
//
//        return node;
//    }
}
