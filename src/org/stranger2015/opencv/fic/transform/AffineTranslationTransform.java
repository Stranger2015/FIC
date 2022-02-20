package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <M>
 */
public
class AffineTranslationTransform<M extends IImage, A extends Address <A>, G extends BitBuffer>
        extends AffineTransform <M, A, G> {
    /**
     *
     */
    public
    AffineTranslationTransform ( M image, Address <A> address ) {
        super(image, EInterpolationType.BILINEAR, address);
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
//    M warpAffine ( M src, M transformMatrix, EInterpolationType interpolationType, Address <A> address ) {
//
//        //Creating an empty matrix to store the result
////                Mat dst = new Mat();
////
//        Point p1 = new Point(0, 0);
//        Point p2 = new Point(src.cols() - 1, 0);
//        Point p3 = new Point(0, src.rows() - 1);
//        Point p4 = new Point(src.cols() * 0.0, src.rows() * 0.33);
//        Point p5 = new Point(src.cols() * 0.85, src.rows() * 0.25);
//        Point p6 = new Point(src.cols() * 0.15, src.rows() * 0.7);
//
//        MatOfPoint2f ma1 = new MatOfPoint2f(p1, p2, p3);
//        MatOfPoint2f ma2 = new MatOfPoint2f(p4, p5, p6);
//
//        // Creating the transformation matrix
//        M transformMatrix1 = (M) getAffineTransform(ma1, ma2);
//
//        // Creating object of the class Size
//        Size size = new Size(src.cols(), src.cols());
//        M out = (M) new Image(src);
//        // Applying Wrap Affine
//        Imgproc.warpAffine((Mat) src, (Mat) out, (Mat) transformMatrix1, size);
//
//        // Writing the image
////        Imgcodecs.imwrite("E:/OpenCV/chap24/Affinetranslate.jpg", dst);
//
//        System.out.println("Image processed");
//
//        return out;
//    }
}
