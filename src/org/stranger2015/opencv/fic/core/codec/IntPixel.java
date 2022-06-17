package org.stranger2015.opencv.fic.core.codec;

/**
 *
 */
public
class IntPixel extends IntScalar {
    protected int[] val;

    /**
     * @param vals
     */
    public
    IntPixel ( int...vals ) {
        super(vals);
    }

    /**
     * @param intPixel
     * @return
     */
    IntPixel plus ( IntPixel intPixel ) {
        int[] val = new int[intPixel.val.length];
        for (int i = 0; i < intPixel.val.length; i++) {
            val[i] = intPixel.val[i] + intPixel.val[i];
        }

        return new IntPixel(val);
    }
}