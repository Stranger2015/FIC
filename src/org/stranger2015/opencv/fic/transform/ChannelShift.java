package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 * @param <A>
 * @param <G>
 */
public
class ChannelShift</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends ShiftTransform<A, G>{

    /**
     * @param image
     * @param intensity
     * @param channelAxis
     * @param address
     */
    protected
    ChannelShift ( GrayScaleImage<A> image, float intensity, int channelAxis, IAddress<A> address ) {
        super(image, intensity, channelAxis, address);
    }
}
