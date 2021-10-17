package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * functor class to affineFlip an image
 */
public class FlipTransform<T extends Mat> extends ImageTransform<T> {

    private final boolean preserveAlpha;

    public FlipTransform(final boolean preserveAlpha) {
        this.preserveAlpha = preserveAlpha;
    }

    public FlipTransform() {
        this(false);
    }

    /**
     * @param inputimage
     * @param outputimage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    void transform ( T inputimage, T outputimage, T transformMatrix, int interpolationType ) {

    }

//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
//        BufferedImage flippedImg = new BufferedImage(inputimage.getWidth(),
//                                                     inputimage.getHeight(), imageType);
//
//        Graphics2D graphics = flippedImg.createGraphics();
//        if (preserveAlpha) {
//            graphics.setComposite(AlphaComposite.Src);
//        }
//        graphics.drawImage(inputimage,
//                           inputimage.getWidth(), 0, 0, inputimage.getHeight(),
//                           0, 0, inputimage.getWidth(), inputimage.getHeight(),
//                           null);
//        graphics.dispose();
//
//        return flippedImg;
//    }
}
