package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to rotate an image by the given quadrant
 */
public class AffineRotateQuadrantsTransform
        extends AffineTransform {

    private final int quadrants;

    /**
     * @param quadrants         the number of 90 degree arcs to rotate by
     * @param interpolationType
     */
    public
    AffineRotateQuadrantsTransform ( IImage image,
                                     int quadrants,
                                     EInterpolationType interpolationType,
                                     IAddress  address ) {
        super(image, interpolationType, address );
        this.quadrants = quadrants;
    }

    /**
     * @param image
     * @param quadrants
     */
    public AffineRotateQuadrantsTransform(IImage image, int quadrants, IAddress address) {
        this(image, quadrants, BILINEAR, address);
    }

    /**
     * @return
     */
    public
    int getQuadrants () {
        return quadrants;
    }
}
