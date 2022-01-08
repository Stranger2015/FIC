package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ImageBlock;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;

import java.util.List;

/**
 *3.  PROPOSED ALGORITHM
 *The main objective of this research is to compress the image
 *with high ratio and increase the PSNR of the encoding phase
 *
 *Step 1: Divide color image into sub windows. Each sub window
 *block is called R- block.
 *
 *Step 2: For each R-block, find a Domain block, which most likely
 *represents current R-block after a certain transform.
 * <p>
 *Step  3:  For  every  R-block,  find  D-block  from  D-pool  which  is
 *most likely parallel to it.
 *Crop  the  D-block  size  to  the  size  of  R-block  by  Shrink
 *operation and, mark the D-block as D-block after Shrink
 *operation is applied.
 *Transpose the D- block by using the following eight relative
 *transformation equation matrices proposed by Jacquin. The eight
 *relative transformations matrices are represented in the equations
 *as follows,
 *Consider T = [aij]n with n = 2 where 1 ≤ (i,j) ≤ 2,
 * <p>
 * Relative transformations matrices can be represented with the following Eq.(1) to Eq.(8),
 * T0 is aij 1 if 0
 * i j
 *else
 *T1 is aij
 *1 if
 *0
 *i j
 *i j
 *else
 * T2 is aij 1 if
 *0
 *i j
 *else
 * T3 is aij
 *1 if
 *0
 *ji
 *i j
 *else
 *  T4 is aij
 *0
 *ij
 *i j
 *else
 *   T5 is aij
 *1 if
 *0
 *ii
 *i j
 *else
 * T6 is aij
 *0
 *i j
 *i j
 *else
 * T7 is aij1 if
 *0
 *i j
 *else
 *Above eight relative transformation blocks are generated for
 *each D-block and these blocks are composite new D-pool.
 *Compare each R-block with the blocks which are in D- pool
 *and obtain nearly all like block. The parallelism can be measured
 *with normal conflict MSE using the Eq.(9).
 *where X and Y are the coefficients and should have the values for
 *making  the  MSE  minimum.  The  Xk  and  Yk  are  represented  in
 *Eq.(10) and Eq.(11) respectively,
 * Xk = 2
 * Yk = 2
 *B
 * W = {Dk(x,y), Tk, Xk, Yk} (12)
 *where  Rk  represents  the  relative  transformation  W  for  each  R-block.
 *Fractal  image  coding,  the  original  image  is  segmented  into
 *non-collaborating  regions  called  range  blocks  and  collaborating
 *regions  called  domains  blocks.  For  each  range  block  affine
 *transformations  given  in  the  Eq.(13)  is  used  to  match  as  best
 *domain block.

 *where  Si  controls the  contrast and  0i  controls the  brightness and
 *ai,  bi,  ci,  di,  ei,  fi  denote  the  eight  symmetries  such  as  identity,
 *reflection about mid-vertical and horizontal axis, first and second diagonal.
 *The Fig.1 is the proposed fractal image compression.
 *1. Partition  each  plane  image  into  non-overlapping  blocks,
 *called Range or R-block.
 *2. Select the maximum Range block of size (Rmax) as 16 or 8
 *and minimum block of size (Rmin) of 4 or 8. R-blocks are
 *compared with domains from the domain pool, which are
 *double the range size.
 *3. A window size of α×α is slided on 3 images in α/2 or α/4.
 *The pixels are averaged in groups to reduce to the size.
 *4. After  partitioning  and  transformation,  fractal  encoding
 *finds  suitable  candidate  from  all  blocks  to  encode  any
 *particular R block.
 *5. To  increase  encoding  speed  classification  of  sub-image
 *into  upper  right,  upper  left,  lower  ri

 * * A Proposed Hybrid Fractal Image Compression Based on Graph Theory and Isosceles Triangle Segmentation
 * * <p>
 * * Proposed algorithms encoding steps as following:
 * * <p>
 * *
 * * Step 1: Segment the initial image with graph-based image segmentation algorithm, and set label for all pixels;
 * *
 * * Step 2: Divide initial image into different R blocks with non-overlapping, each R block is 8*8 size, and set
 * * label for each R block, classify R block based on isosceles triangle segmentation rather than rectangles;
 * *
 * * Step 3: Divide image into different D blocks allowing some overlapping, the size is four times as R block,
 * * and set label for each D block, classify D block based on isosceles triangle segmentation rather than rectangles;
 * *
 * * Step 4: Shrink D block to R block’s size;
 * *
 * * Step 5: Calculate D block’s variance, and for D blocks in the same class, sort them according to their variance;
 * *
 * * Step 6: Choose one R block; calculate its variance and matching threshold;
 * *
 * * Step 7: In the search space where R block located in, find a D block which has the closest variance to
 * * current R block;
 * *
 * * Step 8: Define a searching window; the ratio between the number of blocks which located in the searching
 * * window and the number of total blocks is W%, and half of blocks in the searching window have a larger
 * * variance than the D block mentioned in step 7, others have a smaller variance.
 * * Step 9: In the searching window, after affine transformation and gray migration, find the best-match D
 * * block;
 * * Step 10: Calculate the matching error between best-match D block and R block, if the error is larger than
 * * threshold ‘bias’, divide R block into four sub-blocks, add them to the corresponding search space according
 * * to their labels, and slide certain steps, divide some new D blocks, add them to corresponding search space
 * * according to their labels too. If the error is smaller than threshold bias, storage affine transformation parameters;
 * * now, calculate the variance of range and domain blocks by the equation below. The variance of block I is
 * * defined as,
 * * <p>
 * * Var(I)  =  ()()2* * 2
 * * Where n is the size of the block and Xi is the pixel value of the range blocks.
 * * Step 11: For all R blocks, repeat Step 6-10;
 * * Step 12: Write out the compressed data in the form of a local IFS code;
 * * Step 13: Apply a fractal compression algorithm to obtain a compressed IFS code.
 *
 */
