package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.utils.BitBuffer;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 5.1 Roulette wheel selection
 * In roulette wheel selection, the probability that individual i is selected, P (choice = i), is computed as follows:
 * P (choice = i) =def
 * fitness(i)
 * Σn
 * j=1fitness(j)
 * Think of a roulette wheel in which the segments are of possibly different sizes, based on each individual’s relative
 * fitness.
 * In case you can’t see how this is implemented, here’s some pseudocode:
 * Algorithm: ROULETTE_WHEEL_SELECTION()
 * r := random number, where 0 ≤ r < 1;
 * sum := 0;
 * for each individual i
 * { sum := sum + P (choice = i);
 * if r < sum
 * { return i;
 * }
 * }
 *
 * <p>Implements selection of <i>n</i> candidates from a population by selecting
 * <i>n</i> candidates at random where the probability of each candidate getting
 * selected is proportional to its fitness score.  This is analogous to each
 * candidate being assigned an area on a roulette wheel proportionate to its fitness
 * and the wheel being spun <n>i</n> times.  Candidates may be selected more than
 * once.</p>
 *
 * <p>In some instances, particularly with small population sizes, the randomness
 * of selection may result in excessively high occurrences of particular candidates.
 * // * If this is a problem, provides an alternativeёё* fitness-proportionate strategy for selection.</p>
 *
 * @author Daniel Dyer
 */
public
class RouletteWheelSelector<T extends Individual <G, C>, G extends BitBuffer, C extends Chromosome <G>>
        extends Selector <T, G, C> {
    /**
     *
     */
    public
    RouletteWheelSelector ( ESelectionType type ) {
        super(type);
    }

    /**
     * Selects the required number of candidates from the population with
     * the probability of selecting any particular candidate being proportional
     * to that candidate's fitness score.  Selection is with replacement (the same
     * candidate may be selected multiple times).
     *
     * @param <S>                  The type of the evolved objects in the population.
     * @param population           The candidates to select from.
     * @param naturalFitnessScores True if higher fitness scores indicate fitter
     *                             individuals, false if lower fitness scores indicate fitter individuals.
     * @param selectionSize        The number of selections to make.
     * @param rng                  A source of randomness.
     * @return The selected candidates.
     */
    public
    <S> List <S> select ( List <T> population,
                          boolean naturalFitnessScores,
                          int selectionSize,
                          Random rng ) {
        // Record the cumulative fitness scores.  It doesn't matter whether the
        // population is sorted or not.  We will use these cumulative scores to work out
        // an index into the population.  The cumulative array itself is implicitly
        // sorted since each element must be greater than the previous one.  The
        // numerical difference between an element and the previous one is directly
        // proportional to the probability of the corresponding candidate in the population
        // being selected.
        double[] cumulativeFitnesses = new double[population.size()];
        cumulativeFitnesses[0] = getAdjustedFitness(population.get(0).getFitness(),
                naturalFitnessScores);
        IntStream.range(1, population.size()).forEachOrdered(i -> {
            double fitness = getAdjustedFitness(population.get(i).getFitness(), naturalFitnessScores);
            cumulativeFitnesses[i] = cumulativeFitnesses[i - 1] + fitness;
        });

        List <S> selection = new ArrayList <>(selectionSize);
        for (int i = 0; i < selectionSize; i++) {
            double randomFitness = rng.nextDouble() * cumulativeFitnesses[cumulativeFitnesses.length - 1];
            int index = Arrays.binarySearch(cumulativeFitnesses, randomFitness);
            if (index < 0) {
                // Convert negative insertion point to array index.
                index = Math.abs(index + 1);
            }
//            selection.add(population.get(index)/*.getCandidate()*/); fixme
        }

        return selection;
    }

    private
    double getAdjustedFitness ( double rawFitness, boolean naturalFitness ) {
        if (naturalFitness) {
            return rawFitness;
        }
        else {
            // If standardised fitness is zero we have found the best possible
            // solution.  The evolutionary algorithm should not be continuing
            // after finding it.
            return rawFitness == 0 ? Double.POSITIVE_INFINITY : 1 / rawFitness;
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public
    String toString () {
        return "Roulette Wheel Selector";
    }


    @Override
    public
    T selectFirst () {
        return null;
    }

    @Override
    public
    T selectSecond () {
        return null;
    }
}

