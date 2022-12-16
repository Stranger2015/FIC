package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

/**
 * @param
 * @param <G>
 */
public
class ChannelShift extends ShiftTransform {

    /**
     * @param image
     * @param intensity
     * @param channelAxis
     * @param address
     */
    protected
    ChannelShift ( IImage image, float intensity, int channelAxis, IAddress address ) {
        super(image, intensity, channelAxis, address);
    }
}
