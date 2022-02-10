package org.stranger2015.opencv.fic.core.search.ga;

import com.softtechdesign.ga.Crossover;
import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;

import java.util.Date;

/**
 *                  Algorithm for fractal image compression by applying Genetic Algorithm.
 *
 * Input: take a NxN square image
 * <p>
 * Initialize FIC parameters like
 * range block size,
 * fitness function,
 * error limit,
 * no. of iterations.
 * Initialize GA parameters like mutation rate, crossover rate;
 * Divide the input image into set of range blocks;
 * Divide the input image into set of domain blocks;
 * Generate a random population of chromosomes from region blocks;
 * <p>
 * While Loop (Number of iterations reached)
 * While Loop (until all Regions not coded)
 * -Select Region Blocks sequentially
 * While Loop (until last generation reached)
 * - Compute fitness for all regions;
 * - Depending upon the fitness search
 * the optimal domain block from domain pool;
 * - When optimal domain block found
 * - write obtained transformation parameters to the Output
 * Coefficient;
 * Wend
 * Wend
 * -Generate new population {Apply Selection, Crossover and Mutation operators};
 * Wend.
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
class GaProcessor<T extends Individual> implements ISearchProcessor <T>, Runnable {

    private final int populationSize;
          private final double mutationRate;
    private final double crossoverRate;
    private final int elitismCount;

    /**
     * A new property we've introduced is the size of the population used for
     * tournament selection in crossover.
     */
    protected ISelector <Individual> selector;
