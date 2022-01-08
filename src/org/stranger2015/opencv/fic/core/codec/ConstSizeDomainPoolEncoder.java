package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.Size;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 * @param <N>
 * @param <M>
 * @param <A>
 */
public
class ConstSizeDomainPoolEncoder<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends Encoder <N, A, M> {
    /**
     * @param inputImage
     * @param rangeSize
     * @param domainSize
     */
    public
    ConstSizeDomainPoolEncoder ( M inputImage, Size rangeSize, Size domainSize ) {
        super(inputImage, rangeSize, domainSize);
    }

    /**
     * @return
     */
    @Override
    public
    M encode ( M image ) {
        return null;//todo
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    M randomTransform ( M image, ImageTransform <M> transform ) {
        return null;
    }


    @Override
    public
    M applyTransform ( M image, ImageTransform <M> transform ) {
        return null;//todo
    }

    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M> transform ) {
        return null;//todo
    }

    @Override
    public
    List <ImageTransform <M>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
    }

    @Override
    public
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return null;//todo
    }

    /**
     *
     */
    @Override
    public
    void onPreprocess () {

    }

    /**
     *
     */
    @Override
    public
    void onProcess () {

    }

    /**
     *
     */
    @Override
    public
    void onPostprocess () {

    }
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
//    Image randomRotation ( Image x, Range rg, int rowAxis, int colAxis, int channelAxis, EFillMode fillMode, double cval, int interpolationOrder ) {
//        return null;//todo
//    }
//
//    /**
//     * @param x                  Input image
//     * @param wrg                Width shift range, as a float fraction of the width.
//     * @param hrg                Height shift range, as a float fraction of the height.
//     * @param rowAxis            Index of axis for rows in the input tensor.
//     * @param colAxis            Index of axis for cols in the input tensor.
//     * @param channelAxis        Index of axis for channels in the input tensor.
//     * @param fillMode           Points outside the boundaries of the input
//     *                           are filled according to the given mode
//     *                           (one of CONSTANT, NEAREST, REFLECT, WRAP ).
//     * @param cval
//     * @param interpolationOrder
//     * @return Shifted image tensor.
//     */
//    @Override
//    public
//    Image randomShift ( Image x, Range wrg, Range hrg, int rowAxis, int colAxis, int channelAxis, EFillMode fillMode, double cval, int interpolationOrder ) {
//        return null;//todo
//    }
//
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
//    Image randomShear ( Image image, int intensity, int rowAxis, int colAxis, int channelAxis, EFillMode fillMode, double cval ) {
//        return null;//todo
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
//    Image randomZoom ( Image x, Mat.Tuple2 <Float> zoomRange, int rowAxis, int colAxis, int channelAxis, EFillMode fillMode, double cval ) {
//        return null;//todo
//    }
//
//    @Override
//    public
//    Image randomChannelShift ( Image x, int intensity, int channelAxis ) {
//        return null;//todo
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
//    Image applyAffineTransform ( Image image, double theta, double tx, double ty, double shear, double zx, double zy, int rowAxis, int colAxis, int channelAxis, EFillMode fillMode, double cval, int order ) {
//        return null;//todo
//    }

//    /**
//     * @param matrix
//     * @param x
//     * @param y
//     * @return
//     */
//    @Override
//    public
//    Image transformMatrixOffsetCenter ( Image matrix, int x, int y ) {
//        return null;//todo
//    }
//
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
//    Image applyBrightnessShift ( Image image, Range brightness ) {
//        return null;//todo
//    }
//}
