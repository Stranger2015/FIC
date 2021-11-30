package org.stranger2015.opencv.fic.transform;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;

public abstract
class AffineTransform<M extends Image, C extends CompressedImage> extends ImageTransform<M,C>{

    private final EInterpolationType type;

    /**
     * @param image
     */
    protected
    AffineTransform ( M image, EInterpolationType type) {
        super(image);
        this.type = type;
    }

    /**
     * The Affine transform theory:
     * <p>
     * [ x']   [  m00  m01  m02  ] [ x ]   [ m00x + m01y + m02 ]
     * [ y'] = [  m10  m11  m12  ] [ y ] = [ m10x + m11y + m12 ]
     * [ 1 ]   [   0    0    1   ] [ 1 ]   [         1         ]
     * <p>
     * The corresponding matrix values:
     * <p>
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
    public
    M transform ( M inputImage, M transformMatrix, EInterpolationType interpolationType ) {
        return null;
    }

    public
    EInterpolationType getType () {
        return type;
    }
}
