package org.stranger2015.opencv.fic.core.operation.valid;


import org.locationtech.jts.algorithm.LineIntersector;
import org.locationtech.jts.algorithm.RobustLineIntersector;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.noding.SegmentIntersector;
import org.locationtech.jts.noding.SegmentString;
import org.locationtech.jts.operation.valid.TopologyValidationError;

/**
 * Finds and analyzes intersections in and between polygons,
 * to determine if they are valid.
 * <p>
 * The {@link SegmentString}s which are analyzed can have {@link PolygonRing}s
 * attached.  If so they will be updated with intersection information
 * to support further validity analysis which must be done after
 * basic intersection validity has been confirmed.
 *
 * @author mdavis
 */
class PolygonIntersectionAnalyzer
        implements SegmentIntersector {
    private static final int NO_INVALID_INTERSECTION = -1;

    private final boolean isInvertedRingValid;

    private final LineIntersector li = new RobustLineIntersector();
    private int invalidCode = NO_INVALID_INTERSECTION;
    private Coordinate invalidLocation = null;

    private boolean hasDoubleTouch = false;
    private Coordinate doubleTouchLocation;

    /**
     * Creates a new finder, allowing for the mode where inverted rings are valid.
     *
     * @param isInvertedRingValid true if inverted rings are valid.
     */
    PolygonIntersectionAnalyzer ( boolean isInvertedRingValid ) {
        this.isInvertedRingValid = isInvertedRingValid;
    }

    @Override
    public
    boolean isDone () {
        return isInvalid() || hasDoubleTouch;
    }

    public
    boolean isInvalid () {
        return invalidCode >= 0;
    }

    public
    int getInvalidCode () {
        return invalidCode;
    }

    public
    Coordinate getInvalidLocation () {
        return invalidLocation;
    }

    public
    boolean hasDoubleTouch () {
        return hasDoubleTouch;
    }

    public
    Coordinate getDoubleTouchLocation () {
        return doubleTouchLocation;
    }

    @Override
    public
    void processIntersections ( SegmentString ss0, int segIndex0, SegmentString ss1, int segIndex1 ) {
        // don't test a segment with itself
        boolean isSameSegString = ss0 == ss1;
        boolean isSameSegment = isSameSegString && segIndex0 == segIndex1;
        if (isSameSegment) return;

        int code = findInvalidIntersection(ss0, segIndex0, ss1, segIndex1);
        /**
         * Ensure that invalidCode is only set once,
         * since the short-circuiting in {@link SegmentIntersector} is not guaranteed
         * to happen immediately.
         */
        if (code != NO_INVALID_INTERSECTION) {
            invalidCode = code;
            invalidLocation = li.getIntersection(0);
        }
    }

    private
    int findInvalidIntersection ( SegmentString ss0,
                                  int segIndex0,
                                  SegmentString ss1,
                                  int segIndex1 ) {
        int result = NO_INVALID_INTERSECTION;
        Coordinate p00 = ss0.getCoordinate(segIndex0);
        Coordinate p01 = ss0.getCoordinate(segIndex0 + 1);
        Coordinate p10 = ss1.getCoordinate(segIndex1);
        Coordinate p11 = ss1.getCoordinate(segIndex1 + 1);

        li.computeIntersection(p00, p01, p10, p11);

        if (li.hasIntersection()) {
            boolean isSameSegString = ss0 == ss1;
            /*
             * Check for an intersection in the interior of both segments.
             * Collinear intersections by definition contain an interior intersection.
             */
            if (li.isProper() || li.getIntersectionNum() >= 2) {
                result = TopologyValidationError.SELF_INTERSECTION;
            }
            else {
                /*
                 * Now know there is exactly one intersection,
                 * at a vertex of at least one segment.
                 */
                Coordinate intPt = li.getIntersection(0);
                /* If segments are adjacent the intersection must be their common endpoint.
                 * (since they are not collinear).
                 * This is valid.
                 */
                boolean isAdjacentSegments = isSameSegString && isAdjacentInRing(ss0, segIndex0, segIndex1);// Assert: intersection is an endpoint of both segs
                if (!isAdjacentSegments) {
                    /*
                     * Under OGC semantics, rings cannot self-intersect.
                     * So the intersection is invalid.
                     *
                     * The return of RING_SELF_INTERSECTION is to match the previous IsValid semantics.
                     */
                    if (isSameSegString && !isInvertedRingValid) {
                        result = TopologyValidationError.RING_SELF_INTERSECTION;
                    }
                    else if (!intPt.equals2D(p01) && !intPt.equals2D(p11)) {
                        /*
                         * Check topology of a vertex intersection.
                         * The ring(s) must not cross.
                         */
                        Coordinate e00 = p00;
                        if (intPt.equals2D(p00)) {
                            e00 = prevCoordinateInRing(ss0, segIndex0);
                        }
                        Coordinate e10 = p10;
                        if (intPt.equals2D(p10)) {
                            e10 = prevCoordinateInRing(ss1, segIndex1);
                        }
                        boolean hasCrossing = PolygonNode.isCrossing(intPt, e00, p01, e10, p11);
                        if (hasCrossing) {
                            result = TopologyValidationError.SELF_INTERSECTION;
                        }
                        else {
                            /*
                             * If allowing inverted rings, record a self-touch to support later checking
                             * that it does not disconnect the interior.
                             */
                            if (isSameSegString && isInvertedRingValid) {
                                addSelfTouch(ss0, intPt, e00, p01, e10, p11);
                            }
                            /*
                             * If the rings are in the same polygon
                             * then record the touch to support connected interior checking.
                             *
                             * Also check for an invalid double-touch situation,
                             * if the rings are different.
                             */
                            boolean isDoubleTouch = addDoubleTouch(ss0, ss1, intPt);
                            if (isDoubleTouch && !isSameSegString) {
                                hasDoubleTouch = true;
                                doubleTouchLocation = intPt;
                                // TODO: for poly-hole or hole-hole touch, check if it has bad topology.  If so return invalid code
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    private
    boolean addDoubleTouch ( SegmentString ss0, SegmentString ss1, Coordinate intPt ) {
        return PolygonRing.addTouch((PolygonRing) ss0.getData(), (PolygonRing) ss1.getData(), intPt);
    }

    private
    void addSelfTouch ( SegmentString ss, Coordinate intPt, Coordinate e00, Coordinate e01, Coordinate e10, Coordinate e11 ) {
        PolygonRing polyRing = (PolygonRing) ss.getData();
        if (polyRing == null) {
            throw new IllegalStateException("SegmentString missing PolygonRing data when checking self-touches");
        }
        polyRing.addSelfTouch(intPt, e00, e01, e10, e11);
    }

    /**
     * For a segment string for a ring, gets the coordinate
     * previous to the given index (wrapping if the index is 0)
     *
     * @param ringSS   the ring segment string
     * @param segIndex the segment index
     * @return the coordinate previous to the given segment
     */
    private static
    Coordinate prevCoordinateInRing ( SegmentString ringSS, int segIndex ) {
        int prevIndex = segIndex - 1;
        if (prevIndex < 0) {
            prevIndex = ringSS.size() - 2;
        }
        return ringSS.getCoordinate(prevIndex);
    }

    /**
     * Tests if two segments in a closed {@link SegmentString} are adjacent.
     * This handles determining adjacency across the start/end of the ring.
     *
     * @param ringSS    the segment string
     * @param segIndex0 a segment index
     * @param segIndex1 a segment index
     * @return true if the segments are adjacent
     */
    private static
    boolean isAdjacentInRing ( SegmentString ringSS, int segIndex0, int segIndex1 ) {
        int delta = Math.abs(segIndex1 - segIndex0);
        if (delta <= 1) {
            return true;
        }
        /**
         * A string with N vertices has maximum segment index of N-2.
         * If the delta is at least N-2, the segments must be
         * at the start and end of the string and thus adjacent.
         */
        return delta >= ringSS.size() - 2;
    }
}