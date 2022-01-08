package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
class SipImageBlock extends ImageBlock3x3  {
    /**
     * @param rows
     * @param type
     */
    public
    SipImageBlock ( int rows, int type ) {
        super(rows, 1, type);
    }


    public
    SipImageBlock ( Image image, int x, int y, int w, int h ) {
        super(image, x, y, w, h);
    }

    public
    SipImageBlock ( int rows, int cols, int type ) {
        super(rows, cols, type);
    }
}
