package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.codec.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;

/**
 * @param <M>
 * @param <A>
 * @param <G>
 */
public abstract
class AffineTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends ImageTransform <M, A, G> {//fixme

    private final EInterpolationType type;

    /**
     * @param image
     */
    protected
    AffineTransform ( M image, EInterpolationType type , IAddress<A> address) {
        super(image, type, address);

        this.type = type;
    }

    /**
     * @return
     */
    public
    EInterpolationType getType () {
        return type;
    }
}
