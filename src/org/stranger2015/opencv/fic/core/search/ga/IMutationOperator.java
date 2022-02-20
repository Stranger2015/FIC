package org.stranger2015.opencv.fic.core.search.ga;

/**
 * @param <T>
 */
public
interface IMutationOperator<T extends Individual <T, ?, ?, ?>> extends IUnaryOperator <T> {

    /**
     * @param geneIndex
     */
    void setGeneIndex ( int geneIndex );
}
