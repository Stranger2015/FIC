package org.stranger2015.opencv.fic.core.search.ga;

import java.util.function.Function;

/**
 * @param <T>
 */
public class FitnessFunction<T extends Individual<?,?>> implements Function<Population<T>, Number> {

    /**
     * Applies this function to the given argument.
     *
     * @param population the function argument
     * @return the function result
     */
    @Override
    public
    Number apply ( Population <T> population ) {
        // Loop over population evaluating individuals and suming population
        // fitness
        double fitness=0;
        T[] individuals = population.getIndividuals();
        for (T individual : individuals) {
            fitness = this.calcFitness(individual);
        }

        population.setPopulationFitness(fitness);

        return fitness;
    }

    private
    double calcFitness ( T individual ) {
        return 0;
    }
}
