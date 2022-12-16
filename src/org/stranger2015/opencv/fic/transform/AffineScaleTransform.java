package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * 
 */
public class AffineScaleTransform
        extends AffineTransform{

    private final double scaleX;
    private final double scaleY;

    /**
     * @param image
     * @param scaleX
     * @param scaleY
     * @param interpolationType
     * @param address
     */
    public AffineScaleTransform(IImage image,
                                double scaleX,
                                double scaleY,
                                EInterpolationType interpolationType,
                                IAddress address ) {
        super(image, interpolationType, address );
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * @param image
     * @param scaleX
     * @param scaleY
     * @param address
     */
    public AffineScaleTransform(IImage image, double scaleX, double scaleY, IAddress address) {
        this(image, scaleX, scaleY, BILINEAR, address);
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
}
