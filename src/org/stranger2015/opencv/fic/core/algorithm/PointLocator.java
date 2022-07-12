package org.stranger2015.opencv.fic.core.algorithm;

import org.stranger2015.opencv.fic.core.algorithm.BoundaryNodeRule;
import org.stranger2015.opencv.fic.core.algorithm.PointLocation;
import org.stranger2015.opencv.fic.core.geom.*;

/**
 * Computes the topological ({@link Location})
 * of a single point to a {@link Geometry}.
 * A {@link BoundaryNodeRule} may be specified
 * to control the evaluation of whether the point lies on the boundary or not
 * The default rule is to use the the <i>SFS Boundary Determination Rule</i>
 * <p>
 * Notes:
 * <ul>
 * <li>{@link LinearRing}s do not enclose any area - points inside the ring are still in the EXTERIOR of the ring.
 * </ul>
 * Instances of this class are not reentrant.
 *
 * @version 1.7
 */
public
class PointLocator {
    // default is to use OGC SFS rule
    private BoundaryNodeRule boundaryRule =
            //IBoundaryNodeRule.ENDPOINT_BOUNDARY_RULE;
            BoundaryNodeRule.OGC_SFS_BOUNDARY_RULE;

    private boolean isIn;         // true if the point lies in or on any Geometry element
    private int numBoundaries;    // the number of sub-elements whose boundaries the point lies in

    public
    PointLocator () {
    }

    public
    PointLocator ( BoundaryNodeRule boundaryRule ) {
        if (boundaryRule == null) {
            throw new IllegalArgumentException("Rule must be non-null");
        }
        this.boundaryRule = boundaryRule;
    }

    /**
     * Convenience method to test a point for intersection with
     * a Geometry
     *
     * @param p    the coordinate to test
     * @param geom the Geometry to test
     * @return <code>true</code> if the point is in the interior or boundary of the Geometry
     */
    public
    boolean intersects ( Coordinate p, Geometry geom ) {
        return locate(p, geom) != EXTERIOR;
    }

    /**
     * Computes the topological relationship ({@link Location}) of a single point
     * to a Geometry.
     * It handles both single-element
     * and multi-element Geometries.
     * The algorithm for multi-part Geometries
     * takes into account the SFS Boundary Determination Rule.
     *
     * @return the {@link Location} of the point relative to the input Geometry
     */
    public
    int locate ( Coordinate p, Geometry geom ) {
        if (geom.isEmpty()) return EXTERIOR;

        if (geom instanceof LineString) {
            return locateOnLineString(p, (LineString) geom);
        }
        else if (geom instanceof Polygon) {
            return locateInPolygon(p, (Polygon) geom);
        }

        isIn = false;
        numBoundaries = 0;
        computeLocation(p, geom);
        if (boundaryRule.isInBoundary(numBoundaries)) {
            return BOUNDARY;
        }

        return numBoundaries > 0 || isIn ? INTERIOR : EXTERIOR;
    }

    private
    void computeLocation ( Coordinate p, Object geom ) {
        if (geom instanceof Point) {
            updateLocationInfo(locateOnPoint(p, (Point) geom));
        }
        if (geom instanceof LineString) {
            updateLocationInfo(locateOnLineString(p, (LineString) geom));
        }
        else if (geom instanceof Polygon) {
            updateLocationInfo(locateInPolygon(p, (Polygon) geom));
        }
        else if (geom instanceof MultiLineString) {
            MultiLineString ml = (MultiLineString) geom;
            for (int i = 0; i < ml.getNumGeometries(); i++) {
                LineString l = (LineString) ml.getGeometryN(i);
                updateLocationInfo(locateOnLineString(p, l));
            }
        }
        else if (geom instanceof MultiPolygon) {
            MultiPolygon mpoly = (MultiPolygon) geom;
            for (int i = 0; i < mpoly.getNumGeometries(); i++) {
                Polygon poly = (Polygon) mpoly.getGeometryN(i);
                updateLocationInfo(locateInPolygon(p, poly));
            }
        }
        else if (geom instanceof GeometryCollection) {
            GeometryCollectionIterator geomi = new GeometryCollectionIterator((Geometry) geom);
            while (geomi.hasNext()) {
                Object g2 = geomi.next();
                if (g2 != geom)
                    computeLocation(p, g2);
            }
        }
    }

    private
    void updateLocationInfo ( int loc ) {
        if (loc == INTERIOR) {
            isIn = true;
        }
        if (loc == BOUNDARY) {
            numBoundaries++;
        }
    }

    private
    int locateOnPoint ( Coordinate p, Point pt ) {
        // no point in doing envelope test, since equality test is just as fast
        Coordinate ptCoord = pt.getCoordinate();
        if (ptCoord.equals2D(p))
            return INTERIOR;
        return EXTERIOR;
    }

    private
    int locateOnLineString ( Coordinate p, LineString l ) {
        // bounding-box check
        if (!l.getEnvelopeInternal().intersects(p)) return EXTERIOR;

        ICoordinateSequence seq = l.getCoordinateSequence();
        if (!l.isClosed()) {
            if (p.equals(seq.getCoordinate(0))
                    || p.equals(seq.getCoordinate(seq.size() - 1))) {
                return BOUNDARY;
            }
        }
        if (PointLocation.isOnLine(p, seq)) {
            return INTERIOR;
        }

        return EXTERIOR;
    }

    private
    int locateInPolygonRing ( Coordinate p, LinearRing ring ) {
        // bounding-box check
        if (!ring.getEnvelopeInternal().intersects(p)) return EXTERIOR;

        return PointLocation.locateInRing(p, ring.getCoordinates());
    }

    private
    int locateInPolygon ( Coordinate p, Polygon poly ) {
        if (poly.isEmpty()) {
            return EXTERIOR;
        }

        LinearRing shell = poly.getExteriorRing();

        int shellLoc = locateInPolygonRing(p, shell);
        if (shellLoc == EXTERIOR) {
            return EXTERIOR;
        }
        if (shellLoc == BOUNDARY) {
            return BOUNDARY;
        }
        // now test if the point lies in or on the holes
        for (int i = 0; i < poly.getNumInteriorRing(); i++) {
            LinearRing hole = poly.getInteriorRingN(i);
            int holeLoc = locateInPolygonRing(p, hole);
            if (holeLoc == INTERIOR) {
                return EXTERIOR;
            }
            if (holeLoc == BOUNDARY) {
                return BOUNDARY;
            }
        }

        return INTERIOR;
    }
}
