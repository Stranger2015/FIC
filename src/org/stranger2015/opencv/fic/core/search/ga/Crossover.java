package org.stranger2015.opencv.fic.core.search.ga;

/**
 * @param <C>
 */
abstract
class Crossover<C extends Chromosome> extends Operator<C>
        implements ICrossoverOperator<C>  {

    protected double fitness;

    /**
     * @param gaProcessor
     * @param rate
     */
    protected
    Crossover ( GaSearchProcessor  gaProcessor, double rate ) {
        super(gaProcessor, rate);
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param parent1 the first function argument
     * @param parent2 the second function argument
     * @return the function result
     */
    @SuppressWarnings("unchecked")
    @Override
    public
    C apply ( C parent1, C parent2 ) {
        // Initialize offspring
        C offspring = new Chromosome(parent1.chromosomeLength());
        int swapPoint = (int) (Math.random() * (parent1.getChromosomeLength() + 1));

        // Loop over genome
        for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
            // Use half of parent1's genes and half of parent2's genes
            if (geneIndex < swapPoint) {
                offspring.setGene(geneIndex, parent1.getGene(geneIndex));
            }
            else {
                offspring.setGene(geneIndex, parent2.getGene(geneIndex));
            }
        }

        return offspring;
    }
