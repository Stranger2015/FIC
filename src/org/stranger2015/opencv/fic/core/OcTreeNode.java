package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

@Deprecated
public
class OcTreeNode<N extends OcTreeNode<N,?>, A extends Address<A>> extends QuadTreeNode<N,A> {


    public
    OcTreeNode ( OcTreeNode<N,A> parent,
                 Direction direction,
                 Rect rect) {
        super(parent, direction, rect );
    }

    @Override
    public
    void draw ( Image image, Rect rect ) {
        super.draw(image, rect);
    }
}
