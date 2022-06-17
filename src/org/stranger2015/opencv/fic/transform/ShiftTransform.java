package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 
 */
public abstract
class ShiftTransform</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform<A, G>{

    private final float intensity;
    private final int channelAxis;

    /**
     * @param image
     * @param intensity
     * @param channelAxis
     * @param address
     */
    protected
    ShiftTransform ( GrayScaleImage<A> image, float intensity, int channelAxis, IAddress <A> address) {
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
