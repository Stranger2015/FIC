package org.stranger2015.opencv.fic.core.search.ga;

/**
 * @param <T>
 */
public
interface ICrossoverOperator<T extends Individual> extends IBinOperator <T> {
    /**
     * @param <T>
     */
    abstract
    class Crossover<T extends Individual> extends Operator <T> implements ICrossoverOperator <T> {
        /**
         * @param rate
         */
        protected
        Crossover ( double rate ) {
            super(rate);
        }

        /**
         * Applies this function to the given arguments.
         *
         * @param population1  the first function argument
         * @param population2 the second function argument
         * @return the function result
         */
        @Override
        public
        Number apply ( Population <T> population1, Population <T> population2 ) {
            return null;
        }
    }

    /**
     * @param <T>
     */
        class OnePointCrossover<T extends Individual> extends Crossover<T> {

            /**
             * @param rate
             */
            protected
            OnePointCrossover ( double rate ) {
                super(rate);
            }

            /**
             * @return
             */
            @Override
            public
            double getRate () {
                return rate;
            }

            /**
             * Applies this function to the given arguments.
             *
             * @param population1  the first function argument
             * @param population2 the second function argument
             * @return the function result
             */
            @Override
            public
            Number apply ( Population <T> population1, Population <T> population2 ) {

                return null;
            }
        }

    /**
     * @param <T>
     */
        class TwoPointCrossover<T extends Individual> extends OnePointCrossover<T> {

            /**
             * @param rate
             */
            protected
            TwoPointCrossover ( double rate ) {
                super(rate);
            }

        /**
         * Applies this function to the given arguments.
         *
         * @param population1 the first function argument
         * @param population2 the second function argument
         * @return the function result
         */
        @Override
        public
        Number apply ( Population <T> population1, Population <T> population2 ) {
            return ;
        }
    }

    /**
     * @param <T>
     */
        class UniformCrossover<T extends Individual> extends Crossover<T>{

            /**
             * @param rate
             */
            public
            UniformCrossover ( double rate ) {
                super(rate);
            }
        }
    }
