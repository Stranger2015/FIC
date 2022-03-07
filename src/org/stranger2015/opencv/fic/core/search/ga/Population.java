package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.utils.BitBuffer;

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
class Population<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
        C extends Chromosome <T, A, G>>

        implements Iterable <T> {

    protected final List <T> list = new ArrayList <>();

    public int popSize = 100;
    protected int chromosomeLength = 31;

    protected T fittest;
    protected double populationFitness;

    /**
     * @param list
     */
    public
    Population ( List <T> list ) {
        this.list.addAll(list);
    }

    /**
     * @param list
     * @param fromIndex
     * @param toIndex
     */
    public
    Population ( List <T> list, int fromIndex, int toIndex ) {
        this(list.subList(fromIndex, toIndex));
    }

    /**
     *
     *
     * @param popSize
     * @param chromosomeLength
     */
    public
    Population ( int popSize, int chromosomeLength ) {
        this.popSize = popSize;
        this.chromosomeLength = chromosomeLength;
    }

    /**
     *
     *
     * @param population
     * @param chromosomeLength
     */
    public
    Population ( Population <T, A, G, C> population, int chromosomeLength ) {
        this.chromosomeLength = chromosomeLength;
        popSize = population.size();
        list.addAll(population.list);
    }

    /**
     *
     */
    public
    void init () {
        IntStream.range(0, list.size()).mapToObj(this::apply).forEachOrdered(list::add);
    }

    /**
     * @param popIndex
     * @return
     */
    public
    T getFittest ( int popIndex ) {
//        populations[];
        return (T) new Individual <T, A, G, C>(chromosomeLength);
    }

    /**
     * @return
     */
    public
    T[] getIndividuals () {
        return list.toArray((T[]) new Individual[list.size()]);
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
    Population <T, A, G, C> getRange ( int fromIdx, int toIdx ) {
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
        Population <T, A, G, C> tempPopulation = this.getRange(0, size);
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

    /**
     * @param i
     * @return
     */
    protected
    T apply ( int i ) {
        return (T) new Individual <T, A, G, C>(chromosomeLength);
    }
}
