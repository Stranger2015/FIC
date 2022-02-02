package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.search.ISearchProcessor;

/**
 *  Algorithm for fractal image compression by applying Genetic Algorithm.
 *
 *Input: take a NxN square image
 *
 *Initialize FIC parameters like
 *      range block size,
 *      fitness function,
 *      error limit,
 *      no. of iterations.
 *Initialize GA parameters like mutation rate, crossover rate;
 *Divide the input image into set of range blocks;
 *Divide the input image into set of domain blocks;
 *Generate a random population of chromosomes from region blocks;
 *
 *While Loop (Number of iterations reached)
 *  While Loop (until all Regions not coded)
 *-Select Region Blocks sequentially
 *      While Loop (until last generation reached)
 *- Compute fitness for all regions;
 *- Depending upon the fitness search
 *          the optimal domain block from domain pool;
 *- When optimal domain block found
 *- write obtained transformation parameters to the Output
 * Coefficient;
 *      Wend
 *  Wend
 *-Generate new population {Apply Selection, Crossover and Mutation operators};
 *Wend.
 */
public
class GaSearchProcessor implements ISearchProcessor {
    /**
     *
     */
    @Override
    public
    void search () {

    }
}
