package org.stranger2015.opencv.fic.core.shape.fractal;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.geom.Coordinate;

/**
 *
 */
public abstract
class SpaceFillingCurveCode {

    public int radix;
    /**
     * The maximum curve level that can be represented.
     */
    public int MAX_LEVEL;

    /**
     *
     */
    @Contract(pure = true)
    protected
    SpaceFillingCurveCode () {
        radix = 2;
        MAX_LEVEL = 16;
    }

    /**
     * The number of points in the curve for the given level.
     * The number of points is 2<sup>2 * level</sup>.
     *
     * @param level the level of the curve
     * @return the number of points
     */
    public
    int size ( int level ) {
        checkLevel(level);
        return (int) Math.pow(radix, 2 * level);
    }

    /**
     * The maximum ordinate value for points
     * in the curve for the given level.
     * The maximum ordinate is 2<sup>level</sup> - 1.
     *
     * @param level the level of the curve
     * @return the maximum ordinate value
     */
    public
    int maxOrdinate ( int level ) {
        checkLevel(level);
        return (int) Math.pow(radix, level) - 1;
    }

    /**
     * The level of the finite Hilbert curve which contains at least
     * the given number of points.
     *
     * @param numPoints the number of points required
     * @return the level of the curve
     */
    public static
    int level ( int numPoints ) {
        int pow = (int) ((Math.log(numPoints) / Math.log(radix)));
        int level = pow / 2;
        int size = size(level);
        if (size < numPoints) {
            level += 1;
        }

        return level;
    }

    protected
    void checkLevel ( int level ) {
        if (level > MAX_LEVEL) {
            throw new IllegalArgumentException("Level must be in range 0 to " + MAX_LEVEL);
        }
    }

    /**
     * Clamps a level to the range valid for
     * the index algorithm used.
     *
     * @param level the level of a Hilbert curve
     * @return a valid level
     */
    protected
    int levelClamp ( int level ) {
        // clamp order to [1, 16]
        int lvl = Math.max(level, 1);
        lvl = Math.min(lvl, MAX_LEVEL);

        return lvl;
    }

    /**
     * Computes the point on a Hilbert curve
     * of given level for a given code index.
     * The point ordinates will lie in the range [0, 2<sup>level</sup></i> - 1].
     *
     * @param level the Hilbert curve level
     * @param index the index of the point on the curve
     * @return the point on the Hilbert curve
     */
    public
    Coordinate decode ( int level, int index ) {
        checkLevel(level);
        int lvl = levelClamp(level);

        index = index << (32 - 2 * lvl);

        long i0 = deinterleave(index);
        long i1 = deinterleave(index >> 1);

        long t0 = (i0 | i1) ^ 0xFFFF;
        long t1 = i0 & i1;

        long prefixT0 = prefixScan(t0);
        long prefixT1 = prefixScan(t1);

        long a = (((i0 ^ 0xFFFF) & prefixT1) | (i0 & prefixT0));

        long x = (a ^ i1) >> (16 - lvl);
        long y = (a ^ i0 ^ i1) >> (16 - lvl);

        return new Coordinate(x, y);
    }
}
