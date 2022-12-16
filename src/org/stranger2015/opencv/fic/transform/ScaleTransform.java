package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

import static java.lang.String.format;

/**
 * functor class to affineScale an image
 */
public
class ScaleTransform
        extends PreserveAlphaTransform {

    private final double scaleX;
    private final double scaleY;

    /**
     * @param image
     * @param scaleX
     * @param scaleY
     * @param address
     */
    public
    ScaleTransform ( IImage image, double scaleX, double scaleY, IAddress address ) {
        this(image, scaleX, scaleY, false, address);
    }

    /**
     * @param scaleX        the factor by which coordinates are scaled along the X axis direction
     * @param scaleY        the factor by which coordinates are scaled along the Y axis direction
     * @param preserveAlpha whether to preserve the alpha channel or not
     * @param address
     */
    public
    ScaleTransform ( IImage image, double scaleX, double scaleY, boolean preserveAlpha, IAddress  address ) {
        super(image, preserveAlpha, address);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * @return
     */
    public
    double getScaleX () {
        return scaleX;
    }

    /**
     * @return
     */
    public
    double getScaleY () {
        return scaleY;
    }

    /**
     * @return
     */
    @Override
    public
    String toString () {
        return format("%.2f:%.2f", scaleX, scaleY);
    }
}
