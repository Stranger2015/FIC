package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to rotate an image by the given quadrant
 */
public class AffineRotateQuadrantsTransform<M extends IImage, A extends Address <A>, G extends BitBuffer>
        extends AffineTransform<M, A, G> {

    private final int quadrants;

    /**
     * @param quadrants         the number of 90 degree arcs to rotate by
     * @param interpolationType
     */
    public
    AffineRotateQuadrantsTransform ( M image, int quadrants, EInterpolationType interpolationType, Address<A> address ) {
        super(image, interpolationType, address );
        this.quadrants = quadrants;
    }

    /**
     * @param image
     * @param quadrants
     */
    public AffineRotateQuadrantsTransform(M image, int quadrants, Address<A> address) {
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
