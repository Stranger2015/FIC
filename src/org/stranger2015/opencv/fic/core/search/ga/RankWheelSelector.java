package org.stranger2015.opencv.fic.core.search.ga;


import java.nio.ByteBuffer;

/**
 * @param <T>
 * @param <G>
 * @param <C>
 */
public
class RankWheelSelector<T extends Individual <G, C>, G extends BitBuffer, C extends Chromosome <G>>
        extends Selector <T, G, C> {
    /**
     * @param type
     */
    public
    RankWheelSelector ( ESelectionType type ) {
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
