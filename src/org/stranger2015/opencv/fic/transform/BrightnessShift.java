package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;

/**
 * @param <M>
 * @param <A>
 * @param <G>
 */
public
class BrightnessShift<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends ShiftTransform <M, A, G> {
    /**
     * @param image
     * @param brightness
     */
    public
    BrightnessShift ( M image, float brightness, Address <A> address) {
        super(image, brightness, address);
    }
}
