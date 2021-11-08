package org.stranger2015.opencv.fic;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.TreeNode;

public
class NodeVisualizer implements IDrawable {

    private TreeNode treeNode;

    @Override
    public
    void draw ( Mat image, Rect rect ) {
        treeNode.draw(image, rect);
    }

    public
    void setNode ( TreeNode treeNode ) {
        this.treeNode = treeNode;
    }
}