public
interface IEncoder<N extends TreeNode <N, A, M>, A extends Address <A>, M extends Image>
        extends IImageProcessorListener {

    /**
     * @param listener
     */
    void addListener ( IEncoderListener listener );

    /**
     * @param listener
     */
    void removeListener ( IEncoderListener listener );

    /**
     * @return
     */
    M encode ( M image );

    /**
     *
     */
    void segmentImage ();

    /**
     * @return
     */
    default
    int getImageSizeBase () {
        return 2;
    }


    /**
     * @param image
     * @param axis
     * @return
     */
    M flipAxis ( M image, int axis );

    /**
     * @param image
     * @param transform
     * @return
     */
    M randomTransform ( M image, ImageTransform <M> transform );

    /**
     * @param image
     * @param transform
     * @return
     */
    M applyTransform ( M image, ImageTransform <M> transform );

    /**
     * @param image
     * @param transform
     * @return
     */
    M applyAffineTransform ( M image, AffineTransform <M> transform );

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
//    M randomRotation ( M x,
//                       Range rg,
//                       int rowAxis,
//                       int colAxis,
//                       int channelAxis,
//                       EFillMode fillMode,
//                       double cval,
//                       int interpolationOrder );
////    # Arguments
//        channel_axis:
//        fill_mode:
//            (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
//        cval: Value used for points outside the boundaries
//            of the input if `mode='constant'`.
//        interpolation_order: int, order of spline interpolation.
//            see `ndimage.interpolation.affine_transform`
//    # Returns

//    """
//    theta = np.random.uniform(-rg, rg)
//    x = apply_affine_transform(x, theta=theta, channel_axis=channel_axis,
//                               fill_mode=fill_mode, cval=cval,
//                               order=interpolation_order)
//    return x
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
//    M randomShift ( M x,
//                    Range wrg,
//                    Range hrg,
//                    int rowAxis,
//                    int colAxis,
//                    int channelAxis,
//                    EFillMode fillMode,
//                    double cval,
//                    int interpolationOrder );
////        x: Input tensor. Must be 3D.
////        wrg:
////        hrg:
////        row_axis:
////        col_axis: Index of axis for columns in the input tensor.
////        channel_axis: Index of axis for channels in the input tensor.
////        fill_mode:
////        cval: Value used for points outside the boundaries
////            of the input if `mode='constant'`.
////        interpolation_order: int, order of spline interpolation.
////            see `ndimage.interpolation.affine_transform`
////    # Returns
////
////    """
////    h, w = x.shape[row_axis], x.shape[col_axis]
////    tx = np.random.uniform(-hrg, hrg) * h
////            ty = np.random.uniform(-wrg, wrg) * w
////    x = apply_affine_transform(x, tx=tx, ty=ty, channel_axis=channel_axis,
////                               fill_mode=fill_mode, cval=cval,
////                               order=interpolation_order)
////    return x
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
//    M randomShear ( M image,
//                    int intensity,
//                    int rowAxis,
//                    int colAxis,
//                    int channelAxis,
//                    EFillMode fillMode,
//                    double cval );
//    # Arguments
//        x: Input tensor. Must be 3D.
//        intensity: Transformation intensity.
//        row_axis: Index of axis for rows in the input tensor.
//        col_axis: Index of axis for columns in the input tensor.
//        channel_axis: Index of axis for channels in the input tensor.
//        fill_mode: Points outside the boundaries of the input
//            are filled according to the given mode
//            (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
//        cval: Value used for points outside the boundaries
//            of the input if `mode='constant'`.
//
//    shear = random.uniform(-intensity, intensity)
//
//
//    h, w = x.shape[row_axis], x.shape[col_axis]
//    transform_matrix = transform_matrix_offset_center(shear_matrix, h, w)
//    x = apply_transform(x, transform_matrix, channel_axis, fill_mode, cval)
//    return x

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
//    M randomZoom ( M x,
//                   Mat.Tuple2 <Float> zoomRange,
//                   int rowAxis/*=1*/,
//                   int colAxis/*=2*/,
//                   int channelAxis/*=0*/,
//                   EFillMode fillMode/*='nearest'*/,
//                   double cval/*=0.*/ );
////            """Performs a random spatial zoom of a Numpy image tensor.
//    # Arguments
//        x: Input tensor. Must be 3D.
//        zoom_range: Tuple of floats; zoom range for width and height.
//        row_axis: Index of axis for rows in the input tensor.
//        col_axis: Index of axis for columns in the input tensor.
//        channel_axis: Index of axis for channels in the input tensor.
//        fill_mode: Points outside the boundaries of the input
//            are filled according to the given mode
//            (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
//        cval: Value used for points outside the boundaries
//            of the input if `mode='constant'`.
//    # Returns
//    # Raises
//        ValueError: if `zoom_range` isn't a tuple.
//    """
//            if len(zoom_range) != 2:
//    raise ValueError('`zoom_range` should be a tuple or list of two floats. '
//                             'Received arg: ', zoom_range)
//
//    if zoom_range[0] == 1 and zoom_range[1] == 1:
//    zx, zy = 1, 1
//            else:
//    zx, zy = np.random.uniform(zoom_range[0], zoom_range[1], 2)
//    zoom_matrix = np.array([[zx, 0, 0],
//            [0, zy, 0],
//            [0, 0, 1]])
//
//    h, w = x.shape[row_axis], x.shape[col_axis]
//    transform_matrix = transform_matrix_offset_center(zoom_matrix, h, w)
//    x = apply_transform(x, transform_matrix, channel_axis, fill_mode, cval)
//    return x

//    M randomChannelShift ( M x, int intensity, int channelAxis/*=0*/ );
//    x = np.rollaxis(x, channel_axis, 0)
//    min_x, max_x = np.min(x), np.max(x)
//    channel_images = [np.clip(x_channel + np.random.uniform(-intensity, intensity), min_x, max_x)
//            for x_channel in x]
//    x = np.stack(channel_images, axis=0)
//    x = np.rollaxis(x, 0, channel_axis + 1)
//            return x
//
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
//                             int order );
//
//    /**
//     * @param matrix
//     * @param x
//     * @param y
//     * @return
//     */
//    M transformMatrixOffsetCenter ( M matrix, int x, int y );
//
//    /**
//     * Performs a brightness shift.
//     *
//     * @param image
//     * @param brightness
//     * @return image tensor.
//     * @throws ValueError if `brightness_range` isn't a tuple.
//     */
//    M applyBrightnessShift ( M image, Range brightness );
//
//    /**
//     * Performs a random brightness shift.
//     *
//     * @param image
//     * @param brightnessRange
//     * @return
//     */
//    M randomBrightness ( M image, Range brightnessRange );

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    List <ImageTransform <M>> compress ( M image, int sourceSize, int destinationSize, int step );

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step );
}