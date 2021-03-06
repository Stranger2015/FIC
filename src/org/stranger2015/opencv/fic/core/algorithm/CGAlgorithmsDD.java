package org.stranger2015.opencv.fic.core.algorithm;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.math.DD;

import static java.lang.Double.*;

/**
 * Implements basic computational geometry algorithms using {@link DD} arithmetic.
 *
 * @author Martin Davis
 */
public
class CGAlgorithmsDD {
    private
    CGAlgorithmsDD () {
    }

    /**
     * Returns the index of the direction of the point {@code q} relative to
     * a vector specified by {@code p1-p2}.
     *
     * @param p1 the origin point of the vector
     * @param p2 the final point of the vector
     * @param q  the point to compute the direction to
     * @return {@code 1} if q is counter-clockwise (left) from p1-p2
     * {@code -1} if q is clockwise (right) from p1-p2
     * {@code 0} if q is collinear with p1-p2
     */
    public static
    int orientationIndex ( Coordinate p1, Coordinate p2, Coordinate q ) {
        return orientationIndex(p1.x, p1.y, p2.x, p2.y, q.x, q.y);
    }

    /**
     * Returns the index of the direction of the point {@code q} relative to
     * a vector specified by {@code p1-p2}.
     *
     * @param p1x the x ordinate of the vector origin point
     * @param p1y the y ordinate of the vector origin point
     * @param p2x the x ordinate of the vector final point
     * @param p2y the y ordinate of the vector final point
     * @param qx  the x ordinate of the query point
     * @param qy  the y ordinate of the query point
     * @return 1 if q is counter-clockwise (left) from p1-p2
     * -1 if q is clockwise (right) from p1-p2
     * 0 if q is collinear with p1-p2
     */
    public static
    int orientationIndex ( double p1x, double p1y,
                           double p2x, double p2y,
                           double qx, double qy ) {
        // fast filter for orientation index
        // avoids use of slow extended-precision arithmetic in many cases
        int index = orientationIndexFilter(p1x, p1y, p2x, p2y, qx, qy);
        if (index <= 1) return index;

        // normalize coordinates
        DD dx1 = DD.valueOf(p2x).selfAdd(-p1x);
        DD dy1 = DD.valueOf(p2y).selfAdd(-p1y);
        DD dx2 = DD.valueOf(qx).selfAdd(-p2x);
        DD dy2 = DD.valueOf(qy).selfAdd(-p2y);

        // sign of determinant - unrolled for performance
        return dx1.selfMultiply(dy2).selfSubtract(dy1.selfMultiply(dx2)).signum();
    }

    /**
     * Computes the sign of the determinant of the 2x2 matrix
     * with the given entries.
     *
     * @return -1 if the determinant is negative,
     * 1 if the determinant is positive,
     * 0 if the determinant is 0.
     */
    public static
    int signOfDet2x2 ( DD x1, DD y1, DD x2, DD y2 ) {
        DD det = x1.multiply(y2).selfSubtract(y1.multiply(x2));
        return det.signum();
    }

    /**
     * Computes the sign of the determinant of the 2x2 matrix
     * with the given entries.
     *
     * @return -1 if the determinant is negative,
     * 1 if the determinant is positive,
     * 0 if the determinant is 0.
     */
    public static
    int signOfDet2x2 ( double dx1, double dy1, double dx2, double dy2 ) {
        DD x1 = DD.valueOf(dx1);
        DD y1 = DD.valueOf(dy1);
        DD x2 = DD.valueOf(dx2);
        DD y2 = DD.valueOf(dy2);

        DD det = x1.multiply(y2).selfSubtract(y1.multiply(x2));

        return det.signum();
    }

    /**
     * A value which is safely greater than the
     * relative round-off error in double-precision numbers
     */
    private static final double DP_SAFE_EPSILON = 1e-15;

    /**
     * A filter for computing the orientation index of three coordinates.
     * <p>
     * If the orientation can be computed safely using standard DP
     * arithmetic, this routine returns the orientation index.
     * Otherwise, a value i > 1 is returned.
     * In this case the orientation index must
     * be computed using some other more robust method.
     * The filter is fast to compute, so can be used to
     * avoid the use of slower robust methods except when they are really needed,
     * thus providing better average performance.
     * <p>
     * Uses an approach due to Jonathan Shewchuk, which is in the public domain.
     *
     * @param pax A coordinate
     * @param pay A coordinate
     * @param pbx B coordinate
     * @param pby B coordinate
     * @param pcx C coordinate
     * @param pcy C coordinate
     * @return the orientation index if it can be computed safely
     * @return i > 1 if the orientation index cannot be computed safely
     */
    private static
    int orientationIndexFilter ( double pax,
                                 double pay,
                                 double pbx,
                                 double pby,
                                 double pcx,
                                 double pcy ) {
        double detsum;

        double detleft = (pax - pcx) * (pby - pcy);
        double detright = (pay - pcy) * (pbx - pcx);
        double det = detleft - detright;

        if (detleft > 0.0) {
            if (detright <= 0.0) {
                return signum(det);
            }
            else {
                detsum = detleft + detright;
            }
        }
        else if (detleft < 0.0) {
            if (detright >= 0.0) {
                return signum(det);
            }
            else {
                detsum = -detleft - detright;
            }
        }
        else {
            return signum(det);
        }

        double errbound = DP_SAFE_EPSILON * detsum;
        if ((det >= errbound) || (-det >= errbound)) {
            return signum(det);
        }

        return 2;
    }

    @Contract(pure = true)
    private static
    int signum ( double x ) {
        if (x > 0) {
            return 1;
        }
        if (x < 0) {
            return -1;
        }

        return 0;
    }

    /**
     * Computes an intersection point between two lines
     * using DD arithmetic.
     * If the lines are parallel (either identical
     * or separate) a null value is returned.
     *
     * @param p1 an endpoint of line segment 1
     * @param p2 an endpoint of line segment 1
     * @param q1 an endpoint of line segment 2
     * @param q2 an endpoint of line segment 2
     * @return an intersection point if one exists, or null if the lines are parallel
     */
    public static @Nullable
    Coordinate intersection ( Coordinate p1, Coordinate p2, Coordinate q1, Coordinate q2 ) {
        @Nullable Coordinate result = null;
        DD px = new DD(p1.y).selfSubtract(p2.y);
        DD py = new DD(p2.x).selfSubtract(p1.x);
        DD pw = new DD(p1.x).selfMultiply(p2.y).selfSubtract(new DD(p2.x).selfMultiply(p1.y));

        DD qx = new DD(q1.y).selfSubtract(q2.y);
        DD qy = new DD(q2.x).selfSubtract(q1.x);
        DD qw = new DD(q1.x).selfMultiply(q2.y).selfSubtract(new DD(q2.x).selfMultiply(q1.y));

        DD x = py.multiply(qw).selfSubtract(qy.multiply(pw));
        DD y = qx.multiply(pw).selfSubtract(px.multiply(qw));
        DD w = px.multiply(qy).selfSubtract(qx.multiply(py));

        double xInt = x.selfDivide(w).doubleValue();
        double yInt = y.selfDivide(w).doubleValue();

        if ((!isNaN(xInt)) && (!isInfinite(xInt) && !isNaN(yInt)) && (!isInfinite(yInt))) {
            result = new Coordinate(xInt, yInt);
        }

        return result;
    }
}
