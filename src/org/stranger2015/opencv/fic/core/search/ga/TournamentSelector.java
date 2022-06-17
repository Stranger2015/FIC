package org.stranger2015.opencv.fic.core.search.ga;

import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <T>
 * @param <G>
 * @param <C>
 */
public
class TournamentSelector<T extends Individual <T, A, G, C>, A extends IAddress <A>, G extends BitBuffer,
        C extends Chromosome <T, A, G>>

        extends Selector <T, A, G, C> {
    /**
     * @param type
     */
    protected
    TournamentSelector ( ESelectionType type ) {
        super(type);
    }

    /**
     * @return
     */
    @Override
    public
    T selectFirst () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    T selectSecond () {
        return null;
    }
}
