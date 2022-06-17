package org.stranger2015.opencv.fic.core;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IAddress;

/**
 *
 */
public
class SipImageBlock<A extends IAddress <A>> extends SaImageBlock <A> {

    public static final int blockSideSize = 3;//in encoder

    /**
     * @param address
     * @return
     */
    @Override
    public
    int[] get ( int address ) {
        return super.get(address);
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
    SipImageBlock ( Image <A> image, int x, int y, int w, int h, int centerX, int centerY ) throws ValueError {
        super(image, x, y, w, h);
    }

    /**
     * @param image
     * @param x
     * @param y
     */
    public
    SipImageBlock ( Image <A> image, int x, int y ) throws ValueError {
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
        return actualImage.row(-1)+;

        return 0;
    }

    /**
     * @return
     */
    public
    int getCenterY () {
        return 0;
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
