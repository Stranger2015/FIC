package org.stranger2015.opencv.fic.core.search.ga;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.ValueError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static org.stranger2015.opencv.fic.core.search.ExhaustiveSearchProcessor.ifsRecordLength;
import static org.stranger2015.opencv.fic.core.search.ga.GaSearchProcessor.getRandom;

/**
 *
 */
@SuppressWarnings("unchecked")
public
class Population<C extends Chromosome > extends ArrayList<C> {

//    protected final List <C> list = new ArrayList <>();
    public int popSize = 100;
    protected int chromosomeLength = ifsRecordLength;

    /**
     * @return
     */
    public
    IImage getFittest () {
        return fittest;
    }

    protected IImage fittest;
    protected double populationFitness;

    /**
     * @param list
     */
    public
    Population ( List <C> list ) {
        this.addAll(list);
    }

    /**
     * @param list
     * @param fromIndex
     * @param toIndex
     */
    public
    Population ( List <IImage> list, int fromIndex, int toIndex ) {
        this(list.subList(fromIndex, toIndex));
    }

    /**
     * @param popSize
     * @param chromosomeLength
     */
    public
    Population ( int popSize, int chromosomeLength ) {
        this.popSize = popSize;
        this.chromosomeLength = chromosomeLength;
    }

    /**
     * @param population
     * @param chromosomeLength
     */
    public
    Population ( Population <C> population, int chromosomeLength ) {
        this.chromosomeLength = chromosomeLength;
        popSize = population.size();
        addAll(population);
    }

    /**
     *
     */
    public
    void init () {
        IntStream.range(0, size()).mapToObj(this::
                apply2).forEachOrdered(this::addLast);
    }

    private
    void addLast ( Object o ) {


    }

    public void addLast(C chrom){
        add(chrom);
    }

    /**
     * @param popIndex
     * @return
     */
    public
  IImage getFittest ( int popIndex ) throws ValueError {
//        populations[];
        return new Individual < A, G, C>(chromosomeLength);
    }

    /**
     * @return
     */
    IImage[] getIndividuals () {
        return list.toArray((IImage[]) new Individual[list.size()]);
    }

    /**
     * @return
     */
    public
    int size () {
        return list.size();
    }

    /**
     * @param popIndex
     * @param individual
     */
    public
    void setIndividual ( int popIndex, IImage individual ) {
        list.set(popIndex, (C) individual);
    }

    /**
     * @param populationFitness
     */
    public
    void setPopulationFitness ( double populationFitness ) {
        this.populationFitness = populationFitness;
    }

    /**
     * @param fromIdx
     * @param toIdx
     * @return
     */
    public
    Population<N, A, G, C> getRange ( int fromIdx, int toIdx ) {
        return new Population <>(list.subList(fromIdx, toIdx));
    }

    /**
     * Shuffle randomly current population.
     * <remarks><para>Population shuffling may be useful in cases when selection
     * operator results in not random order of chromosomes (for example, after elite
     * selection population may be ordered in ascending/descending order).</para></remarks>
     */
    public
    void shuffle () {
        // current population size
        int size = this.size();
        // create temporary copy of the population
        Population <N, A, G, C> tempPopulation = this.getRange(0, size);
        // clear current population and refill it randomly
        this.list.clear();
        while (size > 0) {
            int i = getRandom(size);//todo
            this.list.add(tempPopulation.getIndividual(i));
            tempPopulation.list.remove(i);

            size--;
        }
    }

    /**
     * @param i
     * @return
     */
    public
    C getIndividual ( int i ) {
        return list.get(i);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public
    Iterator <C> iterator () {
        return list.listIterator();
    }

    /**
     * @return
     */
    public
    double getPopulationFitness () {
        return populationFitness;
    }

    /**
     * @param i
     * @return
     */
    protected
    IImage apply ( int i ) throws ValueError {
        return new Individual < A, G, C>(chromosomeLength);
    }

    private
    Object apply2 ( int i ) {
        try {
            return apply(i);
        } catch (ValueError e) {
            e.printStackTrace();
        }
        return null;//fixme
    }
}