protected IMutationOperator mutationOperator;
    protected ICrossoverOperator crossoverOperator;

    protected FitnessFunction <T> fitnessFunction;

    /**
     * @param populationSize
     * @param mutationRate
     * @param crossoverRate
     * @param elitismCount
     * @param selector
     */
    @Contract(pure = true)
    public
    GaProcessor ( int populationSize,
                  double mutationRate,
                  double crossoverRate,
                  int elitismCount,
                  ISelector <Individual> selector,
                  FitnessFunction <T> fitnessFunction ) {

        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.selector = selector;
        this.fitnessFunction = fitnessFunction;
    }

    protected Population <T> population;

    /**
     * Initialize population
     *
     * @param chromosomeLength The length of the individuals chromosome
     * @return population The initial population generated
     */
    public
    Population <T> initPopulation ( int chromosomeLength ) {
        // Initialize population
        return new Population <>(this.selector, this.populationSize, chromosomeLength);
    }

    /**
     * Calculate fitness for an individual.
     * <p>
     * This fitness calculation is a little more involved than chapter2's. In
     * this case we initialize a new Robot class, and evaluate its performance
     * in the given maze.
     *
     * @param individual the individual to evaluate
     * @return double The fitness value for individual
     */
    public
    double calcFitness ( T individual ) {
        // Get individual's chromosome
        int[] chromosome = individual.getChromosome();

        // Get fitness
//        Robot robot = new Robot(chromosome, maze, 100);
//        robot.run();
        int fitness = 100;//maze.scoreRoute(robot.getRoute());

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
    void evalPopulation ( Population <T> population ) {
        double populationFitness = fitnessFunction.apply(population).doubleValue();

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
    boolean isTerminationConditionMet ( int generationsCount, int maxGenerations ) {
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
    T selectParent ( Population <T> population ) {
        // Create tournament
        Population <T> tournament = new Population <>(this.selector, populationSize, chromosomeDim);

        // Add random individuals to the tournament
        population.shuffle();
        for (int i = 0; i < this.populationSize; i++) {
            T tournamentIndividual = population.getIndividual(i);
            tournament.setIndividual(i, tournamentIndividual);
        }

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
    Population <T> mutatePopulation ( Population <T> population ) {
        // Initialize new population
        Population <T> newPopulation = new Population <>(this.selector, this.populationSize, chromosomeDim);

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            T individual = population.getFittest(populationIndex);

            // Loop over individual's genes
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                // Skip mutation if this is an elite individual
                if (populationIndex >= this.elitismCount) {
                    // Does this gene need mutation?
                    if (this.mutationRate > Math.random()) {
                        // Get new gene
                        int newGene = 1;
                        if (individual.getGene(geneIndex) == 1) {
                            newGene = 0;
                        }
                        // Mutate gene
                        individual.setGene(geneIndex, newGene);
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
    public
    Population <T> crossoverPopulation ( Population <T> population ) {
        // Create new population
        Population <T> newPopulation = new Population <>(this.selector, this.populationSize, population.size());

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual parent1 = population.getFittest(populationIndex);

            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                // Initialize offspring
                T offspring = (T) new Individual(parent1.getChromosomeLength());

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
                newPopulation.setIndividual(populationIndex, (T) parent1);
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
     * probability that a crossover will occur during genetic mating
     */
    double crossoverProb;

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
    Chromosome[] chromosomes;

    /**
     * storage for temporary holding pool for next generation chromosomes
     */
    Chromosome[] chromNextGen;

    /**
     * storage for pool of prelim generation chromosomes
     */
    Chromosome[] prelimChrom;

    /**
     * index of fittest chromosome in current generation
     */
    int bestFitnessChromIndex;

    /**
     * index of least fit chromosome in current generation
     */
    int worstFitnessChromIndex;

    /**
     * type of crossover to be employed during genetic mating
     */
    protected int crossoverType;

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
     * do a random mutation on given chromosome
     */
    abstract protected
    void doRandomMutation ( int iChromIndex );

    /**
     * do one point crossover between the two given chromosomes
     */
    abstract protected
    void doOnePtCrossover ( Chromosome Chrom1, Chromosome Chrom2 );

    /**
     * do two point crossover between the two given chromosomes
     */
    abstract protected
    void doTwoPtCrossover ( Chromosome Chrom1, Chromosome Chrom2 );

    /**
     * do uniform crossover between the two given chromosomes
     */
    abstract protected
    void doUniformCrossover ( Chromosome Chrom1, Chromosome Chrom2 );

    /**
     * get the fitness value for the given chromosome
     */
    abstract protected
    double getFitness ( int iChromIndex );


    /**
     * Runs the evolution by calling evolve() routine
     */
    public
    void run () {
        evolve();
    }

    /**
     * Initializes the GA using given parameters
     *
     * @param crossoverRate
     * @param chromosomeDim
     * @param populationSize
     * @param elitismCount
     * @param randomSelectionChance
     * @param maxGenerations
     * @param numPrelimRuns
     * @param maxPrelimGenerations
     * @param mutationRate
     * @param crossoverType
     * @param computeStatistics
     */
    public
    GaProcessor ( int chromosomeDim,
                  double crossoverRate,
                  int populationSize,
                  int elitismCount,
                  int randomSelectionChance,
                  int maxGenerations,
                  int numPrelimRuns,
                  int maxPrelimGenerations,
                  double mutationRate,
                  int crossoverType,
                  boolean computeStatistics ) {

        this.populationSize = populationSize;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.randomSelectionProbability = randomSelectionChance;
        this.crossoverType = crossoverType;
        this.chromosomeDim = chromosomeDim;
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
     * Gets the crossover probability
     *
     * @return double
     */
    public
    double getCrossoverProb () {
        return crossoverProb;
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
    int getCrossoverType () {
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
     * @return Chromosome
     */
    public
    Chromosome getFittestChromosome () {
        return (this.chromosomes[bestFitnessChromIndex]);
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
    int getRandom ( int upperBound ) {
        int iRandom = (int) (Math.random() * upperBound);
        return (iRandom);
    }

    /**
     * return a double random number between 0 and upperBound
     *
     * @param upperBound
     * @return double
     */
    double getRandom ( double upperBound ) {
        double dRandom = (Math.random() * upperBound);
        return (dRandom);
    }

    /**
     * Main routine that runs the evolution simulation for this population of chromosomes.
     *
     * @return number of generations
     */
    public
    int evolve () {
        int iGen;
        int iPrelimChrom, iPrelimChromToUsePerRun;

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
                    System.out.println(iPrelimRuns + " of " + numPrelimRuns + " prelim runs --> " +
                            (iGen + 1) + " of " + maxPrelimGenerations + " generations");

                    computeFitnessRankings();
                    doGeneticMating();
                    copyNextGenToThisGen();

                    if (computeStatistics == true) {
                        this.genAvgDeviation[iGen] = getAvgDeviationAmongChroms();
                        this.genAvgFitness[iGen] = getAvgFitness();
                    }
                    iGen++;
                }

                computeFitnessRankings();

                //copy these somewhat fit chromosomes to the main chromosome pool
                int iNumPrelimSaved = 0;
                for (int i = 0; i < populationDim && iNumPrelimSaved < iPrelimChromToUsePerRun; i++)
                    if (this.chromosomes[i].fitnessRank >= populationDim - iPrelimChromToUsePerRun) {
                        this.prelimChrom[iPrelimChrom + iNumPrelimSaved].copyChromGenes(this.chromosomes[i]);
                        //store (remember) these fit chroms
                        iNumPrelimSaved++;
                    }
                iPrelimChrom += iNumPrelimSaved;
            }
            for (int i = 0; i < iPrelimChrom; i++)
                this.chromosomes[i].copyChromGenes(this.prelimChrom[i]);
            System.out.println("INITIAL POPULATION AFTER PRELIM RUNS:");
        }
        else
            System.out.println("INITIAL POPULATION (NO PRELIM RUNS):");

        //Add Preliminary Chromosomes to list box
        addChromosomesToLog(0, 10);

        iGen = 0;
        while (iGen < maxGenerations) {
            computeFitnessRankings();
            doGeneticMating();
            copyNextGenToThisGen();

            if (computeStatistics == true) {
                this.genAvgDeviation[iGen] = getAvgDeviationAmongChroms();
                this.genAvgFitness[iGen] = getAvgFitness();
            }

            iGen++;
        }

        System.out.println("GEN " + (iGen + 1) + " AVG FITNESS = " + this.genAvgFitness[iGen - 1] +
                " AVG DEV = " + this.genAvgDeviation[iGen - 1]);

        addChromosomesToLog(iGen, 10); //display Chromosomes to system.out

        computeFitnessRankings();
        System.out.println("Best Chromosome Found: ");
        System.out.println(this.chromosomes[this.bestFitnessChromIndex].getGenesAsStr() +
                " Fitness= " + this.chromosomes[this.bestFitnessChromIndex].fitness);

        System.out.println("GA end time: " + new Date());
        return (iGen);
    }

    /**
     * Go through all chromosomes and calculate the average fitness (of this generation)
     *
     * @return double
     */
    public
    double getAvgFitness () {
        double rSumFitness = 0.0;

        for (int i = 0; i < populationDim; i++)
            rSumFitness += this.chromosomes[i].fitness;
        return (rSumFitness / populationDim);
    }

    /**
     * Select two parents from population, giving highly fit individuals a greater chance of
     * being selected.
     *
     * @param indexParents
     */
    public
    void selectTwoParents ( int[] indexParents ) {
        int indexParent1 = indexParents[0];
        int indexParent2 = indexParents[1];
        boolean bFound = false;
        int index;

        while (bFound == false) {
            index = getRandom(populationDim); //get random member of population

            if (randomSelectionProbability > getRandom(100)) {
                indexParent1 = index;
                bFound = true;
            }
            else {
                //the greater a chromosome's fitness rank, the higher prob that it will be
                //selected to reproduce
                if (this.chromosomes[index].fitnessRank + 1 > getRandom(populationDim)) {
                    indexParent1 = index;
                    bFound = true;
                }
            }
        }

        bFound = false;
        while (bFound == false) {
            index = getRandom(populationDim); //get random member of population

            if (randomSelectionProbability > getRandom(100)) {
                if (index != indexParent1) {
                    indexParent2 = index;
                    bFound = true;
                }
            }
            else {
                //the greater a chromosome's fitness rank, the higher prob that it will be
                //selected to reproduce
                if ((index != indexParent1)
                        && (this.chromosomes[index].fitnessRank + 1 > getRandom(populationDim))) {
                    //          if (this.chromosomes[index].getNumGenesInCommon(this.chromosomes[indexParent1])+1 > getRandom(chromosomeDim))
                    //          {
                    //            indexParent2 = index;
                    //            bFound = true;
                    //          }
                    indexParent2 = index;
                    bFound = true;
                }
            }
        }

        indexParents[0] = indexParent1;
        indexParents[1] = indexParent2;
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
        for (int i = 0; i < populationDim; i++) {
            if (fitness >= this.chromosomes[i].fitness)
                fitnessRank++;
        }

        return (fitnessRank);
    }

    /**
     * Calculate rankings for all chromosomes. High ranking numbers denote very fit chromosomes.
     */
    void computeFitnessRankings () {
        double rValue;

        // recalc the fitness of each chromosome
        for (int i = 0; i < populationDim; i++)
            this.chromosomes[i].fitness = getFitness(i);

        for (int i = 0; i < populationDim; i++)
            this.chromosomes[i].fitnessRank = getFitnessRank(this.chromosomes[i].fitness);

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
     * Create the next generation of chromosomes by genetically mating fitter individuals of the
     * current generation.
     * Also employ elitism (so the fittest 2 chromosomes always survive to the next generation).
     * This way an extremely fit chromosome is never lost from our chromosome pool.
     */
    void doGeneticMating () {
        int iCnt;
        int iRandom;
        int indexParent1 = -1;
        int indexParent2 = -1;
        Chromosome Chrom1, Chrom2;

        iCnt = 0;

        //Elitism--fittest chromosome automatically go on to next gen (in 2 offspring)
        this.chromNextGen[iCnt].copyChromGenes(this.chromosomes[this.bestFitnessChromIndex]);
        iCnt++;
        this.chromNextGen[iCnt].copyChromGenes(this.chromosomes[this.bestFitnessChromIndex]);
        iCnt++;

//        if (this instanceof GAString) {
//            Chrom1 = new ChromChars(chromosomeDim);
//            Chrom2 = new ChromChars(chromosomeDim);
//        }
//        else if (this instanceof GAFloat) {
//            Chrom1 = new ChromFloat(chromosomeDim);
//            Chrom2 = new ChromFloat(chromosomeDim);
//        }
//        else //must be GASeq
//        {
//            Chrom1 = new ChromStrings(chromosomeDim);
//            Chrom2 = new ChromStrings(chromosomeDim);
//        }

        do {
            int[] indexes = {indexParent1, indexParent2};
            selectTwoParents(indexes);
            indexParent1 = indexes[0];
            indexParent2 = indexes[1];

//            Chrom1.copyChromGenes(this.chromosomes[indexParent1]);
//            Chrom2.copyChromGenes(this.chromosomes[indexParent2]);

            if (getRandom(1.0) < crossoverProb) //do crossover
            {
                if (this.crossoverType == Crossover.ctOnePoint) {
//                    doOnePtCrossover(Chrom1, Chrom2);
                }
                else if (this.crossoverType == Crossover.ctTwoPoint) {
//                    doTwoPtCrossover(Chrom1, Chrom2);
                }
                else if (this.crossoverType == Crossover.ctUniform) {
//                    doUniformCrossover(Chrom1, Chrom2);
                }
                else if (this.crossoverType == Crossover.ctRoulette) {
                    iRandom = getRandom(3);
                    if (iRandom < 1) {
//                        doOnePtCrossover(Chrom1, Chrom2);
                    }
                    else if (iRandom < 2) {
//                        doTwoPtCrossover(Chrom1, Chrom2);
                    }
                    else {
//                        doUniformCrossover(Chrom1, Chrom2);
                    }
                }

//                this.chromNextGen[iCnt].copyChromGenes(Chrom1);
                iCnt++;
//                this.chromNextGen[iCnt].copyChromGenes(Chrom2);
                iCnt++;
            }
            else {//if no crossover, then copy this parent chromosome "as is" into the offspring
//            {
//                // CREATE OFFSPRING ONE
//                this.chromNextGen[iCnt].copyChromGenes(Chrom1);
//                iCnt++;
//
//                // CREATE OFFSPRING TWO
//                this.chromNextGen[iCnt].copyChromGenes(Chrom2);
//                iCnt++;
            }
        }
        while (iCnt < populationDim);
    }

    /**
     * Copy the chromosomes previously created and stored in the "next" generation into the main
     * chromsosome memory pool. Perform random mutations where appropriate.
     */
    void copyNextGenToThisGen () {
        for (int i = 0; i < populationDim; i++) {
            this.chromosomes[i].copyChromGenes(this.chromNextGen[i]);

            //only mutate chromosomes if it is NOT the best
            if (i != this.bestFitnessChromIndex) {
                //always mutate the chromosome with the lowest fitness
                if ((i == this.worstFitnessChromIndex) || (getRandom(1.0) < mutationRate))
                    doRandomMutation(i);
            }
        }
    }

    /**
     * Display chromosome information to System.out
     *
     * @param iGeneration
     * @param iNumChromosomesToDisplay
     */
    void addChromosomesToLog ( int iGeneration, int iNumChromosomesToDisplay ) {
        String sGen, sChrom;

        if (iNumChromosomesToDisplay > this.populationDim)
            iNumChromosomesToDisplay = this.chromosomeDim;

        //Display Chromosomes
        for (int i = 0; i < iNumChromosomesToDisplay; i++) {
            this.chromosomes[i].fitness = getFitness(i);
            sGen = "" + iGeneration;
            if (sGen.length() < 2)
                sGen = sGen + " ";
            sChrom = "" + i;
            if (sChrom.length() < 2)
                sChrom = sChrom + " ";
            System.out.println("Gen " + sGen + ": Chrom" + sChrom + " = " +
                    this.chromosomes[i].getGenesAsStr() + ", fitness = " +
                    this.chromosomes[i].fitness);
        }
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
                for (int i = 0; i < this.populationDim; i++) {
//                    String thisGene =  this.chromosomes[i].getGene(iGene);
                    devCnt++;
                }
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
        long digit, iResult = 0;

        int iLen = sBinary.length();
        for (int i = iLen - 1; i >= 0; i--) {
            if (sBinary.charAt(i) == '1')
                digit = 1;
            else
                digit = 0;
            iResult += (digit << (iLen - i - 1));
        }
        return (iResult);
    }

    //    @Override
    public
    Population generateRandomPopulation () {
        return null;
    }

    /**
     *
     */
    @Override
    public
    T search () {

        return null;
    }

    @Override
    public
    int evaluate () {
        return 0;
    }
}
