package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static org.stranger2015.opencv.fic.core.search.ga.GaProcessor.getRandom;

/**
 *
 */

@SuppressWarnings("unchecked")
public
class Population<T extends Individual> implements Iterable <T> {

    private final List <T> list = new ArrayList <>();

    public final static int popSize = 50;//fixme
    public final static int chromSize = 8;

    protected T fittest;
    private double populationFitness;

    public
    Population ( List <T> list ) {

//        this.popSize = popSize;
//        this.chromSize = chromSize;
    }

    public
    Population ( List <T> list, int fromIndex, int toIndex ) {
        this(list.subList(fromIndex, toIndex));
    }

    /**
     * @param popSize
     * @param chromosomeLength
     */
    public
    Population ( int popSize, int chromosomeLength ) {

    }

    /**
     * @param population
     */
    public
    Population( Population <T> population ) {
        list.clear();
        list.addAll(population.list);
    }

    public
    Population ( ISelector <T> selector, int popSize, int chromSize ) {

    }

    /**
     *
     */
    public
    void init () {
        IntStream.range(0, list.size()).mapToObj(i ->
                (T) new Individual(8)).forEachOrdered(list::add);
    }

    /**
     * @param popIndex
     * @return
     */
    public
    T getFittest ( int popIndex ) {
        return (T) new Individual(8);
    }

    /**
     * @return
     */
    public
    T[] getIndividuals () {
        return (T[]) list.toArray(new Individual[list.size()]);
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
    void setIndividual ( int popIndex, T individual ) {
        list.set(popIndex, individual);
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
    Population <T> getRange ( int fromIdx, int toIdx ) {
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
        Population <T> tempPopulation = this.getRange(0, size);
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
    T getIndividual ( int i ) {
        return list.get(i);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @NotNull
    @Override
    public
    Iterator <T> iterator () {
        return list.listIterator();
    }

    /**
     * @return
     */
    public
    double getPopulationFitness () {
        return populationFitness;
    }
}
