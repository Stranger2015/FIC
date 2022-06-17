package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public
class BrightnessShift</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends ShiftTransform <A, G> {
    /**
     * @param image
     * @param brightness
     */
    public
    BrightnessShift ( M image, float brightness, IAddress <A> address) {
        super(image, brightness,-1, address);//foxme
    }
}
