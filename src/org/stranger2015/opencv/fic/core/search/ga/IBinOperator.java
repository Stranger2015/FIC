package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.utils.BitBuffer;

import java.util.function.BinaryOperator;

/**
 *
 */
public
interface IBinOperator<T extends Individual <T, A, G, C>, A extends IAddress <A>, G extends BitBuffer,
        C extends Chromosome <T, A, G>> extends BinaryOperator <T> {

    /**
     * @return
     */
    double getRate ();

    /**
     * @return
     */
    String toString ();
}
