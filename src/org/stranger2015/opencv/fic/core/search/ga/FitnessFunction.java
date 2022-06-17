package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.search.SearchProcessorEvaluator;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 */
public
class FtnessFunction</*T extends Individual <T, A, G, C>*/ /* M extends IImage <A> */, A extends IAddress <A>,
        G extends BitBuffer>
//        C extends Chromosome <T, A, G>>

        extends SearchProcessorEvaluator <M, A,G> {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     */
    @Override
    public
    Number apply ( M i ) {
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
    double calcFitness ( M individual ) {

        return 0;
    }

    public
    Number apply ( Population <A, G> population ) {
        return null;
    }
}
