package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <T>
 * @param <G>
 * @param <C>
 */
public
interface ISelector<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
        C extends Chromosome <T, A, G>> {
    /**
     * @return
     */
    T selectFirst ();

    /**
     * @return
     */
    T selectSecond ();

    /**
     * @return
     */
    ESelectionType getType ();
}
