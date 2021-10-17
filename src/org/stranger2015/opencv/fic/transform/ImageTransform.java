package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Mat;

import java.io.Serializable;

/**
 * Produces transformed copies of a given input image.
 */
public abstract
class ImageTransform<T extends Mat> implements ITransformer <T>, Serializable {

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
     * @param inputimage        the original image to apply the transformation to
     * @param outputimage       the original image to apply the transformation to
     * @param transformMatrix   the affine transformation operation-matrix
     * @param interpolationType the interpolation type to use - one of
     *                          AffineTransformOp.{TYPE_NEAREST_NEIGHBOR ,TYPE_BILINEAR, TYPE_BICUBIC}
     * @return the transformed image
     */
    public
    void affineTransform ( final T inputimage,
                           final T outputimage,
                           //                   final AffineTransform transform,
                           final Mat transformMatrix,
                           final int interpolationType ) {
//        (Imgproc.warpAffine(inputimage, outputimage, dsize,);
    }
}
