package org.stranger2015.opencv.fic.core.search.ga;

/**
 * @param <T>
 * @param <G>
 * @param <C>
 */
public
class ElitismSelector<C extends Chromosome> extends Selector<C> {
    /**
     * @param type
     */
    protected
    ElitismSelector ( ESelectionType type ) {
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
