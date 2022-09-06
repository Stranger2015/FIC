package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.search.SearchProcessor;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import java.io.PrintStream;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * Algorithm for fractal image compression by applying Genetic Algorithm.
 * <p>
 * Input: take a NxN square image
 * <p>
 * Initialize FIC parameters like
 * <p>
 * range block size,
 * fitness function,
 * error limit,
 * no. of iterations.
 * <p>
 * Initialize GA parameters like mutation rate, crossover rate;
 * <p>
 * Divide the input image into set of range blocks;
 * <p>
 * Divide the input image into set of domain blocks;
 * <p>
 * Generate a random population of chromosomes from region blocks;
 * <p>
 *      While Loop (Number of iterations reached){
 *          While Loop (until all Regions not coded){
 * <p>
 *              -Select RegionOfInterest Blocks sequentially
 * <p>
 *              While Loop (until last generation reached){
 * <p>
 *                  - Compute fitness for all regions;
 * <p>
 *                  - Depending upon the fitness search the optimal domain block from domain pool;
 *
 *                  - When optimal domain block found
 *                         - write obtained transformation parameters to the Output Coefficient;
 *              }
 *          }
 *
 *          -Generate new population {Apply Selection, Crossover and Mutation operators};
 *     }.
 * <p>
 * =====================================================================================
 * <p>
 * /**
 * * <pre>
 *  * Package ga
 *  * --------------------------------------------------------------------------------------------
 *  * The GAFloat, GAString, and GASequenceList classes all extend the GA class and can be used
 *  * to model different populations of candidate solutions. You will generally have to extend
 *  * one of these classes every time you create a new GA. In the simplest cases, you will subclass
 *  * one of these classes and then just override and implement your own GetFitness() function. The
 *  * three main subclasses of GA are:
 *  *   GAString (chromosomes are stored as strings)
 *  *   GAFloat (chromosomes are stored as floating point numbers)
 *  *   GASequenceList (chromosomes are stored as strings, additional methods in this class handle
 *  *                   sorting sequences. For example, the GASalesman class extends GASequenceList)
 *  *
 *  * For example:
 *  *   If your chromosomes are floating point numbers, you should subclass TGAFloat and override
 *  *   the getFitness() function with your own.
 *  *
 *  *   If your chromosomes are strings, you should subclass TGAString and override the
 *  *   getFitness() function with your own.
 *  *
 *  *   If your chromosomes are characters in a sequence (or list) that needs to be rearranged, you
 *  *   should use TGASequenceList and override the getFitness() function with your own.
 *  * ---------------------------------------------------------------------------------------------
 *  *
 *  *  This main abstract class is extended by the 3 main GA subclasses:
 *  *  GAString, GAFloat, GASequenceList
 *  *  It (obviously) contains the methods common to all GA subclasses
 *  * </pre>
 * *
 * * @author Jeff Smith jeff@SoftTechDesign.com
 */

