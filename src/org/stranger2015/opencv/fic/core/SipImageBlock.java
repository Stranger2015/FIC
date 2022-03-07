package org.stranger2015.opencv.fic.core;

/**
 *
 */
public
class SipImageBlock<A extends Address <A>> extends SaImageBlock <A> {

    public static final int blockSideSize = 3;//in encoder

    protected final int centerX;
    protected final int centerY;

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
     * @param centerX
     */
    public
    SipImageBlock ( Image <A> image, int x, int y, int w, int h, int centerX, int centerY ) {
        super(image, x, y, w, h);

        this.centerX = centerX;
        this.centerY = centerY;
    }

    /**
     * @param image
     * @param x
     * @param y
     */
    public
    SipImageBlock ( Image <A> image, int x, int y ) {
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

    @SuppressWarnings("unchecked")
    @Override
    public
    boolean equals ( Object o ) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SipImageBlock)) {
            return false;
        }

        SipImageBlock <A> that = (SipImageBlock <A>) o;

        if (getCenterX() != that.getCenterX()) {
            return false;
        }

        return getCenterY() == that.getCenterY();
    }

    /**
     * @param contractivity
     * @return
     */
    @Override
    public
    IImage <A> contract ( int contractivity ) {
        return super.contract(contractivity);
    }

    /**
     * @return
     */
    @Override
    public
    int hashCode () {
        int result = getCenterX();
        result = 31 * result + getCenterY();

        return result;
    }
}
