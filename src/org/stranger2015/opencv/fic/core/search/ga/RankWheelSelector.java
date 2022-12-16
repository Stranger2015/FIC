package org.stranger2015.opencv.fic.core.search.ga;


/**
 * @param <T>
 * @param <G>
 * @param <C>
 */
public
class RankWheelSelector<C extends Chromosome>
        extends Selector <C> {
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
    C selectFirst () {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    C selectSecond () {
        return null;
    }
}
