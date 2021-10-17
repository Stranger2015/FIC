package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.stranger2015.opencv.fic.IDrawable;

public
class Square extends Rect implements IDrawable {
    public
    Square ( int x, int y, int size ) {
        super(x, y, size, size);
    }

    public
    Square () {
    }

    public
    Square ( Point p1, Point p2 ) {
        super(p1, p2);
    }

    public
    Square ( Point p, Size s ) {
        super(p, s);
    }

    public
    Square ( double[] vals ) {
        super(vals);
    }

    @Override
    public
    void draw ( Mat image, Rect rect, boolean drawQuads ) {

    }

}