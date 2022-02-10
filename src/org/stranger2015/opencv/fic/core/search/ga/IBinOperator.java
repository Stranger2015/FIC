package org.stranger2015.opencv.fic.core.search.ga;

import java.util.function.BiFunction;

/**
 *
 */
public
interface IBinOperator<T extends Individual> extends BiFunction <Population <T>, Population <T>, Number> {
    /**
     * @return
     */
    double getRate ();

    /**
     * @return
     */
    String toString ();
}
