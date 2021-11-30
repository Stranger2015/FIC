package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

import java.awt.image.AffineTransformOp;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to rotate an image by the given quadrant
 */
public class AffineRotateQuadrantsTransform<M extends Image, C extends CompressedImage> extends AffineTransform<M, C> {

    private final int quadrants;
    private final EInterpolationType interpolationType;

    /**
     * @param quadrants the number of 90 degree arcs to rotate by
     * @param interpolationType  
     */
    public AffineRotateQuadrantsTransform(M image, int quadrants, EInterpolationType interpolationType) {
        super(image);
        this.quadrants = quadrants;
        this.interpolationType = interpolationType;
    }

    public AffineRotateQuadrantsTransform(M image, int quadrants) {
        this(image, quadrants, BILINEAR);
    }

//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        return affineTransform(inputimage, AffineTransform.getQuadrantRotateInstance(
//                quadrants, inputimage.getWidth()  / 2,
//                           inputimage.getHeight() / 2), interpolationType);
//    }
//
    public
    int getQuadrants () {
        return quadrants;
    }

    public
    EInterpolationType getInterpolationType () {
        return interpolationType;
    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType ) {
        return null;
    }
}
