package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;

/**
 * @param <M>
 * @param <A>
 * @param <G>
 */
public
class ChannelShift<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends ShiftTransform<M, A, G>{
    /**
     * @param image
     * @param intensity
     * @param channelAxis
     * @param address
     */
    protected
    ChannelShift ( M image, float intensity, int channelAxis, Address<A> address ) {
        super(image, intensity, channelAxis, address);
    }
}
