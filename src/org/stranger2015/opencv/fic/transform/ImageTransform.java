package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

import java.io.Serializable;

/**
 * Produces transformed copies of a given input image.
 */
public abstract
class ImageTransform<M extends Image> implements ITransform <M>, Serializable {

    protected final M outputImage;

    public int dihedralAffineTransformerIndex;
//    public double contrastScale;
//    public int brightnessOffset;
    public int originalDomainX;
    public int originalDomainY;

    /**
     * @param image
     */
    @SuppressWarnings("unchecked")
    protected
    ImageTransform (M image) {
        dihedralAffineTransformerIndex = -1;
//        contrastScale = 0;
//        brightnessOffset = 0;
        originalDomainX = 0;
        originalDomainY = 0;
        outputImage = (M) new CompressedImage(image);
    }

    public static
    <M extends Image>
    ImageTransform <M> create (M image) {
        return new NoneTransform <>(image);
    }

    /**
     * @return
     */
    public
    M getOutputImage () {
        return outputImage;
    }

    /**
     * The Affine transform theory:
     *
     * [ x']   [  m00  m01  m02  ] [ x ]   [ m00x + m01y + m02 ]
     * [ y'] = [  m10  m11  m12  ] [ y ] = [ m10x + m11y + m12 ]
     * [ 1 ]   [   0    0    1   ] [ 1 ]   [         1         ]
     *
     * The corresponding matrix values:
     *
     * [  m00  m01  m02  ]     [ scx  shx  trx ]
     * [  m10  m11  m12  ] <=> [ shy  scy  try ]
     * [   0    0    1   ]     [  0   0     1  ]
     *
     * @param inputImage        the original image to apply the transformation to
     * @param transformMatrix   the affine transformation operation-matrix
     * @param interpolationType the interpolation type to use - one of
     *                          EInterpolationType.{NEAREST_NEIGHBOR ,BILINEAR, BICUBIC}
     * @return the transformed image
     */
    @Override
    public abstract
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType );
}
