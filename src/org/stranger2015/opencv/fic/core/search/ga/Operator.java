package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;

/**
 
 */
public abstract
class Operator{
    protected final GaSearchProcessor  gaProcessor;
    protected final double rate;

    /**
     * @param rate
     */
    @Contract(pure = true)
    protected
    Operator ( GaSearchProcessor  gaProcessor, double rate ) {
        this.gaProcessor = gaProcessor;
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
