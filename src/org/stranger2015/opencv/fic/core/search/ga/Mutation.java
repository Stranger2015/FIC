package org.stranger2015.opencv.fic.core.search.ga;

/**
 * @param <T>
 */
public
class Mutation
        extends Operator
        implements IMutationOperator {

    private int geneIndex;

    /**
     * @param rate
     */
    protected
    Mutation ( GaSearchProcessor gaProcessor, double rate ) {
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
