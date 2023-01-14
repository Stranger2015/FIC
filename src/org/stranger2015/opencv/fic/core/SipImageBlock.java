package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;

/**
 *
 */
public
class SipImageBlock extends SaImageBlock  {

    public static final int blockSideSize = 3;//in encoder

    /**
     * @param address
     * @param j
     * @return
     */
    @Override
    public double[] get ( int address, int j ) {
        return super.get(address, j);
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
    SipImageBlock ( IImage image, int x, int y, int w, int h, int centerX, int centerY ) throws ValueError {
        super(image, x, y, w, h);
    }

    /**
     * @param image
     * @param x
     * @param y
     */
    public
    SipImageBlock ( IImage image, int x, int y ) throws ValueError {
        super(image, x, y, blockSideSize, blockSideSize);
    }

    /**
     * @param submat
     */
    public
    SipImageBlock ( Mat submat ) {
        super(submat);
    }

    /**
     * @return
     */
    public
    int getCenterX () {
        return getWidth()/2;
    }

    /**
     * @return
     */
    public
    int getCenterY () {
        return getHeight()/2;
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

        SipImageBlock  that = (SipImageBlock ) o;

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
    IImage contract ( int contractivity ) {
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
