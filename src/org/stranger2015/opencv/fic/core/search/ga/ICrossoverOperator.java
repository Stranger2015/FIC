package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <T>
 */
public
interface ICrossoverOperator<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
        C extends Chromosome <T, A, G>>
        extends IBinOperator <T,A,G,C> {
    /**
     * @param <T>
     */
    abstract
    class Crossover<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
            C extends Chromosome <T, A, G>>
            extends Operator <T, A, G, C>
            implements ICrossoverOperator <T, A, G, C> {

        protected double fitness;

        /**
         * @param gaProcessor
         * @param rate
         */
        protected
        Crossover ( GaProcessor <T, A, G, C> gaProcessor, double rate ) {
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
        T apply ( T parent1, T parent2 ) {
            // Initialize offspring
            T offspring = (T) new Individual <T, A, G, C>(parent1.getChromosomeLength());
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

        /**
         * @param <T>
         * @param <A>
         * @param <G>
         * @param <C>
         */
        static
        class OnePointCrossover<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
                C extends Chromosome <T, A, G>>
                extends Crossover <T, A, G, C> {

            /**
             * @param rate
             */
            protected
            OnePointCrossover ( GaProcessor <T, A, G, C> gaProcessor, double rate ) {
                super(gaProcessor, rate);
            }

            /**
             * Applies this function to the given arguments.
             *
             * @param parent1 the first function argument
             * @param parent2 the second function argument
             * @return the function result
             */
            @Override
            public
            T apply ( T parent1, T parent2 ) {
                T offspring = super.apply(parent1, parent2);

                // Get random swap point
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
        }

        /**
         * @param <T>
         */
        static
        class TwoPointCrossover<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
                C extends Chromosome <T, A, G>>
                extends OnePointCrossover <T, A, G, C> {

            /**
             * @param rate
             */
            protected
            TwoPointCrossover ( GaProcessor <T, A, G, C> gaProcessor, double rate ) {
                super(gaProcessor, rate);
            }

            /**
             * Applies this function to the given arguments.
             *
             * @param parent1 the first function argument
             * @param parent2 the second function argument
             * @return the function result
             */
            @Override
            public
            T apply ( T parent1, T parent2 ) {
                return super.apply(parent1, parent2);
            }
        }

        /**
         * @param <T>
         */
        static
        class UniformCrossover<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
                C extends Chromosome <T, A, G>> extends Crossover <T, A, G, C> {

            /**
             * @param rate
             */
            public
            UniformCrossover ( GaProcessor <T, A, G, C> gaProcessor, double rate ) {
                super(gaProcessor, rate);
            }

            /**
             * Applies this function to the given arguments.
             *
             * @param parent1 the first function argument
             * @param parent2 the second function argument
             * @return the function result
             */
            @Override
            public
            T apply ( T parent1, T parent2 ) {
                return super.apply(parent1, parent2);
            }
        }
    }
}