public abstract
class GaSearchProcessor<M  extends IImage <A>, A extends IAddress <A>, G extends BitBuffer
        /*C extends Chromosome <A, G, C>*/>
        extends SearchProcessor <M,A, G> {

    protected final int popSize;
    protected final double mutationRate;
    protected final double crossoverRate;
    protected final int elitismCount;

    /**
     * A new property we've introduced is the size of the population used for
     * tournament selection in crossover.
     */
    protected final ISelector <M,A, G> selector;
    protected final IMutationOperator<M,A,G>  mutationOperator;
    protected final ICrossoverOperator <A, G> crossoverOperator;
    protected final FitnessFunction <A, G> fitnessFunction;

    /**
     * @param popSize
     * @param mutationRate
     * @param crossoverRate
     * @param elitismCount
     * @param selector
     */
    @Contract(pure = true)
    protected
    GaSearchProcessor ( int popSize,
                  double mutationRate,
                  double crossoverRate,
                  int elitismCount,
                  ISelector < A, G> selector,
                  FitnessFunction <T, A, G, C> fitnessFunction,
                  IMutationOperator <T> mutationOperator,
                  ICrossoverOperator <T, A, G, C> crossoverOperator ) {

        this.popSize = popSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.selector = selector;
        this.fitnessFunction = fitnessFunction;
        this.mutationOperator = mutationOperator;
        this.crossoverOperator = crossoverOperator;
    }

    protected Population <T, A, G, C>[] populations;
    protected int popIndex;

    /**
     * Initialize population
     *
     * @param chromosomeLength The length of the individuals chromosome
     * @return population The initial population generated
     */
    public
    Population <T, A, G, C> initPopulation ( int chromosomeLength ) {
        // Initialize population
        return new Population <>(populations[popIndex].getRange(0, populations[popIndex].size()),
                chromosomeLength);
    }

    /**
     * Calculate fitness for an individual.
     *
     * @param individual the individual to evaluate
     * @return double The fitness value for individual
     */
    public
    double calcFitness ( T individual ) {
        // Get individual's chromosome
        C chromosome = individual.getChromosome();

        double fitness = 100;

        // Store fitness
        individual.setFitness(fitness);

        return fitness;
    }

    /**
     * Evaluate the whole population
     * <p>
     * Essentially, loop over the individuals in the population, calculate the
     * fitness for each, and then calculate the entire population's fitness. The
     * population's fitness may or may not be important, but what is important
     * here is making sure that each individual gets evaluated.
     * <p>
     * The difference between this method and the one in chapter2 is that this
     * method requires the maze itself as a parameter; unlike the All Ones
     * problem in chapter2, we can't determine a fitness just by looking at the
     * chromosome -- we need to evaluate each member against the maze.
     *
     * @param population the population to evaluate
     */
    public
    double evalPopulation ( Population <T, A, G, C> population ) {

        return fitnessFunction.apply(population).doubleValue();//fixme
    }

    /**
     * Check if population has met termination condition
     * <p>
     * We don't actually know what a perfect solution looks like for the robot
     * controller problem, so the only constraint we can give to the genetic
     * algorithm is an upper bound on the number of generations.
     *
     * @param generationsCount Number of generations passed
     * @param maxGenerations   Number of generations to terminate after
     * @return boolean True if termination condition met, otherwise, false
     */
    public
    boolean isFinalState ( int generationsCount, int maxGenerations ) {
        return (generationsCount > maxGenerations);
    }

    /**
     * Selects parent for crossover using tournament selection
     * <p>
     * Tournament selection works by choosing N random individuals, and then
     * choosing the best of those.
     *
     * @param population
     * @return The individual selected as a parent
     */
    public
    T selectParent ( Population <T, A, G, C> population ) {
        // Create tournament
        Population <T, A, G, C> tournament = new Population <>(population, population.chromosomeLength);

        // Add random individuals to the tournament
        population.shuffle();
        IntStream.range(0, this.popSize).forEachOrdered(i -> {
            T tournamentIndividual = population.getIndividual(i);
            tournament.setIndividual(i, tournamentIndividual);
        });

        // Return the best
        return tournament.getFittest(0);
    }

    /**
     * Apply mutation to population
     * <p>
     * This method is the same as chapter2's version.
     *
     * @param population The population to apply mutation to
     * @return The mutated population
     */
    public
    Population <T, A, G, C> mutatePopulation ( Population <T, A, G, C> population ) {
        // Initialize new population
        Population <T, A, G, C> newPopulation = new Population <>(population, population.chromosomeLength);//fixme

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            T individual = population.getFittest(populationIndex);
            // Loop over individual's genes
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                // Skip mutation if this is an elite individual
                if (populationIndex >= this.elitismCount) {
                    // Does this gene need mutation?
                    if (this.mutationRate > Math.random()) {
                        mutationOperator.setGeneIndex(geneIndex);
                        individual = mutationOperator.apply(individual);
                    }
                }
            }

            // Add individual to population
            newPopulation.setIndividual(populationIndex, individual);
        }

        // Return mutated population
        return newPopulation;
    }

    /**
     * Crossover population using single point crossover
     * <p>
     * Single-point crossover differs from the crossover used in chapter2.
     * Chapter2's version simply selects genes at random from each parent, but
     * in this case we want to select a contiguous region of the chromosome from
     * each parent.
     * <p>
     * For instance, chapter2's version would look like this:
     * <p>
     * Parent1: AAAAAAAAAA
     * Parent2: BBBBBBBBBB
     * Child  : AABBAABABA
     * <p>
     * This version, however, might look like this:
     * <p>
     * Parent1: AAAAAAAAAA
     * Parent2: BBBBBBBBBB
     * Child  : AAAABBBBBB
     *
     * @param population Population to crossover
     * @return Population The new population
     */
    @SuppressWarnings("unchecked")
    public
    Population <T, A, G, C> crossoverPopulation ( Population <T, A, G, C> population ) {
        // Create new population
        Population <T, A, G, C> newPopulation = new Population <>(population.size(), population.chromosomeLength);//fixme

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            T parent1 = population.getFittest(populationIndex);

            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                // Initialize offspring
                T offspring = (T) new Individual <T, A, G, C>(parent1.getChromosomeLength());

                // Find second parent
                T parent2 = this.selectParent(population);

                // Get random swap point
                int swapPoint = (int) (Math.random() * (parent1.getChromosomeLength() + 1));

                // Loop over genome
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    // Use half of parent1's genes and half of parent2's genes
                    if (geneIndex < swapPoint) {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    }
                    else {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }

                // Add offspring to new population
                newPopulation.setIndividual(populationIndex, offspring);
            }
            else {
                // Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }

        return newPopulation;
    }

    //==========================================================================================
    /**
     * maximum generations to evolve
     */
    int maxGenerations;

    /**
     * number of prelim generations to evolve. Set to zero to disable
     */
    int numPrelimRuns;

    /**
     * prelim generations. Prelim runs are useful for building fitter "starting" chromosome stock before the main evolution run.
     */
    int maxPrelimGenerations; //maximum prelim generations to evolve

    /**
     * 1-100 (e.g. 10 = 10% chance of random selection--not based on fitness).
     * Setting nonzero randomSelectionChance helps maintain genetic diversity during evolution
     */
    int randomSelectionProbability;

    /**
     * dimension of chromosome (number of genes)
     */
    protected int chromosomeDim;

    /**
     * number of chromosomes to evolve. A larger population dim will result in a better evolution but will slow the process down
     */
    protected int populationDim;

    /**
     * storage for pool of chromosomes for current generation
     */
    Chromosome <T, A, G>[] chromosomes;

    /**
     * storage for temporary holding pool for next generation chromosomes
     */
    Chromosome <T, A, G>[] chromNextGen;

    /**
     * storage for pool of prelim generation chromosomes
     */
    Chromosome <T, A, G>[] prelimChrom;

    /**
     * index of the fittest chromosome in current generation
     */
    int bestFitnessChromIndex;

    /**
     * index of the least fit chromosome in current generation
     */
    int worstFitnessChromIndex;

    /**
     * type of crossover to be employed during genetic mating
     */
    protected ECrossoverType crossoverType;

    /**
     * statistics--average deviation of current generation
     */
    double[] genAvgDeviation;

    /**
     * statistics--average fitness of current generation
     */
    double[] genAvgFitness;

    /**
     * compute statistics for each generation during evolution?
     */
    boolean computeStatistics;

    /**
     * initialize the population (chromosomes) to random values
     */
    abstract protected
    void initPopulation ();

    /**
     * get the fitness value for the given chromosome
     */
    abstract protected
    double getFitness ( int index );


    /**
     * Runs the evolution by calling evolve() routine
     */
    public
    void run () {
        evolve();
    }

    /**
     *
     */
    @Override
    public
    ITransform <T, A, G> searchForBestTransform () {
        return evolve();
    }

    /**
     * Initializes the GA using given parameters
     *
     * @param chromosomeDim
     * @param crossoverRate
     * @param popSize
     * @param elitismCount
     * @param selector
     * @param maxGenerations
     * @param numPrelimRuns
     * @param maxPrelimGenerations
     * @param mutationRate
     * @param computeStatistics
     */
    public
    GaSearchProcessor ( int chromosomeDim,
                  double crossoverRate,
                  int popSize,
                  int elitismCount,
                  ISelector <T, A, G, C> selector,
                  FitnessFunction <T, A, G, C> fitnessFunction,
                  IMutationOperator <T> mutationOperator,
                  ICrossoverOperator <T, A, G, C> crossoverOperator,
                  int maxGenerations,
                  int numPrelimRuns,
                  int maxPrelimGenerations,
                  double mutationRate,
                  boolean computeStatistics ) {

        this.popSize = popSize;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.selector = selector;
        this.chromosomeDim = chromosomeDim;
        this.fitnessFunction = fitnessFunction;
        this.mutationOperator = mutationOperator;
        this.crossoverOperator = crossoverOperator;
        this.computeStatistics = computeStatistics;

        this.chromosomes = new Chromosome[populationDim];
        this.chromNextGen = new Chromosome[populationDim];
        this.prelimChrom = new Chromosome[populationDim];
        this.genAvgDeviation = new double[maxGenerations];
        this.genAvgFitness = new double[maxGenerations];

        this.maxGenerations = maxGenerations;
        this.numPrelimRuns = numPrelimRuns;
        this.maxPrelimGenerations = maxPrelimGenerations;
        this.mutationRate = mutationRate;
    }

    /**
     * Gets the average deviation of the given generation of chromosomes
     *
     * @param iGeneration
     * @return
     */
    public
    double getAvgDeviation ( int iGeneration ) {
        return (this.genAvgDeviation[iGeneration]);
    }

    /**
     * Gets the average fitness of the given generation of chromosomes
     *
     * @param iGeneration
     * @return
     */
    public
    double getAvgFitness ( int iGeneration ) {
        return (this.genAvgFitness[iGeneration]);
    }

    /**
     * Returns the mutation probability
     *
     * @return double
     */
    public
    double getMutationRate () {
        return mutationRate;
    }

    /**
     * Gets the maximum number of generations this evolution will evolve
     *
     * @return int
     */
    public
    int getMaxGenerations () {
        return maxGenerations;
    }

    /**
     * Gets the number of preliminary runs that will be performed before the main
     * evolution begins
     *
     * @return int
     */
    public
    int getNumPrelimRuns () {
        return numPrelimRuns;
    }

    /**
     * Gets the maximum number of preliminary generations to evolve
     *
     * @return int
     */
    public
    int getMaxPrelimGenerations () {
        return maxPrelimGenerations;
    }

    /**
     * Gets the random selection probability
     *
     * @return int
     */
    public
    int getRandomSelectionProb () {
        return randomSelectionProbability;
    }

    /**
     * Gets the dimension (size or number) of genes per chromosome
     *
     * @return int
     */
    public
    int getChromosomeDim () {
        return chromosomeDim;
    }

    /**
     * Gets the dimension (size or number) of chromosomes in the population
     *
     * @return int
     */
    public
    int getPopulationDim () {
        return populationDim;
    }

    /**
     * Gets the crossover type (e.g. one point, two point, uniform, roulette)
     *
     * @return
     */
    public
    ECrossoverType getCrossoverType () {
        return crossoverType;
    }

    /**
     * Returns whether statistics will be computed for this evolution run
     *
     * @return boolean
     */
    public
    boolean getComputeStatistics () {
        return computeStatistics;
    }

    /**
     * Returns the fittest chromosome in the population
     *
     * @return Chromosome<G>
     */
    public
    Chromosome <T, A, G> getFittestChromosome () {
        return this.chromosomes[bestFitnessChromIndex];
    }

    /**
     * Gets the fitness value of the fittest chromosome in the population
     *
     * @return double
     */
    public
    double getFittestChromosomesFitness () {
        return (this.chromosomes[bestFitnessChromIndex].fitness);
    }

    /**
     * return a integer random number between 0 and upperBound
     *
     * @param upperBound
     * @return int
     */
    public static
    int getRandom ( int upperBound ) {
        return ((int) (Math.random() * upperBound));
    }

    /**
     * return a double random number between 0 and upperBound
     *
     * @param upperBound
     * @return double
     */
    public static
    double getRandom ( double upperBound ) {
        return ((Math.random() * upperBound));
    }

    /**
     * Main routine that runs the evolution simulation for this population of chromosomes.
     *
     * @return the best found image transformation          //number of generations
     */
    public
    ITransform <T, A, G> evolve () {
        int iGen;
        int iPrelimChrom;
        int iPrelimChromToUsePerRun;

        System.out.println("GA start time: " + new Date());

        if (numPrelimRuns > 0) {
            iPrelimChrom = 0;
            //number of fittest prelim chromosomes to use with final run
            iPrelimChromToUsePerRun = populationDim / numPrelimRuns;

            for (int iPrelimRuns = 1; iPrelimRuns <= numPrelimRuns; iPrelimRuns++) {
                iGen = 0;
                initPopulation();

                //create a somewhat fit chromosome population for this prelim run
                while (iGen < maxPrelimGenerations) {
                    try (PrintStream printStream = System.out.printf(
                            "%d of %d prelim runs --> %d of %d generations ",
                            iPrelimRuns,
                            numPrelimRuns,
                            iGen + 1,
                            maxPrelimGenerations)) {
                    }

                    computeFitnessRankings();
//                    doCrossover();
                    copyNextGenToThisGen();

                    if (computeStatistics) {
                        this.genAvgDeviation[iGen] = getAvgDeviationAmongChroms();
                        this.genAvgFitness[iGen] = getAvgFitness();
                    }
                    iGen++;
                }

                computeFitnessRankings();

                //copy these somewhat fit chromosomes to the main chromosome pool
                int iNumPrelimSaved = 0;
                for (int i = 0; i < populationDim && iNumPrelimSaved < iPrelimChromToUsePerRun; i++) {
                    if (this.chromosomes[i].fitnessRank >= populationDim - iPrelimChromToUsePerRun) {
                        this.prelimChrom[iPrelimChrom + iNumPrelimSaved].copyChromGenes(this.chromosomes[i]);
                        //store (remember) these fit chroms
                        iNumPrelimSaved++;
                    }
                }
                iPrelimChrom += iNumPrelimSaved;
            }
            IntStream.range(0, iPrelimChrom).forEachOrdered(i ->
                    this.chromosomes[i].copyChromGenes(this.prelimChrom[i]));

            System.out.println("INITIAL POPULATION AFTER PRELIM RUNS:");
        }
        else {
            System.out.println("INITIAL POPULATION (NO PRELIM RUNS):");
        }

        //Add Preliminary Chromosomes to list box
        addChromosomesToLog(0, 10);

        iGen = 0;
        while (iGen < maxGenerations) {
            computeFitnessRankings();
//            doCrossover();
            copyNextGenToThisGen();

            if (computeStatistics) {
                this.genAvgDeviation[iGen] = getAvgDeviationAmongChroms();
                this.genAvgFitness[iGen] = getAvgFitness();
            }

            iGen++;
        }

        System.out.printf("GEN %d, AVG FITNESS = %f, AVG DEV = %f",
                (iGen + 1),
                this.genAvgFitness[iGen - 1],
                this.genAvgDeviation[iGen - 1]);

        addChromosomesToLog(iGen, 10); //display Chromosomes to system.out

        computeFitnessRankings();
        System.out.println("Best Chromosome<G> Found: ");
        System.out.printf("%s Fitness= %f",
                this.chromosomes[this.bestFitnessChromIndex].getGenesAsG(),
                this.chromosomes[this.bestFitnessChromIndex].fitness);

        System.out.println("GA end time: " + new Date());

        //return (iGen);
        return getFittestChromosome();
    }

    /**
     * Go through all chromosomes and calculate the average fitness (of this generation)
     *
     * @return double
     */
    public
    double getAvgFitness () {
        double rSumFitness = IntStream.range(0, populationDim).mapToDouble(i -> this.chromosomes[i].fitness).sum();

        return (rSumFitness / populationDim);
    }

    /**
     * Calculate the ranking of the parameter "fitness" with respect to the current generation.
     * If the fitness is high, the corresponding fitness ranking will be high, too.
     * For example, if the fitness passed in is higher than any fitness value for any chromosome in the
     * current generation, the fitnessRank will equal the populationDim. And if the fitness is lower than
     * any fitness value for any chromosome in the current generation, the fitnessRank will equal zero.
     *
     * @param fitness
     * @return int the fitness ranking
     */
    int getFitnessRank ( double fitness ) {
        int fitnessRank = -1;
        fitnessRank += IntStream.range(0, populationDim).filter(i -> fitness >= this.chromosomes[i].fitness).count();

        return (fitnessRank);
    }

    /**
     * Calculate rankings for all chromosomes. High ranking numbers denote very fit chromosomes.
     */
    void computeFitnessRankings () {
        double rValue;

        // recalc the fitness of each chromosome
        IntStream.range(0, populationDim).forEachOrdered(i -> this.chromosomes[i].fitness = getFitness(i));

        IntStream.range(0, populationDim).forEachOrdered(i -> this.chromosomes[i].fitnessRank
                = getFitnessRank(this.chromosomes[i].fitness));

        double rBestFitnessVal;
        double rWorstFitnessVal;
        for (int i = 0; i < populationDim; i++) {
            if (this.chromosomes[i].fitnessRank == populationDim - 1) {
                rBestFitnessVal = this.chromosomes[i].fitness;
                this.bestFitnessChromIndex = i;
            }
            if (this.chromosomes[i].fitnessRank == 0) {
                rWorstFitnessVal = this.chromosomes[i].fitness;
                this.worstFitnessChromIndex = i;
            }
        }
    }

    /**
     * Copy the chromosomes previously created and stored in the "next" generation into the main
     * chromsosome memory pool. Perform random mutations where appropriate.
     */
    void copyNextGenToThisGen () {
        //only mutate chromosomes if it is NOT the best
        IntStream.range(0, populationDim).forEachOrdered(i -> {
            this.chromosomes[i].copyChromGenes(this.chromNextGen[i]);
            if (i != this.bestFitnessChromIndex) {
                //always mutate the chromosome with the lowest fitness
                if ((i == this.worstFitnessChromIndex) || (getRandom(1.0) < mutationRate)) {
//                    doRandomMutation(i);
                }
            }
        });
    }

    /**
     * Display chromosome information to System.out
     *
     * @param iGeneration
     * @param iNumChromosomesToDisplay
     */
    void addChromosomesToLog ( int iGeneration, int iNumChromosomesToDisplay ) {
//        String sGen, sChrom;

        if (iNumChromosomesToDisplay > this.populationDim) {
            iNumChromosomesToDisplay = this.chromosomeDim;
        }

        //Display Chromosomes
        //            sGen = "" + iGeneration;
        //            if (sGen.length() < 2)
        //                sGen += " ";
        //            sChrom = "" + i;
        //            if (sChrom.length() < 2) {
        //                sChrom += " ";
        //            }
        IntStream.range(0, iNumChromosomesToDisplay).forEachOrdered(i -> {
            this.chromosomes[i].fitness = getFitness(i);
            System.out.printf("Gen %d: Chrom%d = %s, fitness = %f",
                    iGeneration,
                    i,
                    this.chromosomes[i].getGenesAsG(),
                    this.chromosomes[i].fitness);
        });
    }

    /**
     * Get the average deviation from the current population of chromosomes. The smaller this
     * deviation, the higher the convergence is to a particular (but not necessarily optimal)
     * solution. It calculates this deviation by determining how many genes in the populuation
     * are different than the bestFitGenes. The more genes which are "different", the higher
     * the deviation.
     *
     * @return
     */
    protected
    double getAvgDeviationAmongChroms () {
        int devCnt = 0;
        for (int iGene = 0; iGene < this.chromosomeDim; iGene++) {
//            if (this instanceof GAString) {
//                char bestFitGene = ((ChromChars) this.chromosomes[this.bestFitnessChromIndex]).getGene(iGene);
//                for (int i = 0; i < this.populationDim; i++) {
//                    char thisGene = ((ChromChars) this.chromosomes[i]).getGene(iGene);
//                    if (thisGene != bestFitGene)
//                        devCnt++;
//                }
//            }
//            else if (this instanceof GAFloat) {
//                double bestFitGene =
//                        ((ChromFloat) this.chromosomes[this.bestFitnessChromIndex]).getGene(iGene);
//                for (int i = 0; i < populationDim; i++) {
//                    double thisGene = ((ChromFloat) this.chromosomes[i]).getGene(iGene);
//                    if (thisGene != bestFitGene)
//                        devCnt++;
//                }
//            }
//            else //GAStringsSeq
            {
                String bestFitGene = null;
//                        ((ChromStrings) this.chromosomes[this.bestFitnessChromIndex]).getGene(iGene);
                //                    String thisGene =  this.chromosomes[i].getGene(iGene);
                devCnt += IntStream.range(0, this.populationDim).count();
            }
        }

        return devCnt;
    }

    /**
     * Take a binary string and convert it to the long integer. For example, '1101' --> 13
     *
     * @param sBinary
     * @return long
     */
    long binaryStrToInt ( String sBinary ) {
        long digit = 0;
        long iResult = 0;

        int iLen = sBinary.length();
        for (int i = iLen - 1; i >= 0; i--) {
            digit = sBinary.charAt(i) == '1' ? 1 : 0;
            iResult += (digit << (iLen - i - 1));
        }
        return (iResult);
    }

    /**
     * @return
     */
    public
    Population <T, A, G, C> generateRandomPopulation () {
        Population <T, A, G, C> population = new Population <>(popSize, 31);

        return null;//TODO
    }

    /**
     *
     */
    @Override
    public
    GrayScaleImage<A> search () {
        return (T) evolve();
    }

    @Override
    public
    double evaluate () {
        return (double) fitnessFunction.apply(populations[popIndex]);
    }
}
