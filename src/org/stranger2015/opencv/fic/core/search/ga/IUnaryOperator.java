package org.stranger2015.opencv.fic.core.search.ga;

import java.util.function.UnaryOperator;

/**
 * @param <T>
 */
public
interface IUnaryOperator<T extends Individual> extends UnaryOperator<T> {

    /**
     * @return
     */
   double getRate();
}
