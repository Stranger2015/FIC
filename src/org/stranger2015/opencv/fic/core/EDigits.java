package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.codec.IAddress;

import java.util.BitSet;
import java.util.EnumSet;
import java.util.List;

/**
 *
 */
public
enum EDigits {
    _0,
    _1,
    _2,
    _3,
    _4,
    _5,
    _6,
    _7,
    _8;

    /**
     * @return
     */
    @Contract(pure = true)
    public
    BitSet getOccurrences () {
        return occurrences;
    }

    private final BitSet occurrences;

    /**
     *
     */
    EDigits () {
        occurrences = new BitSet(0);
    }

}
