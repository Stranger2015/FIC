package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
class SipImageBlock extends ImageBlock {

    public static final int blockSideSize = 3;//in encoder

    protected int centerX;
    protected int centerY;

    /**
     * @param centerX
     */
    public
    void setCenterX ( int centerX ) {
        this.centerX = centerX;
    }

    /**
     * @param centerY
     */
    public
    void setCenterY ( int centerY ) {
        this.centerY = centerY;
    }

    /**
     * @param address
     * @return
     */
    public
    double[] get ( int address ) {
        return super.get(address, 0);
    }

    /**
     * @param image
     * @param x
     * @param y
     * @param w
     * @param h
     */
    public
    SipImageBlock ( Image image, int x, int y, int w, int h ) {
        super(image, x, y, w, h);
    }

    /**
     * @param image
     * @param x
     * @param y
     */
public
    SipImageBlock ( Image image, int x, int y) {
        super(image, x, y, blockSideSize, blockSideSize);
    }

    /**
     * @return
     */
    public
    int getCenterX () {
        return centerX;
    }

    /**
     * @return
     */
    public
    int getCenterY () {
        return centerY;
    }

    @Override
    public
    boolean equals ( Object o ) {
        if (this == o) return true;
        if (!(o instanceof SipImageBlock)) return false;

        SipImageBlock that = (SipImageBlock) o;

        if (getCenterX() != that.getCenterX()) return false;
        return getCenterY() == that.getCenterY();
    }

    @Override
    public
    int hashCode () {
        int result = getCenterX();
        result = 31 * result + getCenterY();
        return result;
    }
}
