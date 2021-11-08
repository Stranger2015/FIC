package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.List;

/**
 *
 */
public
class QuadTreeNode<N extends QuadTreeNode <N>> extends BinTreeNode <N> {

    public
    QuadTreeNode ( N parent, CornerDirection quadrant, Rect rect, List <N> nodes ) {
        super(parent, quadrant, rect, nodes);
    }

//    public
//    List <TreeNode> build ( Mat image, int width, int height, int x, int y, int w, int h ) {
//        List <TreeNode> children = new ArrayList <>();
//
//        if (width == w) {
//            new Leaf(image.submat(x, y, x + width, y = height), new Rect(x, y, w, h));
//        }
//
//        children.addAll(build(image, width / 2, height / 2, x, y, w, h));
//        children.addAll(build(image, width / 2, height / 2, x + width / 2, y, w, h));
//        children.addAll(build(image, width / 2, height / 2, x, y + height / 2, w, h));
//        children.addAll(build(image, width / 2, height / 2, x + width / 2, y + height / 2, w, h));
//
//        TreeNode qTree = instance();
//        return children;
//    }

    @Override
    public
    void draw ( Mat image, Rect rect ) {
        super.draw(image, rect);
    }
}