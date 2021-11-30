package org.stranger2015.opencv.fic.core.codec;

import org.opencv.core.*;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.AffineTransforms;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public
class Codec<M extends Image, C extends CompressedImage> implements IEncoder <M, C>, IDecoder <C, M> {
//    private final IEncoder <M, C> encoder = new Encoder <>();
//    private final IDecoder <C, M> decoder = new Decoder <>();

    public static
    <M extends Image, C extends CompressedImage>
    Codec<M, C>    create( EPartitionScheme scheme,  EncodeAction action){

        return null;
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    M encode ( M image ) {
        return null;
    }

    /**
     * @param x
     * @param axis
     * @return
     */
    @Override
    public
    M flipAxis ( M x, int axis ) {
        return null;
    }

    @Override
    public
    M randomTransform ( M image, ImageTransform <M, C> transform ) {
        return null;
    }

    @Override
    public
    M applyTransform ( M image, ImageTransform <M, C> transform ) {
        return null;
    }

    @Override
    public
    M applyAffineTransform ( M image, AffineTransform <M, C> transform ) {
        return null;
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
//                       Range rg,
//                       int rowAxis,
//                       int colAxis,
//                       int channelAxis,
//                       EFillMode fillMode,
//                       double cval,
//                       int interpolationOrder ) {
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
//     *                           (one of CONSTANT, NEAREST, REFLECT, WRAP ).
//     * @param cval
//     * @param interpolationOrder
//     * @return Shifted image tensor.
//     */
//    @Override
//    public
//    M randomShift ( M x,
//                    Range wrg,
//                    Range hrg,
//                    int rowAxis,
//                    int colAxis,
//                    int channelAxis,
//                    EFillMode fillMode,
//                    double cval,
//                    int interpolationOrder ) {
//
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
//                    int intensity,
//                    int rowAxis,
//                    int colAxis,
//                    int channelAxis,
//                    EFillMode fillMode,
//                    double cval ) {
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
//        return x;
//    }
@Override
@SuppressWarnings("unchecked")
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
//
//        M transformMatrix = null;
//        /*new Image(
//                1, 0, 0,
//                0, 1, 0,
//                0, 0, 1);*/
//        if (theta != 0) {
//            theta = toRadians(theta);
//            transformMatrix = (M) new Image(
//                    cos(theta), -sin(theta), 0,
//                    sin(theta), cos(theta), 0,
//                    0, 0, 1
//            );
//        }
//        if (tx != 0 || ty != 0) {
//            M shiftMatrix = (M) new Image(
//                    1, 0, tx,
//                    0, 1, ty,
//                    0, 0, 1
//            );
//            transformMatrix = transformMatrix == null ?
//                    shiftMatrix :
//                    dot(transformMatrix, shiftMatrix);
//            if (shear != 0) {
//                shear = toRadians(shear);
//                M shearMatrix = (M) new Image(
//                        1, -sin(shear), 0,
//                        0, cos(shear), 0,
//                        0, 0, 1);
//                transformMatrix = transformMatrix == null ?
//                        shearMatrix :
//                        dot(transformMatrix, shearMatrix);
//                if (zx != 1 || zy != 1) {
//                    M zoomMatrix = (M) new Image(
//                            zx, 0, 0,
//                            0, zy, 0,
//                            0, 0, 1);
//
//                    if (transformMatrix == null) {
//                        transformMatrix = zoomMatrix;
//                    }
//                    else {
//                        transformMatrix = dot(transformMatrix, zoomMatrix);
//
//                        if (transformMatrix != null) {
////        int h=x.
////                            int w = x.shape[rowAxis], x.shape[colAxis]
////        transformMatrix = transformMatrixOffsetCenter(
//                transformMatrix, h, w)
//        x = np.rollAxis(x, channelAxis, 0)
//        finalAffineMatrix = transformMatrix[:2, :2]
//        finalOffset = transformMatrix[:2, 2]
//
//        channelImages = [ndimage.interpolation.affineTransform(
//                xChannel,
//                finalAffineMatrix,
//                finalOffset,
//                order=order,
//                mode=fillMode,
//                cval) for //                xChannel, in x]
//        x = np.stack(channelImages, axis)
//        x = np.rollAxis(x, 0, channelAxis + 1)
//
//                        }
//                    }
//                }
//            }
//        }
//
//        return image;
//    }
//
//    /**
//     * @param mat1
//     * @param mat2
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static
//    <M extends Image/*, C extends CompressedImage*/>
//    M dot ( M mat1, M mat2 ) {
//        return (M) new Image(mat1.dot(mat2));
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public
//    M transformMatrixOffsetCenter ( M matrix, int x, int y ) {
//        final double X = x / 2.0 + 0.5;
//        final double Y = y / 2.0 + 0.5;
//
//        M offsetMatrix = (M) new Image(
//                1, 0, X,
//                0, 1, Y,
//                0, 0, 1);
//        M resetMatrix = (M) new Image(
//                1, 0, -X,
//                0, 1, -Y,
//                0, 0, 1
//        );
//
//        return dot(dot(offsetMatrix, matrix), resetMatrix);
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
//    M randomBrightness ( M image, Range brightnessRange ) {
//        return null;
//    }

//    public
//    int applyTransform ( M image, int direction, int angle, double contrast/*=1.0*/, double brightness/*=0.0*/ ) {
//        return (int) (contrast * rotate(flip(image, direction), angle) + brightness);
//    }

//    private
//    double rotate ( M image, int angle ) {
////        return Imgproc.rotatedRectangleIntersection().rotate(img, angle, reshape=False)
//        return 0;
//    }
//
//    private
//    M flip ( M image, int direction ) {
//        return direction == -1 ? mirror(image) : image;
//    }
//
//    private
//    M mirror ( M image ) {
//        return null;
//    }
//
//    public
//    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
//        List <ImageBlock> transformedBlocks = new ArrayList <>();
//        for (int k : new int[0]) {//fixme  k in range((img.shape[0] - sourceSize) // step + 1):
//
//        }
//  generate_all_transformed_blocks(img, source_size, destination_size, step):
//    factor = source_size // destination_size
//    transformed_blocks = []
//    for k in range((img.shape[0] - source_size) // step + 1):
//        for l in range((img.shape[1] - source_size) // step + 1):
//            # Extract the source block and reduce it to the shape of a destination block
//            S = reduce(image[k*step:k*step+source_size,l*step:l*step+source_size], factor)
//            # Generate all possible transformed blocks
//            for direction, angle in candidates:
//                transformed_blocks.append((k, l, direction, angle, apply_transform(S, direction, angle)))
//    return transformed_blocks

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    public
    List <ImageTransform <M, C>> compress ( M image, int sourceSize, int destinationSize, int step ) {
        List <ImageTransform <M, C>> transformations = new ArrayList <>();
        List <ImageBlock> transformedBlocks = generateAllTransformedBlocks(image, sourceSize, destinationSize, step);
//    for (int i : range(image.shape[0])){ // destination_size):
//            transformations.append([]);
//        for(int j : range(image.shape[1] )){// destination_size):
//            System.out.printf("%d, %d",i, j);
//    transformations.get(i).add(None);
//        }
        float minD = Float.NEGATIVE_INFINITY;
//            # Extract the destination block
//    D = image.get[i*destinationSize,(i+1)*destinationSize,j*destinationSize:(j+1)*destinationSize]
//            # Test all possible transformations and take the best one
//            for( k, l, direction, angle, S in transformed_blocks:
//    contrast, brightness = findContrastAndBrightness2(D, S)){
//            S = contrast * S + brightness
//            d = np.sum(np.square(D - S));
//            if d <min_d:
//            min_d = d;
//            transformations[i][j] = (k, l, direction, angle, contrast, brightness)
//        }
        return transformations;
    }

    @Override
    public
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step ) {
        return null;
    }

//    int[] findContrastAndBrightness2 ( int D, int S ) {
//        return new int[]{D, S};
//    }
//            # Fit the contrast and the brightness
//    A = np.concatenate((np.ones((S.size, 1)), np.reshape(S, (S.size, 1))), axis=1)
//    b = np.reshape(D, (D.size,))
//    x, _, _, _ = np.linalg.lstsq(A, b)
//            return x[1], x[0]

//    S = reduce(img[k*step:k*step+sourceSize,l*step:l*step+sourceSize], factor)
//
//===============================================================================

    /**
     *
     */
    @Override
    public
    M decode () {
        return null;
    }
}
