package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;

import static org.stranger2015.opencv.fic.transform.EInterpolationType.BILINEAR;

/**
 * 
 */
public class AffineFlipTransform<M extends IImage> extends AffineTransform <M> {


    public AffineFlipTransform(M image, EInterpolationType interpolationType) {super(image, interpolationType);
    }

    public AffineFlipTransform(M image) {
        this(image, BILINEAR);
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

//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        AffineTransform transform = new AffineTransform();
//        transform.translate(inputimage.getWidth()  / 2, inputimage.getHeight()  / 2);
//        transform.scale(1, -1);
//        transform.translate(-inputimage.getWidth() / 2, -inputimage.getHeight() / 2);
//        return affineTransform(inputimage, transform, interpolationType);
//    }
}
