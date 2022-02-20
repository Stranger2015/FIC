package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.utils.BitBuffer;

import java.nio.ByteBuffer;


/**
 * @param <T>
 * @param <G>
 * @param <C>
 */
public abstract
class Selector<T extends Individual <G, C>, G extends BitBuffer, C extends Chromosome <G>>
        implements ISelector <T, G, C> {

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
