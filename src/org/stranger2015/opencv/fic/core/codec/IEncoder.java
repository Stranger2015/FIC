package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ImageBlock;
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
*=======================================================================================================================
 * A Proposed Hybrid Fractal Image Compression Based on Graph Theory and Isosceles Triangle Segmentation
 * <p>
 * Proposed algorithms encoding steps as following:
 * <p>
 *
 * Step 1: Segment the initial image with graph-based image segmentation algorithm, and set label for all pixels;
 *
 * Step 2: Divide initial image into different R blocks with non-overlapping, each R block is 8*8 size, and set
 * label for each R block, classify R block based on isosceles triangle segmentation rather than rectangles;
 *
 * Step 3: Divide image into different D blocks allowing some overlapping, the size is four times as R block,
 * and set label for each D block, classify D block based on isosceles triangle segmentation rather than rectangles;
 *
 * Step 4: Shrink D block to R block’s size;
 *
 * Step 5: Calculate D block’s variance, and for D blocks in the same class, sort them according to their variance;
 *
 * Step 6: Choose one R block; calculate its variance and matching threshold;
 *
 * Step 7: In the search space where R block located in, find a D block which has the closest variance to
 * current R block;
 *
 * Step 8: Define a searching window; the ratio between the number of blocks which located in the searching
 * window and the number of total blocks is W%, and half of blocks in the searching window have a larger
 * variance than the D block mentioned in step 7, others have a smaller variance.
 * Step 9: In the searching window, after affine transformation and gray migration, find the best-match D
 * block;
 * Step 10: Calculate the matching error between best-match D block and R block, if the error is larger than
 * threshold ‘bias’, divide R block into four sub-blocks, add them to the corresponding search space according
 * to their labels, and slide certain steps, divide some new D blocks, add them to corresponding search space
 * according to their labels too. If the error is smaller than threshold bias, storage affine transformation parameters;
 * now, calculate the variance of range and domain blocks by the equation below. The variance of block I is
 * defined as,
 * <p>
 * Var(I)  =  ()()2* 2
 * Where n is the size of the block and Xi is the pixel value of the range blocks.
 * Step 11: For all R blocks, repeat Step 6-10;
 * Step 12: Write out the compressed data in the form of a local IFS code;
 * Step 13: Apply a fractal compression algorithm to obtain a compressed IFS code.
 *
 */
public
interface IEncoder<N extends TreeNode <N, A, M>, A extends Address <A>, M extends IImage>
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
     * @return
     */
    void segmentImage ( M image);

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

    /**
     *
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    List <ImageTransform <M>> compress ( M image, int sourceSize, int destinationSize, int step );

    /**
     *
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    List <ImageBlock> generateAllTransformedBlocks ( M image, int sourceSize, int destinationSize, int step );

    /**
     * @return
     */
    List<ImageBlock> getRangeBlocks();

    /**
     * @return
     */
    List<ImageBlock> getDomainBlocks();

    /**
     * @return
     */
    List<ImageBlock> getCodebookBlocks();

    /**
     * @return
     */
    List<ImageTransform<M>> getTransforms();

    M getInputImage();
}