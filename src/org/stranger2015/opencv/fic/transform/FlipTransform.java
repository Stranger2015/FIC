package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

/**
 * functor class to affineFlip an image
 */
public
class FlipTransform<M extends Image, C extends CompressedImage> extends PreserveAlphaTransform <M, C> {

    /**
     * @param preserveAlpha
     */
    public
    FlipTransform ( M image, boolean preserveAlpha ) {
        super(image, preserveAlpha);
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
        M out = (M) new Image();
        return out;
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
