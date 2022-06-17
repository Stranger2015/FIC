package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.transform.ITransform;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <T>
 */
public
class SgaSearchProcessor<T extends Individual <T, A, G, C>, A extends IAddress <A>, G extends BitBuffer, C extends Chromosome <T, A, G>>
        extends GaSearchProcessor <T, A, G, C> {

    /**
     * Please see chapter2/GeneticAlgorithm for additional comments.
     *
     * This GeneticAlgorithm class is designed to solve the
     * "Robot Controller in a Maze" problem, and is necessarily a little different
     * from the chapter2/GeneticAlgorithm class.
     *
     * This class introduces the concepts of tournament selection and single-point
     * crossover. Additionally, the calcFitness method is vastly different from the
     * AllOnesGA fitness method; in this case we actually have to evaluate how good
     * the robot is at navigating a maze!
     *
     * @author bkanber
     *
     */

    /**
     * A new property we've introduced is the size of the population used for
     * tournament selection in crossover.
     */
    protected int tournamentSize;
    protected int chromosomeLength;

    /**
     * @param popSize
     * @param mutationRate
     * @param crossoverRate
     * @param elitismCount
     * @param selector
     * @param fitnessFunction
     * @param mutationOperator
     * @param crossoverOperator
     */
    public
    SgaSearchProcessor ( int popSize,
                         double mutationRate,
                         double crossoverRate,
                         int elitismCount,
                         ISelector <T,A,G,C> selector,
                         FitnessFunction <T,A,G,C> fitnessFunction,
                         IMutationOperator <T> mutationOperator,
                         ICrossoverOperator <T,A,G,C> crossoverOperator ) {

        super(popSize,
                mutationRate,
                crossoverRate,
                elitismCount,
                selector,
                fitnessFunction,
                mutationOperator,
                crossoverOperator
        );

    }

    /**
     * Initialize population
     *
     * @param chromosomeLength The length of the individuals chromosome
     * @return population The initial population generated
     */
    public
    Population <T, A, G, C> initPopulation ( int popSize, int chromosomeLength ) {
        // Initialize population
        Population <T, A, G, C> population = new Population <>(popSize, chromosomeLength);

        return population;
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
        C chromosome = individual.getChromosome();

        // Get fitness
//        Robot robot = new Robot(chromosome, maze, 100);
//        robot.run();
//        int fitness = maze.scoreRoute(robot.getRoute());

        // Store fitness
        double fitness = 0;
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
    double  evalPopulation ( Population <T, A, G, C> population ) {
        double populationFitness = 0;

        // Loop over population evaluating individuals and summing population fitness
        for (T individual : population.getIndividuals()) {
            populationFitness += this.calcFitness(individual);
        }

        population.setPopulationFitness(populationFitness);
        return populationFitness;
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
        Population <T, A, G, C> tournament = new Population <>(population, chromosomeLength);

        // Add random individuals to the tournament
        population.shuffle();
        for (int i = 0; i < this.tournamentSize; i++) {
            T tournamentIndividual = population.getIndividual(i);
            tournament.setIndividual(i, tournamentIndividual);
        }

        // Return the best
        return tournament.getFittest(0);
    }

    /**
     * initialize the population (chromosomes) to random values
     */
    @Override
    protected
    void initPopulation () {

    }

    /**
     * get the fitness value for the given chromosome
     *
     * @param iChromIndex
     */
    @Override
    protected
    double getFitness ( int iChromIndex ) {
        // fitnessFunction.apply();
        return 0;
    }}
