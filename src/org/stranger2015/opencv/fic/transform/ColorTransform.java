package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 
 * @param
 * @param <G>
 */
public
class ColorTransform implements ITransform {

    @Override
    public
    IImage warpAffine ( IImage inputImage, Mat transformMatrix, EInterpolationType interpolationType ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    IAddress getAddress () throws InstantiationException, IllegalAccessException {
        return null;
    }
}
