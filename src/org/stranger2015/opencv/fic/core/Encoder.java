package org.stranger2015.opencv.fic.core;

/**
 * A Proposed Hybrid Fractal Image Compression Based on Graph Theory and
 * Isosceles Triangle Segmentation
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
// * Var(I)  =  ()()2* * 2
 * Where n is the size of the block and Xi is the pixel value of the range blocks.
 * Step 11: For all R blocks, repeat Step 6-10;
 * Step 12: Write out the compressed data in the form of a local IFS code;
 * Step 13: Apply a fractal compression algorithm to obtain a compressed IFS code.
 */
public
class Encoder {

    /**
     *
     */
    public
    Encoder () {
    }

    /**
     *
     */
    public
    void encode () {

    }
}
