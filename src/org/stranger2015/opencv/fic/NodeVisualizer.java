package org.stranger2015.opencv.fic;

import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.core.Node;

public
class NodeVisualizer implements IDrawable {

    private Node node;

    @Override
    public
    void draw ( Mat image, Rect rect, boolean  drawQuads ) {
        node.draw(image, rect, drawQuads);
    }

    public
    void setNode ( Node node ) {
        this.node = node;
    }
}
