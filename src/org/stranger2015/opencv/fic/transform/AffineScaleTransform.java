package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * 
 */
public class AffineScaleTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends AffineTransform<M, A, G>{

    private final double scaleX;
    private final double scaleY;

    /**
     * @param image
     * @param scaleX
     * @param scaleY
     * @param interpolationType
     * @param address
     */
    public AffineScaleTransform(M image, double scaleX, double scaleY, EInterpolationType interpolationType, Address<A> address ) {
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
    public AffineScaleTransform(M image, double scaleX, double scaleY, Address<A> address) {
        this(image, scaleX, scaleY, BILINEAR, address);
    }

    public
    double getScaleX () {
        return scaleX;
    }

    public
    double getScaleY () {
        return scaleY;
    }
}
