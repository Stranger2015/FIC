package org.stranger2015.opencv.fic.core;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.stranger2015.opencv.fic.IDrawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public abstract
class Polygon implements IShape, IDrawable <Image> {
    protected int sides;
    protected double side;

    public
    List <Point> getVertices () {
        return Collections.unmodifiableList(vertices);
    }

    protected final List <Point> vertices = new ArrayList <>();

    public
    int getX () {
        return x;
    }

    public
    int getY () {
        return y;
    }

    protected int x;
    protected int y;

    /**
     * @param i
     * @param size
     */
    public
    Polygon ( int x, int y, int i, int size ) {
        this.x = x;
        this.y = y;
        sides = i;
        side = size;
    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Image image, Rect rect ) {
        for (int i = 0; i < sides; i++) {
            double x1 = x + side * Math.cos(i * 2 * Math.PI / sides);
            double y1 = y + side * Math.sin(i * 2 * Math.PI / sides);
            vertices.add(new Point(x1, y1));
//            ....todo
        }
    }
}
