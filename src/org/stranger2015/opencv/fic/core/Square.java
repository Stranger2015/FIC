package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
 class Square extends Rectangle {

    /**
     * @param x
     * @param y
     * @param sideSize
     */
    public
    Square ( int x, int y, int sideSize ) throws ValueError {
        super(x, y, sideSize, sideSize);
    }

    /**
     * @return
     */
    @Override
    public
    int area () {
        return width * width;
    }
}

