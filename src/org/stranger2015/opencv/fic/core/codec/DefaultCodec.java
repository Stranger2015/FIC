package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.CompressedImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.AffineTransforms;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

import static org.stranger2015.opencv.fic.core.EPartitionScheme.FIXED_SIZE;

public
class DefaultCodec<M extends Image, C extends CompressedImage> implements IEncoder <M, C>, IConstants {
    private final IEncoder <M, C> encoder = Encoder.create(FIXED_SIZE, new EncodeAction(null));
    private final IDecoder <C, M> decoder = new Decoder <>();

    /**
     * @return
     */
    @Override
    public
    M encode (M image) {
        return encoder.encode(image);
    }

    /**
     * @param x
     * @param axis
     * @return
     */
    @Override
    public
    M flipAxis ( M x, int axis ) {
        return encoder.flipAxis(x, axis);
    }

    @Override
    public
    M randomTransform ( M image, ImageTransform <M, C> transform ) {
        return encoder.randomTransform(image, transform);
    }

    @Override
    public
    M applyTransform ( M image, ImageTransform <M, C> transform ) {
        return encoder.applyTransform(image, transform);
    }

    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M, C> transform ) {
        return encoder.applyAffineTransform(image, transform);
    }

    @Override
    public
    List <ImageTransform <M, C>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return encoder.compress(image, sourceSize, destinationSize, step);
    }

    @Override
    public
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return encoder.generateAllTransformedBlocks(image, sourceSize, destinationSize, step);
    }

//    /**
//     * Performs a random rotation of an image
//     *
//     * @param x                  Input tensor. Must be 3D.?????????????
//     * @param rg                 Rotation range, in degrees.
//     * @param rowAxis            Index of axis for rows in the input tensor.
//     * @param colAxis            Index of axis for columns in the input tensor.
//     * @param channelAxis
//     * @param fillMode           Points outside the boundaries of the input are filled according to the given mode
//     * @param cval
//     * @param interpolationOrder
//     * @return
//     */
//    @Override
//    public
//    M randomRotation ( M x,
//                           Range rg,
//                           int rowAxis,
//                           int colAxis,
//                           int channelAxis,
//                           EFillMode fillMode,
//                           double cval,
//                           int interpolationOrder ) {
//        return null;
//    }

//    /**
//     * @param x                  Input image
//     * @param wrg                Width shift range, as a float fraction of the width.
//     * @param hrg                Height shift range, as a float fraction of the height.
//     * @param rowAxis            Index of axis for rows in the input tensor.
//     * @param colAxis            Index of axis for cols in the input tensor.
//     * @param channelAxis        Index of axis for channels in the input tensor.
//     * @param fillMode           Points outside the boundaries of the input
//     *                           are filled according to the given mode
//     *                           (one of ... ).
//     * @param cval
//     * @param interpolationOrder
//     * @return Shifted image tensor.
//     */
//    @Override
//    public
//    M randomShift ( M x,
//                        Range wrg,
//                        Range hrg,
//                        int rowAxis,
//                        int colAxis,
//                        int channelAxis,
//                        EFillMode fillMode,
//                        double cval,
//                        int interpolationOrder ) {
//        return null;
//    }

//    /**
//     * Performs a random spatial shear of an image tensor.
//     *
//     * @param image
//     * @param intensity
//     * @param rowAxis
//     * @param colAxis
//     * @param channelAxis
//     * @param fillMode
//     * @param cval
//     * @return Sheared image tensor.
//     */
//    @Override
//    public
//    M randomShear ( M image,
//                        int intensity,
//                        int rowAxis,
//                        int colAxis,
//                        int channelAxis,
//                        EFillMode fillMode,
//                        double cval ) {
//        return null;
//    }
//
//    /**
//     * @param x
//     * @param zoomRange
//     * @param rowAxis
//     * @param colAxis
//     * @param channelAxis
//     * @param fillMode
//     * @param cval
//     * @return Zoomed image tensor.//////
//     */
//    @Override
//    public
//    M randomZoom ( M x,
//                   Mat.Tuple2 <Float> zoomRange,
//                   int rowAxis,
//                   int colAxis,
//                   int channelAxis,
//                   EFillMode fillMode,
//                   double cval ) {
//        return null;
//    }
//
//    @Override
//    public
//    M randomChannelShift ( M x, int intensity, int channelAxis ) {
//        return null;
//    }
//
//    /**
//     * @param image       Input image.
//     * @param theta       Rotation angle, in degrees.
//     * @param tx          Width shift.
//     * @param ty          Height shift.
//     * @param shear       Shear angle, in degrees.
//     * @param zx          Zoom in x direction.
//     * @param zy          Zoom in y direction
//     * @param rowAxis     Index of axis for rows in the input image.
//     * @param colAxis     Index of axis for columns in the input image.
//     * @param channelAxis Index of axis for channels in the input image.
//     * @param fillMode    Points outside the boundaries of the input are filled according to the given mode
//     *                    (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
//     * @param cval        Value used for points outside the boundaries of the input if `mode='constant'`.
//     * @param order       Order
//     * @return Value used for points outside the boundaries of the input if `mode='constant'`.
//     */
//    @Override
//    public
//    M applyAffineTransform ( M image,
//                             double theta,
//                             double tx,
//                             double ty,
//                             double shear,
//                             double zx,
//                             double zy,
//                             int rowAxis,
//                             int colAxis,
//                             int channelAxis,
//                             EFillMode fillMode,
//                             double cval,
//                             int order ) {
//        return null;
//    }
//
//    /**
//     * @param matrix
//     * @param x
//     * @param y
//     * @return
//     */
//    @Override
//    public
//    M transformMatrixOffsetCenter ( M matrix, int x, int y ) {
//
//        return matrix;
//    }

//    /**
//     * Performs a brightness shift.
//     *
//     * @param image
//     * @param brightness
//     * @return image tensor.
//     * @throws ValueError if `brightness_range` isn't a tuple.
//     */
//    @Override
//    public
//    M applyBrightnessShift ( M image, Range brightness ) {
//        return null;
//    }
//
//    /**
//     * Performs a random brightness shift.
//     *
//     * @param image
//     * @param brightnessRange
//     * @return
//     */
//    @Override
//    public
//    M randomBrightness ( M image, int brightnessRange ) {
//        return image;
//    }

//    /**
//     * Performs a brightness shift.
//     *
//     * @param x
//     * @param brightness
//     * @return Numpy image tensor.
//     * @throws ValueError if `brightness_range` isn't a tuple.
//     */
//    @Override
//    public
//    M applyBrightnessShift ( M x, Mat.Tuple2 <Float> brightness ) {
//        return x;
//    }

//    /**
//     * Performs a random brightness shift.
//     *
//     * @param x
//     * @param brightnessRange
//     * @return
//     */
//    @Override
//    public
//    M randomBrightness ( M x, Range brightnessRange ) {
//        return x;
//    }
}
