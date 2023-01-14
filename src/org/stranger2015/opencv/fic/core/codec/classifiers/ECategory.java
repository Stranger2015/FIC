package org.stranger2015.opencv.fic.core.codec.classifiers;

import org.jetbrains.annotations.Contract;

/**
 *
 */
public
enum ECategory implements IClassifiable {
    COMPLEX_EDGE,
    MIDRANGE,
    SIMPLE_EDGE,
    SMOOTH,
    UNCLASSIFIED;

    int weight;//DEGREE OF MEMBERSHIP

    private int timesUsed;

    @Contract(pure = true)
    public
    int getTimesUsed () {
        return timesUsed;
    }

    /**
     *
     */
    @Contract(mutates = "this")
    void incTimesUsed () {
        ++timesUsed;
    }

    /**
     * @param weight
     */
    @Contract(pure = true)
    ECategory ( int weight) {
        this.weight = weight;
    }

    ECategory () {

    }
}
