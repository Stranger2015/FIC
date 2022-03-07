package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.search.SearchProcessorEvaluator;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <T>
 */
public
class FitnessFunction<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
        C extends Chromosome <T, A, G>>

        extends SearchProcessorEvaluator <T, A> {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     */
    @Override
    public
    Number apply ( T i ) {
        // Loop over population evaluating individuals and suming population
        // fitness
        double fitness = calcFitness(i);
//        T[] individuals = population.getIndividuals();
//        for (T individual : individuals) {
//            fitness = this.calcFitness(individual);
        //       }

        i.fitness = fitness;

        return fitness;
    }

    protected
    double calcFitness ( T individual ) {

        return 0;
    }
}
