package org.stranger2015.opencv.fic.core.search.ga;

import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.fic.core.ValueError;

import static org.stranger2015.opencv.utils.BitBuffer.allocate;

/**
 * @param
 * @param <G>
 * @param <C>
 */
@SuppressWarnings("unchecked")
@Deprecated
public
class Individual
        extends Image  {
    /**
     *
     */
    protected double fitness;
    /**
     *
     */
    protected final C chromosome;

    /**
     * @param chromosomeLength
     */
    public
    Individual ( int chromosomeLength ) throws ValueError {
        super((MatOfInt) mat, colorType);
        chromosome = (C) new BinChromosome (allocate(chromosomeLength));
    }

    /**
     * @param fitness
     */
    void setFitness ( double fitness ) {
        this.fitness = fitness;
    }

    /**
     * @return
     */
    public
    double getFitness () {
        return fitness;
    }

    /**
     * @return
     */
    public
    C getChromosome () {
        return chromosome;
    }

    /**
     * @return
     */
    public
    int getChromosomeLength () {
        return 31;
    }

    /**
     * @param geneIndex
     * @return
     */
    public
    boolean getGene ( int geneIndex ) {
//        return chromosome.getGenesAsG().get(geneIndex);
        return true;
    }

    /**
     * @param geneIndex
     * @param newGene
     */
    public
    void setGene ( int geneIndex, boolean newGene ) {
//        chromosome.getGenesAsG().set(geneIndex, newGene);
    }

    /**
     * @return
     */
    @Override
    public
    Mat getMat () {
        return actualImage;
    }

    @Override
    public
    int get ( IAddress  address, int[] data ) {
        return 0;
    }
}
