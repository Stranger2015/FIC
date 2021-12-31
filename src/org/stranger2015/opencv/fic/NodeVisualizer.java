package org.stranger2015.opencv.fic;

import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.TreeNodeBase;

public
class NodeVisualizer implements IDrawable<Image> {

    private TreeNodeBase <?> treeNode;

    @Override
    public
    void draw ( Image image, Rect rect ) {
        treeNode.draw(image, rect);
    }

    public
    void setNode ( TreeNodeBase <?> treeNode ) {
        this.treeNode = treeNode;
    }
}
