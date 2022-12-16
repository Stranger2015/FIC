package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * @param
 * @param <G>
 */
public
class DctTransform extends ImageTransform {

    /**
     * @param image
     */
    protected
    DctTransform ( IImage image, IAddress address ) {
        super(image, BILINEAR, address);
    }
}
