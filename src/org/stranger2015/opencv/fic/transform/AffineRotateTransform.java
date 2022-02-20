package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to rotate an image by the given degrees
 */
public class AffineRotateTransform<M extends IImage, A extends Address <A>, G extends BitBuffer> extends AffineTransform<M, A, G> {

    private final double degrees;

    /**
     * @param degrees
     * @param interpolationType
     */
    public AffineRotateTransform(M image, double degrees, EInterpolationType interpolationType, Address<A> address) {
        super(image, interpolationType, address );
        this.degrees = degrees;
    }

    /**
     * @param degrees
     */
    public AffineRotateTransform(M image , double degrees, Address<A> address) {
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
//    M warpAffine ( M src, M transformMatrix, EInterpolationType interpolationType, Address<A> address ) {
//        // Creating a Point object
//        Point point = new Point(300, 200);//todo
//
//        // Creating the transformation matrix M
//        M rotationMatrix = (M) Imgproc.getRotationMatrix2D(point, getDegrees(), 1);
//
//        // Creating the object of the class Size
//        Size size = new Size(src.cols(), src.cols());
//        M out = (M) new Image(src);
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
