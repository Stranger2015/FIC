package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * functor class to rotate an image by the given degrees
 */
public
class RotationTransform<M extends IImage<A>, A extends Address <A>, G extends BitBuffer>
        extends PreserveAlphaTransform <M, A, G> {

    private final double degrees;
    private final double pointX;//int
    private final double pointY;//imt

    /**
     * @param degrees
     */
    public
    RotationTransform ( double degrees, Address <A> address ) {
        this(degrees, 0, 0, false, address);
    }

    /**
     * @param degrees
     * @param preserveAlpha
     */
    public
    RotationTransform ( double degrees, boolean preserveAlpha, Address <A> address ) {
        this(degrees, 0, 0, preserveAlpha, address);
    }

    /**
     * @param degrees
     * @param pointX
     * @param pointY
     */
    public
    RotationTransform ( double degrees,
                        double pointX,
                        double pointY,
                        Address <A> address ) {

        this(degrees, pointX, pointY, false, address);
    }

    /**
     * @param degrees       the angle of rotation measured in degrees
     * @param pointX        the x coordinate of the origin of the rotation
     * @param pointY        the y coordinate of the origin of the rotation
     * @param preserveAlpha whether to preserve the alpha channel or not
     */
    public
    RotationTransform ( double degrees,
                        double pointX,
                        double pointY,
                        boolean preserveAlpha,
                        Address <A> address ) {

        super(null, preserveAlpha, address);

        this.degrees = degrees;
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public
    double getDegrees () {
        return degrees;
    }

    public
    double getPointX () {
        return pointX;
    }

    public
    double getPointY () {
        return pointY;
    }
}
