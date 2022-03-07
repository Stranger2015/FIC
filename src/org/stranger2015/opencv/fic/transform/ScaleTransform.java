package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.utils.BitBuffer;

import static java.lang.String.format;

/**
 * functor class to affineScale an image
 */
public
class ScaleTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends PreserveAlphaTransform<M, A, G> {

    private final double scaleX;
    private final double scaleY;

    /**
     * @param image
     * @param scaleX
     * @param scaleY
     */
    public
    ScaleTransform ( M image, double scaleX, double scaleY, Address<A> address ) {
        this(image, scaleX, scaleY, false, address);
    }

    /**
     * @param scaleX        the factor by which coordinates are scaled along the X axis direction
     * @param scaleY        the factor by which coordinates are scaled along the Y axis direction
     * @param preserveAlpha whether to preserve the alpha channel or not
     */
    public
    ScaleTransform ( M image, double scaleX, double scaleY, boolean preserveAlpha, Address<A> address ) {
        super(image, preserveAlpha, address);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public
    double getScaleX () {
        return scaleX;
    }

    public
    double getScaleY () {
        return scaleY;
    }

    @Override
    public
    String toString () {
        return format("%.2f:%.2f", scaleX, scaleY);
    }
}
