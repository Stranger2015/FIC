package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.Address;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 * @param <T>
 */
public abstract
class Operator<T extends Individual <T, A, G, C>, A extends Address <A>, G extends BitBuffer,
        C extends Chromosome <T, A, G>> {

    protected final GaProcessor <T, A, G, C> gaProcessor;
    protected final double rate;

    /**
     * @param rate
     */
    @Contract(pure = true)
    protected
    Operator ( GaProcessor <T, A, G, C> gaProcessor, double rate ) {
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
