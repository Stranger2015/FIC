package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <A>
 * @param <G>
 */
public abstract
class AffineTransform< A extends IAddress <A>, G extends BitBuffer>
        extends ImageTransform <A, G> {

    private final EInterpolationType type;

    /**
     * @param image
     */
    protected
    AffineTransform ( IImage <A> image, EInterpolationType type , IAddress<A> address) {
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
