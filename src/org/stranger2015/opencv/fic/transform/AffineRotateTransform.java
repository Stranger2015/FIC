package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to rotate an image by the given degrees
 */
public class AffineRotateTransform
        extends AffineTransform {

    private final double degrees;

    /**
     * @param degrees
     * @param interpolationType
     */
    public AffineRotateTransform( IImage image,
                                  double degrees,
                                  EInterpolationType interpolationType,
                                  IAddress address) {

        super(image, interpolationType, address );
        this.degrees = degrees;
    }

    /**
     * @param degrees
     */
    public AffineRotateTransform(IImage image , double degrees, IAddress address) {
        this(image, degrees, BILINEAR, address);
    }

//    /**
//     * @param src
//     * @param transformMatrix
//     * @param interpolationType
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    @Override
//    public
//    IImage warpAffine ( IImage src, IImage transformMatrix, EInterpolationType interpolationType, Address address ) {
//        // Creating a Point object
//        Point point = new Point(300, 200);//todo
//
//        // Creating the transformation matrix M
//        IImage rotationMatrix = (M) Imgproc.getRotationMatrix2D(point, getDegrees(), 1);
//
//        // Creating the object of the class Size
//        Size size = new Size(src.cols(), src.cols());
//        IImage out = (M) new Image(src);
//
//        // Rotating the given image
//        Imgproc.warpAffine((Mat)src, (Mat)out, (Mat)rotationMatrix, size);
//
//        return out;
//    }

    public
    double getDegrees () {
        return degrees;
    }
}
