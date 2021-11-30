package org.stranger2015.opencv.fic;

import org.opencv.core.Mat;
import org.opencv.core.Range;
import org.stranger2015.opencv.fic.core.EPartitionScheme;
import org.stranger2015.opencv.fic.core.ImagePartitionProcessor;
import org.stranger2015.opencv.fic.core.PartitionScheme;
import org.stranger2015.opencv.fic.core.TreeNode;

/**
 *   Algorithm for fractal image compression by applying Genetic Algorithm.
 *
 * Input: take a NxN square image
 *
 * Initialize FIC parameters like range block size, fitness function, error limit, no. of iterations.
 * Initialize GA parameters like mutation rate, crossover rate;
 * Divide the input image into set of range blocks;
 * Divide the input image into set of domain blocks;
 * Generate a random population of chromosomes from
 * region blocks;
 * While Loop (Number of iterations reached)
 * While Loop (until all Regions not coded)
 * -Select Region Blocks sequentially
 * While Loop (until last generation reached)
 * - Compute fitness for all regions;
 * - Depending upon the fitness search
 * the optimal domain block from
 * domain pool;
 * - When optimal domain block
 * found
 * - write obtained transformation
 * parameters to the Output
 * Coefficient;
 * Wend
 * Wend
 * -Generate new population {Apply Selection,
 * Crossover and Mutation operators};
 * Wend.
 *
 * @param <N>
 * @param <M>
 */
public
class ImagePartitionSegmenter<N extends TreeNode<N>, M extends Mat> extends ImagePartitionProcessor <N, M> {

    public
    ImagePartitionSegmenter ( M image, EPartitionScheme scheme ) {
        super(image, scheme);
    }

    public
    void segment ( ) {
        double fx = image.width() / 8.0;
        double fy = image.height() / 8.0;
        Range range = new Range();
        M subimage = (M) new Mat();

//        Imgproc.resize(image, subimage, new Size(), fx, fy, Imgproc.INTER_CUBIC);
    }
}
