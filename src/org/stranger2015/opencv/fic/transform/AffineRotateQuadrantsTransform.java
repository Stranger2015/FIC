package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to rotate an image by the given quadrant
 */
public class AffineRotateQuadrantsTransform</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer>
        extends AffineTransform< A, G> {

    private final int quadrants;

    /**
     * @param quadrants         the number of 90 degree arcs to rotate by
     * @param interpolationType
     */
    public
    AffineRotateQuadrantsTransform ( IImage<A> image,
                                     int quadrants,
                                     EInterpolationType interpolationType,
                                     IAddress <A> address ) {
        super(image, interpolationType, address );
        this.quadrants = quadrants;
    }

    /**
     * @param image
     * @param quadrants
     */
    public AffineRotateQuadrantsTransform(IImage<A> image, int quadrants, IAddress<A> address) {
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
