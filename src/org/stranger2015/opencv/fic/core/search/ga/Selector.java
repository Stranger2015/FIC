package org.stranger2015.opencv.fic.core.search.ga;

/**
 * @param <C>
 */
public abstract
class Selector<C extends Chromosome>
        implements ISelector  {

    protected final ESelectionType type;

    /**
     * @param type
     */
//    @Contract(pure = true)
    protected
    Selector ( ESelectionType type ) {
        this.type = type;
    }

    /**
     * @return
     */
    @Override
    public
    ESelectionType getType () {
        return type;
    }
}
