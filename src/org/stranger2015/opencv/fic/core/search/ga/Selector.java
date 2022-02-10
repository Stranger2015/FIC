package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;

/**
 * @param <T>
 */
public abstract
class Selector<T extends Individual> implements ISelector<T> {
    protected final ESelectionType type;

    /**
     * @param type
     */
    @Contract(pure = true)
    protected
    Selector ( ESelectionType type ) {
        this.type = type;
    }
}
