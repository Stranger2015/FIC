package org.stranger2015.opencv.fic.core;

import org.opencv.core.Rect;

/**
 *
 */
public
class Rectangle extends Rect implements IShape {
    public
    Rectangle ( int x, int y, int width, int height ) {
        super(x, y, width, height);
    }

    /**
     * @param image
     * @param rect
     */
    @Override
    public
    void draw ( Image image, Rect rect ) {

    }
}
