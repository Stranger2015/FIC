package org.stranger2015.opencv.fic.core.search.ga;

import org.opencv.core.MatOfInt;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.utils.GrayScaleImage;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.utils.BitBuffer.allocate;

@SuppressWarnings("unchecked")
public
class Individual</*M extends IImage<A>,*/ A extends IAddress <A>, G extends BitBuffer, C extends Chromosome <M, A, G>>
        extends GrayScaleImage <A> {
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
        super();
        chromosome = (C) new BinChromosome <A, G>(allocate(chromosomeLength));
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
    MatOfInt getMat () {
        return actualImage;
    }

    @Override
    public
    int get ( IAddress <A> address, int[] data ) {
        return 0;
    }
}
