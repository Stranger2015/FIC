package org.stranger2015.opencv.fic.core.geom;

import org.locationtech.jts.algorithm.locate.IndexedPointInAreaLocator;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Location;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.index.SpatialIndex;
import org.locationtech.jts.index.strtree.STRtree;
import org.stranger2015.opencv.fic.core.operation.valid.PolygonTopologyAnalyzer;

import java.util.List;

/**
 * Tests whether a MultiPolygon has any element polygon
 * nested inside another polygon, using a spatial
 * index to speed up the comparisons.
 * <p>
 * The logic assumes that the polygons do not overlap and have no collinear segments
 * (so they are properly nested, and there are no duplicate rings).
 */
class IndexedNestedPolygonTester
{
    private MultiPolygon multiPoly;
    private SpatialIndex index;
    private IndexedPointInAreaLocator[] locators;
    private Coordinate nestedPt;

    public IndexedNestedPolygonTester(MultiPolygon multiPoly)
    {
        this.multiPoly = multiPoly;
        loadIndex();
    }

    private void loadIndex()
    {
        index = new STRtree();

        for (int i = 0; i < multiPoly.getNumGeometries(); i++) {
            Polygon poly = (Polygon) multiPoly.getGeometryN(i);
            Envelope env = poly.getEnvelopeInternal();
            index.insert(env, i);
        }
    }

    private IndexedPointInAreaLocator getLocator(int polyIndex) {
        if (locators == null) {
            locators = new IndexedPointInAreaLocator[multiPoly.getNumGeometries()];
        }
        IndexedPointInAreaLocator locator = locators[polyIndex];
        if (locator == null) {
            locator = new IndexedPointInAreaLocator(multiPoly.getGeometryN(polyIndex));
            locators[polyIndex] = locator;
        }
        return locator;
    }

    /**
     * Gets a point on a nested polygon, if one exists.
     *
     * @return a point on a nested polygon, or null if none are nested
     */
    public Coordinate getNestedPoint() { return nestedPt; }

    /**
     * Tests if any polygon is nested (contained) within another polygon.
     * This is invalid.
     * The nested point will be set to reflect this.
     * @return true if some polygon is nested
     */
    public boolean isNested()
    {
        for (int i = 0; i < multiPoly.getNumGeometries(); i++) {
            Polygon poly = (Polygon) multiPoly.getGeometryN(i);
            LinearRing shell = poly.getExteriorRing();

            List<Integer> results = index.query(poly.getEnvelopeInternal());
            for (Integer polyIndex : results) {
                Polygon possibleOuterPoly = (Polygon) multiPoly.getGeometryN(polyIndex);

                if (poly == possibleOuterPoly)
                    continue;
                /**
                 * If polygon is not fully covered by candidate polygon it cannot be nested
                 */
                if (! possibleOuterPoly.getEnvelopeInternal().covers( poly.getEnvelopeInternal()) )
                    continue;

                nestedPt = findNestedPoint(shell, possibleOuterPoly, getLocator(polyIndex));
                if (nestedPt != null)
                    return true;
            }
        }
        return false;
    }

    private Coordinate findNestedPoint(LinearRing shell,
                                       Polygon possibleOuterPoly, IndexedPointInAreaLocator locator)
    {
        /**
         * Try checking two points, since checking point location is fast.
         */
        Coordinate shellPt0 = shell.getCoordinateN(0);
        int loc0 = locator.locate(shellPt0);
        if (loc0 == Location.EXTERIOR) return null;
        if (loc0 == Location.INTERIOR) {
            return shellPt0;
        }

        Coordinate shellPt1 = shell.getCoordinateN(0);
        int loc1 = locator.locate(shellPt1);
        if (loc1 == Location.EXTERIOR) return null;
        if (loc1 == Location.INTERIOR) {
            return shellPt1;
        }

        /**
         * The shell points both lie on the boundary of
         * the polygon.
         * Nesting can be checked via the topology of the incident edges.
         */
        return findSegmentInPolygon(shell, possibleOuterPoly);
    }

    /**
     * Finds a point of a shell segment which lies inside a polygon, if any.
     * The shell is assume to touch the polyon only at shell vertices,
     * and does not cross the polygon.
     *
     * @param shell the shell to test
     * @param poly the polygon to test against
     * @return an interior segment point, or null if the shell is nested correctly
     */
    private static Coordinate findSegmentInPolygon(LinearRing shell, Polygon poly)
    {
        LinearRing polyShell = poly.getExteriorRing();
        if (polyShell.isEmpty()) return null;

        Coordinate shell0 = shell.getCoordinateN(0);
        Coordinate shell1 = shell.getCoordinateN(1);

        if (! PolygonTopologyAnalyzer.isSegmentInRing(shell0, shell1, polyShell))
            return null;

        /**
         * Check if the shell is inside a hole (if there are any).
         * If so this is valid.
         */
        for (int i = 0; i < poly.getNumInteriorRing(); i++) {
            LinearRing hole = poly.getInteriorRingN(i);
            if (hole.getEnvelopeInternal().covers(shell.getEnvelopeInternal())
                    && PolygonTopologyAnalyzer.isSegmentInRing(shell0, shell1, hole)) {
                return null;
            }
        }

        /**
         * The shell is contained in the polygon, but is not contained in a hole.
         * This is invalid.
         */
        return shell0;
    }
}
