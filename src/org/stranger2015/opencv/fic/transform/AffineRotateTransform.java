package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

import static java.awt.image.AffineTransformOp.*;
import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * functor class to rotate an image by the given degrees
 */
public class AffineRotateTransform<M extends Image> extends AffineTransform<M> {

    private final double degrees;

    /**
     * @param degrees
     * @param interpolationType
     */
    public AffineRotateTransform(M image, double degrees, EInterpolationType interpolationType) {
        super(image, interpolationType);
        this.degrees = degrees;
    }

    /**
     * @param degrees
     */
    public AffineRotateTransform(M image , double degrees) {
        this(image, degrees, BILINEAR);
    }

    /**
     * @param src
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    M transform ( M src, M transformMatrix, EInterpolationType interpolationType ) {
        // Creating a AddressedPoint object
        Point point = new Point(300, 200);//todo

        // Creating the transformation matrix M
        M rotationMatrix = (M) Imgproc.getRotationMatrix2D(point, getDegrees(), 1);

        // Creating the object of the class Size
        Size size = new Size(src.cols(), src.cols());
        M out = (M) new Image();

        // Rotating the given image
        Imgproc.warpAffine(src, out, rotationMatrix, size);

        return out;
    }

    public
    double getDegrees () {
        return degrees;
    }

//    public BufferedImage transform(final BufferedImage inputimage) {
//        return affineTransform(inputimage, AffineTransform.getRotateInstance(
//                Math.toRadians(degrees), inputimage.getWidth()  / 2,
//                                         inputimage.getHeight() / 2), interpolationType);
//    }

}
