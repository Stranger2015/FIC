package org.stranger2015.opencv.fic.core.search.ga;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.utils.BitBuffer;

/**
 
 */
public abstract
class Operator<A extends IAddress <A>, G extends BitBuffer>{
//        C extends Chromosome <T, A, G>> {

    protected final GaSearchProcessor <A, G> gaProcessor;
    protected final double rate;

    /**
     * @param rate
     */
    @Contract(pure = true)
    protected
    Operator ( GaSearchProcessor <A, G> gaProcessor, double rate ) {
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
