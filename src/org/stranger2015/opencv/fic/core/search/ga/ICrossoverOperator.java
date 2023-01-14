package org.stranger2015.opencv.fic.core.search.ga;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

import javax.print.CancelablePrintJob;

/**
 * @param <T>
 */
public
interface ICrossoverOperator<C extends Chromosome>
        extends IBinOperator<C> {



        /**
         * @param <T>
         */
        static
        class TwoPointCrossover<C extends Chromosome >
                extends OnePointCrossover <C> {

            /**
             * @param rate
             */
            protected
            TwoPointCrossover ( GaSearchProcessor gaProcessor, double rate ) {
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
        class UniformCrossover<T extends Individual <M, A, G, C>, A extends IAddress , G extends BitBuffer,
                C extends Chromosome <M, A, G>> extends Crossover <M, A, G, C> {

            /**
             * @param rate
             */
            public
            UniformCrossover ( GaSearchProcessor <M, A, G, C> gaProcessor, double rate ) {
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
