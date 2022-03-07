package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.utils.BitBuffer;


/**
 * @param <T>
 * @param <G>
 * @param <C>
 */
public abstract
class Selector<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
        C extends Chromosome <T, A, G>>
        implements ISelector <T, A, G, C> {

    protected final ESelectionType type;

    /**
     * @param type
     */
    @Contract(pure = true)
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
