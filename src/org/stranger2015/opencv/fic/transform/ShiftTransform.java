package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 
 */
public abstract
class ShiftTransform
        extends ImageTransform{

    private final float intensity;
    private final int channelAxis;

    /**
     * @param image
     * @param intensity
     * @param channelAxis
     * @param address
     */
    protected
    ShiftTransform ( IImage image, float intensity, int channelAxis, IAddress  address) {
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
