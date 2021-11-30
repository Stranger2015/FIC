package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.util.List;

@Deprecated
public
class OcTreeNode<N extends OcTreeNode<N>> extends QuadTreeNode<N> {


    public
    OcTreeNode ( OcTreeNode<N> parent,
                 Direction direction,
                 Rect rect) {
        super(parent, direction, rect );
    }

    @Override
    public
    void draw ( Mat image, Rect rect ) {
        super.draw(image, rect);
    }
}
