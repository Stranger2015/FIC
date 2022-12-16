package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 
 * @param 
 * @param <G>
 */
public
class BrightnessShift extends ShiftTransform  {
    /**
     * @param image
     * @param brightness
     */
    public
    BrightnessShift ( IImage image, float brightness, IAddress  address) {
        super(image, brightness,-1, address);//foxme
    }
}
