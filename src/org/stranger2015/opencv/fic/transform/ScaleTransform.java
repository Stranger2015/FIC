package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;


import static java.lang.String.format;

/**
 * functor class to affineScale an image
 */
public
class ScaleTransform<M extends Image, C extends CompressedImage> extends PreserveAlphaTransform<M, C> {

//    private final boolean preserveAlpha;
    private final double scaleX;
    private final double scaleY;

    public
    ScaleTransform ( M image, double scaleX, double scaleY ) {
        this(image, scaleX, scaleY, false);
    }

    /**
     * @param scaleX        the factor by which coordinates are scaled along the X axis direction
     * @param scaleY        the factor by which coordinates are scaled along the Y axis direction
     * @param preserveAlpha whether to preserve the alpha channel or not
     */
    public
    ScaleTransform ( M image, double scaleX, double scaleY, boolean preserveAlpha ) {
        super(image, preserveAlpha);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
//        this.preserveAlpha = preserveAlpha;
    }

//    @Override
//    public BufferedImage transform(final BufferedImage inputimage) {
//        int imageType    = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
//        int scaledWidth  = (int) (inputimage.getWidth() * scaleX);
//        int scaledHeight = (int) (inputimage.getHeight() * scaleY);
//        BufferedImage scaledImg = new BufferedImage(scaledWidth, scaledHeight, imageType);
//
//        Graphics2D graphics = scaledImg.createGraphics();
//        if (preserveAlpha) {
//            graphics.setComposite(AlphaComposite.Src);
//        }
//        graphics.drawImage(inputimage, 0, 0, scaledWidth, scaledHeight, null);
//        graphics.dispose();
//
//        return scaledImg;
//    }

    public
    double getScaleX () {
        return scaleX;
    }

    public
    double getScaleY () {
        return scaleY;
    }

    @Override
    public
    String toString () {
        return format("%.2f:%.2f", scaleX, scaleY);
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
