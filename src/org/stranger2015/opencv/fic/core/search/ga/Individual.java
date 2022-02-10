package org.stranger2015.opencv.fic.core.search.ga;

public
class Individual {
    /**
     *
     */
    private double fitness;

    public
    Individual ( int chromosomeLength ) {

    }

    /**
     * @param fitness
     */
    void setFitness( double fitness){
        this.fitness = fitness;
    }

    /**
     * @return
     */
    public
    double getFitness () {
        return fitness;
    }

    public
    int[] getChromosome () {
        return new int[0];
    }

    public
    int getChromosomeLength () {
        return 0;
    }

    public
    int getGene ( int geneIndex ) {
        return 0;
    }

    public
    void setGene ( int geneIndex, int newGene ) {

    }
}
