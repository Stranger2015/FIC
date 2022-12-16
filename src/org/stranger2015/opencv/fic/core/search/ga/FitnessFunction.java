package org.stranger2015.opencv.fic.core.search.ga;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.search.SearchProcessorEvaluator;

/**
 
 */
public
class FitnessFunction
        extends SearchProcessorEvaluator {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     */
    @Override
    public
    Number apply ( IImage i ) {
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

    /**
     * @param individual
     * @return
     */
    protected
    double calcFitness ( IImage individual ) {

        return 0;
    }

    /**
     * @param population
     * @return
     */
    public
    Number apply ( Population <N,A, G, C> population ) {
        return null;
    }
}
