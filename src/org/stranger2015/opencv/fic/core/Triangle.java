package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;

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
    class TriangleNode extends QuadNode {

        public
        TriangleNode ( Node child0, Node child1, Node child2, Node child3 ) {
            super(child0, child1, child2, child3);
        }

        public
        TriangleNode (Object...objects) {
        instance(objects);
        }

          @Override
        public
        void draw ( Mat image, Rect rect, boolean drawQuads ) {

        }

        /**
         * @param objects
         * @return
         */
        @Override
        public
        Node instance ( Object... objects ) {
            return new TriangleNode(
                    objects[0],
                    objects[1],
                    objects[2],
                    objects[3]
            );
        }
    }
}
