package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;

import java.util.List;

public
class Triangle extends Square {

    public
    Triangle ( int x, int y, int size ) {
        super(x, y, size);
    }

    public
    Triangle () {}

    public
    Triangle ( Point p, Size s ) {
        super(p, s);
    }

    public
    Triangle ( Point p, int size ) {
        super(p, new Size(size, size));
    }

    public
    Triangle ( double[] vals ) {
        super(vals);
    }

    public static
    class TriangleTreeNode<N extends TriangleTreeNode<N>> extends QuadTreeNode<N> {

        public
        TriangleTreeNode (TriangleTreeNode<N> parent, Rect rect, List <N> nodes ) {
            super(parent, rect,nodes);
        }

          @Override
        public
        void draw ( Mat image, Rect rect ) {

        }

    }
}
