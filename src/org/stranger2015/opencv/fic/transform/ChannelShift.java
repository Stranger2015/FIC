package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

public
class ChannelShift<M extends Image, C extends CompressedImage> extends ShiftTransform<M,C>{
    protected
    ChannelShift ( M image, float intensity, int channelAxis ) {
        super(image, intensity, channelAxis);
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType ) {
        return null;
    }
}
