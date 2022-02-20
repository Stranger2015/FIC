package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.Image;
import org.stranger2015.opencv.utils.BitBuffer;

import static org.stranger2015.opencv.utils.BitBuffer.allocate;

@SuppressWarnings("unchecked")
public
class Individual<M extends IImage<A>, A extends Address <A>, G extends BitBuffer, C extends Chromosome <M, A, G>>
        extends Image<A> {
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
    Individual ( int chromosomeLength ) {
        chromosome = (C) new BinChromosome <M, A, G>(allocate(chromosomeLength));
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

//    public
//    <S>
//    S getCandidate () {
//        return candidate;
//    }

//    public
//    void setCandidate ( S candidate ) {
//        this.candidate = candidate;
//    }
}
