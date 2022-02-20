package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * @param <M>
 */
public abstract
class ShiftTransform<M extends IImage, A extends Address <A>, G extends BitBuffer> extends ImageTransform<M, A, G>{
    private final float intensity;
    private final int channelAxis;

    /**
     * @param image
     * @param intensity
     * @param channelAxis
     * @param address
     */
    protected
    ShiftTransform (M image, float intensity, int channelAxis, Address<A> address) {
        super(image, BILINEAR, address);

        this.intensity = intensity;
        this.channelAxis = channelAxis;
    }

    /**
     * @return
     */
    public
    float getIntensity () {
        return intensity;
    }

    /**
     * @return
     */
    public
    int getChannelAxis () {
        return channelAxis;
    }
}
