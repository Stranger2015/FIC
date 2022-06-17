package org.stranger2015.opencv.fic.core;

import static org.stranger2015.opencv.fic.core.IShape.EShape.CIRCLE;

public
class Circle extends org.stranger2015.opencv.fic.core.Shape {

int radius;

    public
    Circle () {
        super(image, blockSize);
    }

    /**
     * @return
     */
    @Override
    public
    IShape.EShape getShapeKind () {
        return CIRCLE;
    }

    /**
     * @return
     */
    @Override
    public
    double area () {
        return (int) (Math.PI*2*radius);//fixme
    }
}
