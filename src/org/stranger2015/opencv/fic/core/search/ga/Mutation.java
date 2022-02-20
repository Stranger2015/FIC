package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <T>
 */
public
class Mutation<T extends Individual<T,A,G>, A extends Address<A>, G extends BitBuffer, C extends Chromosome<T,A,G>>
        extends Operator <T, A,G>
        implements IMutationOperator <T> {

    private int geneIndex;

    /**
     * @param rate
     */
    protected
    Mutation ( GaProcessor <T, A,G,C> gaProcessor, double rate ) {
        super(gaProcessor, rate);
    }

    /**
     * Applies this function to the given argument.
     *
     * @param individual the function argument
     * @return the function result
     */
    @Override
    public
    T apply ( T individual ) {
        // Get new gene
        int newGene = 1;
        if (individual.getGene(geneIndex) == 1) {
            newGene = 0;
        }
        // Mutate gene
        individual.setGene(geneIndex, newGene);

        return individual;
    }

    /**
     * @param geneIndex
     */
    @Override
    public
    void setGeneIndex ( int geneIndex ) {
        this.geneIndex = geneIndex;
    }
}
