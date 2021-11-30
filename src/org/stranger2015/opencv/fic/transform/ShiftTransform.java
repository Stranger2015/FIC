package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

/**
 * @param <M>
 * @param <C>
 */
public abstract
class ShiftTransform<M extends Image, C extends CompressedImage> extends ImageTransform<M,C>{
    private final float intensity;
    private final int channelAxis;

    protected
    ShiftTransform (M image, float intensity, int channelAxis) {
        super(image);

        this.intensity = intensity;
        this.channelAxis = channelAxis;
    }

    public
    ShiftTransform ( M image, float intensity ) {
        this(image,intensity,-1);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public abstract
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType );

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
