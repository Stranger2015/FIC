package org.stranger2015.opencv.fic.core.search.ga;

import java.util.function.Function;

/**
 * @param <T>
 */
public
interface IUnaryOperator<T extends Individual> extends Function<Population<T>, Number> {

    /**
     * @return
     */
   double getRate();
}
