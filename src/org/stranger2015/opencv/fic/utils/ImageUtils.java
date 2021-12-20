package org.stranger2015.opencv.fic.utils;

import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.codec.SipImage;

/**
 *
 */
public
class ImageUtils {

    /**
     *
     */
    private
    ImageUtils () {
    }

    /**
     * @param input
     * @return
     */
    public static
    SipImage imageToSipImage( Image input ){
        SipImage sipImage = new SipImage();
        double[] outputPixelData = new double[input.rows() * input.cols()];
        for (int i = 0; i < input.rows(); i++) {
            for (int j = 0; j < input.cols(); j++) {
                input.get(i, j);

            }
        }

        return sipImage;
    }
}
