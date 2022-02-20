package org.stranger2015.opencv.fic.core.search.ga;

import java.nio.ByteBuffer;

/**
 * @param <T>
 * @param <G>
 * @param <C>
 */
public
class TournamentSelector<T extends Individual <G, C>, G extends BitBuffer, C extends Chromosome <G>>
        extends Selector <T, G, C> {
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
