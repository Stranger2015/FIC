package org.stranger2015.opencv.fic.core.search;

/**
 *
 */
public
interface ISearchProcessor<T> {
//    Population generateRandomPopulation();
    /**
     *
     */
    T search();

    /**
     * @return
     */
    int evaluate();
}
