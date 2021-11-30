package org.stranger2015.opencv.fic;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.TreeNode;

public
class NodeVisualizer implements IDrawable<Image> {

    private TreeNode<?> treeNode;

    @Override
    public
    void draw ( Image image, Rect rect ) {
        treeNode.draw(image, rect);
    }

    public
    void setNode ( TreeNode<?> treeNode ) {
        this.treeNode = treeNode;
    }
}
