package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;

/**
 * @param <T>
 */
public abstract
class Operator<T extends  Individual> {
    protected final double rate;

    /**
     * @param rate
     */
    @Contract(pure = true)
    protected
    Operator ( double rate ) {
        this.rate = rate;
    }

    /**
     * @return
     */
    public
    double getRate () {
        return rate;
    }
}
