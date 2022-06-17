package org.stranger2015.opencv.fic.utils;

import org.jetbrains.annotations.Contract;

/**
 *
 */
public
class NumberUtil {
    @Contract(pure = true)
    public static
    boolean equalsWithTolerance ( double x1, double x2, double tolerance ) {
        return Math.abs(x1 - x2) <= tolerance;
    }

}

