package org.stranger2015.opencv.fic.core.codec;

import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.core.search.ga.*;

/**
 * 4. GA’s and the IFS Inverse Problem:
 *
 * Genetic algorithms work with a population of
 * individuals which are iteratively adapted towards the
 * optimum by means of a random process of selection,
 * recombination and mutation. During this process, a
 * fitness function measures the quality of the population,
 * and selection favours those individuals of higher quality.
 * Most of the evolutionary algorithms described in the
 * literature for solving the IFS inverse problem follow the
 * optimization problem. In this case, each individual is an
 * IFS model consisting of a number of transformations and
 * its fitness is given by some convenient measure of
 * similarity between the target image and the IFS attractor.
 * To generate the IFS code of a given image by the use
 * of genetic algorithms, two different approaches of
 * representation can be considered:
 * • Consider the whole IFS of the coded image as
 * an individual, and then iterate the genetic
 * algorithm on a population of IFS, each IFS is
 * constituted by a fix number of transformations
 * (depending on the partition pattern) as genes.
 * • For each range bloc we associate a population
 * of transformation as individuals, each
 * transformation (individual) is represented by its
 * parameters as genes.
 * <p>
 * 4.1. Our algorithm : improving standard method with
 * genetic algorithms
 * The fractal compression scheme for a single image can
 * be seen as in the following algorithm.
 * procedure GA
 * {
 * for( i = 0; i<n; i++){
 * initialize(xi):          // with random values
 * f(xi) = evaluate (xi);    // determine fitness
 * }
 * While termination condition not satisfied do begin
 * p1...n := select from x1...n in random order
 * For i := 1 to n step 2 do begin
 * xil , xi+l l: = crossover(pi , pi+1)
 * mutate(xil)
 * mutate(xi+ll)
 * End
 * x1...n := x1...nl (* copy new population *)
 * For i := 1 to n do begin
 * f (xi) := evaluate(xi)
 * End
 * End {while}
 * End
 * --------------------------------------------------------------------------------------------------------------------
 * <p>
 * Genetic algorithm for FIC
 * -------------------------
 * <p>
 * 1. P ← generate ( LIFS ) random
 * <p>
 * 2. For all LIFS pi ∈ P evaluate by applying ( pi ) to generate an image
 * and measuring its distance ( using the L1 or L2 metric ) to the original
 * image
 * <p>
 * 3. While termination criteria not met
 * <p>
 * 4. Do reproduce pi ∈ P according to evaluation
 * <p>
 * 5. Apply the desired mutation operator ( simple or modified )
 * to some pi ∈ P, selected in some way, creating new LIFS
 * <p>
 * 6. Apply the desired mating operator ( simple or modified )
 * to some pi, pj ∈ P selected in some way, creating new LIF
 * <p>
 * 7. Evaluate new LIFS(as above)
 * <p>
 * 8. Replace the worst old strings with the best new strings
 * ===================================================================================
 * <p>
 * Algorithm: GA(n, χ, μ)
 * // Initialize generation 0:
 * k := 0;
 * Pk := a population of n randomly-generated individuals;
 * // Evaluate Pk:
 * Compute fitness(i) for each i ∈ Pk;
 * do
 * { // Create generation k + 1:
 * // 1. Copy:
 * Select (1 − χ) × n members of Pk and insert into Pk+1;
 * // 2. Crossover:
 * Select χ × n members of Pk; pair them up; produce offspring; insert the offspring into Pk+1;
 * // 3. Mutate:
 * Select μ × n members of Pk+1; invert a randomly-selected bit in each;
 * // Evaluate Pk+1:
 * Compute fitness(i) for each i ∈ Pk;
 * // Increment:
 * k := k + 1;
 * }
 * while fitness of the fittest individual in Pk is not high enough;
 * return the fittest individual from Pk
 */
public
class FicGaProcessor<T extends Individual>
        extends GaProcessor <T>
        implements ISearchProcessor <T> {
    /**
     * @param populationSize
     * @param mutationRate
     * @param crossoverRate
     * @param elitismCount
     * @param selector
     */
    public
    FicGaProcessor ( int populationSize,
                     double mutationRate,
                     double crossoverRate,
                     int elitismCount,
                     ISelector <Individual> selector ) {

        super(
                populationSize,
                mutationRate,
                crossoverRate,
                elitismCount,
                selector);
    }

//    /**
//     * Initializes the GA using given parameters
//     *
//     * @param chromosomeDim
//     * @param populationSize
//     * @param crossoverRate
//     * @param randomSelectionProb
//     * @param maxGenerations
//     * @param numPrelimRuns
//     * @param maxPrelimGenerations
//     * @param mutationRate
//     * @param crossoverType
//     * @param computeStatistics
//     */
//    public
//    FicGaProcessor ( int chromosomeDim,
//                     double crossoverRate,
//                     int populationSize,
//                     int elitismCount,
//                     int randomSelectionProb,
//                     int maxGenerations,
//                     int numPrelimRuns,
//                     int maxPrelimGenerations,
//                     double mutationRate,
//                     int crossoverType,
//                     boolean computeStatistics )  {
//        super(
//                chromosomeDim,
//        crossoverRate,
//        populationSize,
//        elitismCount,
//        randomSelectionProb,
//        maxGenerations,
//        numPrelimRuns,
//        maxPrelimGenerations,
//        mutationRate,
//        crossoverType,
//        computeStatistics );
//    }

    /**
     * initialize the population (chromosomes) to random values
     */
    @Override
    protected
    void initPopulation () {
        Population.init();
    }

    /**
     * do a random mutation on given chromosome
     *
     * @param iChromIndex
     */
    @Override
    protected
    void doRandomMutation ( int iChromIndex ) {

    }

    /**
     * do one point crossover between the two given chromosomes
     *
     * @param Chrom1
     * @param Chrom2
     */
    @Override
    protected
    void doOnePtCrossover ( Chromosome Chrom1, Chromosome Chrom2 ) {

    }

    /**
     * do two point crossover between the two given chromosomes
     *
     * @param Chrom1
     * @param Chrom2
     */
    @Override
    protected
    void doTwoPtCrossover ( Chromosome Chrom1, Chromosome Chrom2 ) {

    }

    /**
     * do uniform crossover between the two given chromosomes
     *
     * @param Chrom1
     * @param Chrom2
     */
    @Override
    protected
    void doUniformCrossover ( Chromosome Chrom1, Chromosome Chrom2 ) {

    }

    /**
     * get the fitness value for the given chromosome
     *
     * @param iChromIndex
     */
    @Override
    protected
    double getFitness ( int iChromIndex ) {
        return 0;
    }

    /**
     *
     */
    @Override
    public
    T search () {

        return null;
    }

    public
    void generate () {

    }

    public
    int evaluate () {
        int fitness = 0;


        return fitness;
    }
}
