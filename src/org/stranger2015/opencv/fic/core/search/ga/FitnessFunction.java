package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.search.SearchProcessorEvaluator;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 */
public
class FitnessFunction< A extends IAddress <A>, G extends BitBuffer>
//        C extends Chromosome <T, A, G>>

        extends SearchProcessorEvaluator <A,G> {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     */
    @Override
    public
    Number apply ( IImage<A> i ) {
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
    double calcFitness ( IImage<A> individual ) {

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
