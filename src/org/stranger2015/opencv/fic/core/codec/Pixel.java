package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Scalar;

/**
 *
 */
public
class Pixel extends Scalar {
    /**
     * @param vals
     */
    public
    Pixel ( double...vals ) {
        super(vals);
    }

    /**
     * @param pixel
     * @return
     */
    Pixel plus ( Pixel pixel ) {
        double[] val = new double[pixel.val.length];
        for (int i = 0; i < pixel.val.length; i++) {
            val[i] = this.val[i] + pixel.val[i];
        }

        return new Pixel(val);
    }
}