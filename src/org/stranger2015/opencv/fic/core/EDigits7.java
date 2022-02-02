package org.stranger2015.opencv.fic.core;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.codec.IAddress;

import java.util.BitSet;
import java.util.EnumSet;
import java.util.List;
@Deprecated
/**
 *
 */
public
enum EDigits7 implements IDigits7 {
    _0,
    _1,
    _2,
    _3,
    _4,
    _5,
    _6,
    ;

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
    EDigits7 () {
        occurrences = new BitSet(0);
    }

    @Override
    public
    int zero () {
        return _0.ordinal();
    }

    @Override
    public
    int one () {
        return _1.ordinal();
    }

    @Override
    public
    int two () {
        return _2.ordinal();
    }

    @Override
    public
    int three () {
        return _3.ordinal();
    }

    @Override
    public
    int four () {
        return _4.ordinal();
    }

    @Override
    public
    int five () {
        return _5.ordinal();
    }

    @Override
    public
    int six () {
        return _6.ordinal();
    }
}