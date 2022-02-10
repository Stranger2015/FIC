package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.stranger2015.opencv.fic.core.Image;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * 
 */
public class AffineScaleTransform<M extends IImage> extends AffineTransform<M>{

    private final double scaleX;
    private final double scaleY;

    public AffineScaleTransform(M image, double scaleX, double scaleY, EInterpolationType interpolationType) {
        super(image, interpolationType);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public AffineScaleTransform(M image, double scaleX, double scaleY) {
        this(image, scaleX, scaleY, BILINEAR);
    }

    /**
     * @param src
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    M transform ( M src, M transformMatrix, EInterpolationType interpolationType ) {
        // Creating the Size object
        Size size = new Size(src.rows()*2, src.rows()*2);

        M out= (M) new Image(image);
        // Scaling the Image
        Imgproc.resize(src, out, size, 0, 0, Imgproc.INTER_AREA);

        // Writing the image
//        Imgcodecs.imwrite("E:/OpenCV/chap24/scale_output.jpg", dst);

        System.out.println("Image Processed");
        return out;
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
