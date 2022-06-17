package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.fic.core.IImage;
import org.stranger2015.opencv.fic.core.TreeNodeBase;
import org.stranger2015.opencv.fic.core.ValueError;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static org.stranger2015.opencv.fic.core.search.ga.GaSearchProcessor.getRandom;

/**
 *
 */
@SuppressWarnings("unchecked")
public
class Population<N extends TreeNodeBase.TreeNode <N, A, G>, A extends IAddress <A>, /* M extends IImage <A> */, G extends BitBuffer,
        C extends Chromosome <M, A, G>>

        implements Iterable <M> {

    protected final List <M> list = new ArrayList <>();

    public int popSize = 100;
    protected int chromosomeLength = 31;

    protected M fittest;
    protected double populationFitness;

    /**
     * @param list
     */
    public
    Population ( List <M> list ) {
        this.list.addAll(list);
    }

    /**
     * @param list
     * @param fromIndex
     * @param toIndex
     */
    public
    Population ( List <M> list, int fromIndex, int toIndex ) {
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
    Population ( Population <N, A, M, G, C> population, int chromosomeLength ) {
        this.chromosomeLength = chromosomeLength;
        popSize = population.size();
        list.addAll(population.list);
    }

    /**
     *
     */
    public
    void init () {
        IntStream.range(0, list.size()).mapToObj(( int i ) -> {
            try {
                return apply(i);
            } catch (ValueError e) {
                e.printStackTrace();
            }
            return null;//fixme
        }).forEachOrdered(list::add);
    }

    /**
     * @param popIndex
     * @return
     */
    public
    M getFittest ( int popIndex ) throws ValueError {
//        populations[];
        return (M) new Individual <M, A, G, C>(chromosomeLength);
    }

    /**
     * @return
     */
    M[] getIndividuals () {
        return list.toArray((M[]) new Individual[list.size()]);
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
    void setIndividual ( int popIndex, M individual ) {
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
    Population<N, A, M, G, C> getRange ( int fromIdx, int toIdx ) {
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
        Population <N, A, M, G, C> tempPopulation = this.getRange(0, size);
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
    M getIndividual ( int i ) {
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
    Iterator <M> iterator () {
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
    M apply ( int i ) throws ValueError {
        return (M) new Individual <M, A, G, C>(chromosomeLength);
    }
}
