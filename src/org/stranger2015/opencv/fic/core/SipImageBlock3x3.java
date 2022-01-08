package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
class SipImageBlock3x3 extends SipImageBlock {

    /**
     * @param rows
     */
    public
    SipImageBlock3x3 ( int rows, int type ) {
        super(rows, 1, type);
    }

    public
    SipImageBlock3x3 ( Image image, int x, int y, int w, int h ) {
        super(image, x, y, w, h);
    }

    /**
     * @param address
     * @param data
     * @return
     */
    @Override
    public
    int put ( int address, double... data ) {
        return super.put(address, data);
    }

    /**
     * @return
     */
    @Override
    public
    String toString () {
        return "SipImageBlock3x3{" + '}';
    }

    /**
     * @return
     */
    @Override
    public
    ImageBlock subImage () {
        return super.subImage();
    }
}
