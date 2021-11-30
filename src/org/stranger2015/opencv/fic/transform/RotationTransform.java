package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

/**
 * functor class to rotate an image by the given degrees
 */
public
class RotationTransform<M extends Image, C extends CompressedImage> extends PreserveAlphaTransform <M, C> {

    private final double degrees;
    private final double pointX;
    private final double pointY;
//    private final boolean preserveAlpha;

    /**
     * @param degrees
     */
    public
    RotationTransform ( double degrees ) {
        this(degrees, 0, 0, false);
    }

    /**
     * @param degrees
     * @param preserveAlpha
     */
    public
    RotationTransform ( double degrees, boolean preserveAlpha ) {
        this(degrees, 0, 0, preserveAlpha);
    }

    /**
     * @param degrees
     * @param pointX
     * @param pointY
     */
    public
    RotationTransform ( double degrees,
                        double pointX,
                        double pointY ) {

        this(degrees, pointX, pointY, false);
    }

    /**
     * @param degrees       the angle of rotation measured in degrees
     * @param pointX        the x coordinate of the origin of the rotation
     * @param pointY        the y coordinate of the origin of the rotation
     * @param preserveAlpha whether to preserve the alpha channel or not
     */
    public
    RotationTransform ( double degrees,
                        double pointX,
                        double pointY,
                        boolean preserveAlpha ) {

        super(degrees,preserveAlpha);

        this.degrees = degrees;
        this.pointX = pointX;
        this.pointY = pointY;
//        this.preserveAlpha = preserveAlpha;
    }

//    @Override
//    public BufferedImage transform( BufferedImage inputimage) {
//        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
//        BufferedImage rotatedImg = new BufferedImage(inputimage.getWidth(), inputimage.getHeight(), imageType);
//
//        Graphics2D graphics = rotatedImg.createGraphics();
//        if (preserveAlpha) {
//            graphics.setComposite(AlphaComposite.Src);
//        }
//        graphics.rotate(Math.toRadians(degrees), pointX, pointY);
//        graphics.drawImage(inputimage, null, null);
//        graphics.dispose();
//
//        return rotatedImg;
//    }

    public
    double getDegrees () {
        return degrees;
    }

    public
    double getPointX () {
        return pointX;
    }

    public
    double getPointY () {
        return pointY;
    }

//    public
//    boolean isPreserveAlpha () {
//        return preserveAlpha;
//    }

    /**
     * @param inputImage
     * @param transformMatrix
     * @param interpolationType
     * @return
     */
    @Override
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType ) {

        return (M) outputImage;
    }
}
