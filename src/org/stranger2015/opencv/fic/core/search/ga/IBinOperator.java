package org.stranger2015.opencv.fic.core.search.ga;

import java.util.function.BinaryOperator;

/**
 *
 */
public
interface IBinOperator<T extends Individual> extends BinaryOperator <T> {
    /**
     * @return
     */
    double getRate ();

    /**
     * @return
     */
    String toString ();
}
