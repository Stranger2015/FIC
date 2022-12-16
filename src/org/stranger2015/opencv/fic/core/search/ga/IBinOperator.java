package org.stranger2015.opencv.fic.core.search.ga;

import java.util.function.BinaryOperator;

/**
 *
 */
public
interface IBinOperator<C> extends BinaryOperator<C> {

    /**
     * @return
     */
    double getRate ();

    /**
     * @return
     */
    String toString ();
}
