package org.stranger2015.opencv.fic.core.operation.distance;


import org.locationtech.jts.geom.util.LinearComponentExtracter;
import org.locationtech.jts.geom.util.PointExtracter;
import org.locationtech.jts.geom.util.PolygonExtracter;
import org.locationtech.jts.operation.distance.ConnectedElementLocationFilter;
import org.stranger2015.opencv.fic.core.algorithm.Distance;
import org.stranger2015.opencv.fic.core.algorithm.PointLocator;
import org.stranger2015.opencv.fic.core.geom.*;

import java.util.List;

/**
 * Find two points on two {@link Geometry}s which lie
 * within a given distance, or else are the nearest points
 * on the geometries (in which case this also
 * provides the distance between the geometries).
 * <p>
 * The distance computation also finds a pair of points in the input geometries
 * which have the minimum distance between them.
 * If a point lies in the interior of a line segment,
 * the coordinate computed is a close
 * approximation to the exact point.
 * <p>
 * Empty geometry collection components are ignored.
 * <p>
 * The algorithms used are straightforward O(n^2)
 * comparisons.  This worst-case performance could be improved on
 * by using Voronoi techniques or spatial indexes.
 *
 * @version 1.7
 */
public
class DistanceOp {
    /**
     * Compute the distance between the nearest points of two geometries.
     *
     * @param g0 a {@link Geometry}
     * @param g1 another {@link Geometry}
     * @return the distance between the geometries
     */
    public static
    double distance ( Geometry g0, Geometry g1 ) {
        DistanceOp distOp = new DistanceOp(g0, g1);

        return distOp.distance();
    }

    /**
     * Test whether two geometries lie within a given distance of each other.
     *
     * @param g0       a {@link Geometry}
     * @param g1       another {@link Geometry}
     * @param distance the distance to test
     * @return true if g0.distance(g1) &lt;= distance
     */
    public static
    boolean isWithinDistance ( Geometry g0, Geometry g1, double distance ) {
        // check envelope distance for a short-circuit negative result
        double envDist = g0.getEnvelopeInternal().distance(g1.getEnvelopeInternal());
        if (envDist > distance) {
            return false;
        }

        // MD - could improve this further with a positive short-circuit based on envelope MinMaxDist

        DistanceOp distOp = new DistanceOp(g0, g1, distance);

        return distOp.distance() <= distance;
    }

    /**
     * Compute the the nearest points of two geometries.
     * The points are presented in the same order as the input Geometries.
     *
     * @param g0 a {@link Geometry}
     * @param g1 another {@link Geometry}
     * @return the nearest points in the geometries
     */
    public static
    Coordinate[] nearestPoints ( Geometry g0, Geometry g1 ) {
        org.locationtech.jts.operation.distance.DistanceOp distOp = new org.locationtech.jts.operation.distance.DistanceOp(g0, g1);
        return distOp.nearestPoints();
    }

    /**
     * Compute the the closest points of two geometries.
     * The points are presented in the same order as the input Geometries.
     *
     * @param g0 a {@link Geometry}
     * @param g1 another {@link Geometry}
     * @return the closest points in the geometries
     * @deprecated renamed to nearestPoints
     */
    public static
    Coordinate[] closestPoints ( Geometry g0, Geometry g1 ) {
        DistanceOp distOp = new DistanceOp(g0, g1);

        return distOp.nearestPoints();
    }

    // input
    private final Geometry[] geom;
    private double terminateDistance = 0.0;
    // working
    private final PointLocator ptLocator = new PointLocator();
    private GeometryLocation[] minDistanceLocation;
    private double minDistance = Double.MAX_VALUE;

    /**
     * Constructs a DistanceOp that computes the distance and nearest points between
     * the two specified geometries.
     *
     * @param g0 a Geometry
     * @param g1 a Geometry
     */
    public
    DistanceOp ( Geometry g0, Geometry g1 ) {
        this(g0, g1, 0.0);
    }

    /**
     * Constructs a DistanceOp that computes the distance and nearest points between
     * the two specified geometries.
     *
     * @param g0                a Geometry
     * @param g1                a Geometry
     * @param terminateDistance the distance on which to terminate the search
     */
    public
    DistanceOp ( Geometry g0, Geometry g1, double terminateDistance ) {
        this.geom = new Geometry[2];
        geom[0] = g0;
        geom[1] = g1;
        this.terminateDistance = terminateDistance;
    }

    /**
     * Report the distance between the nearest points on the input geometries.
     *
     * @return the distance between the geometries
     * or 0 if either input geometry is empty
     * @throws IllegalArgumentException if either input geometry is null
     */
    public
    double distance () {
        if (geom[0] == null || geom[1] == null) {
            throw new IllegalArgumentException("null geometries are not supported");
        }
        if (geom[0].isEmpty() || geom[1].isEmpty()) {
            return 0.0;
        }

        computeMinDistance();

        return minDistance;
    }

    /**
     * Report the coordinates of the nearest points in the input geometries.
     * The points are presented in the same order as the input Geometries.
     *
     * @return a pair of {@link Coordinate}s of the nearest points
     */
    public
    Coordinate[] nearestPoints () {
        computeMinDistance();
        return new Coordinate[]{
        minDistanceLocation[0].getCoordinate(),
        minDistanceLocation[1].getCoordinate()
};
    }

    /**
     * @return a pair of {@link Coordinate}s of the nearest points
     * @deprecated renamed to nearestPoints
     */
    public
    Coordinate[] closestPoints () {
        return nearestPoints();
    }

    /**
     * Report the locations of the nearest points in the input geometries.
     * The locations are presented in the same order as the input Geometries.
     *
     * @return a pair of {@link org.locationtech.jts.operation.distance.GeometryLocation}s for the nearest points
     */
    public
    GeometryLocation[] nearestLocations () {
        computeMinDistance();
        return minDistanceLocation;
    }

    /**
     * @return a pair of {@link org.locationtech.jts.operation.distance.GeometryLocation}s for the nearest points
     * @deprecated renamed to nearestLocations
     */
    public
    GeometryLocation[] closestLocations () {
        return nearestLocations();
    }

    private
    void updateMinDistance ( GeometryLocation[] locGeom, boolean flip ) {
        // if not set then don't update
        if (locGeom[0] == null) {
            return;
        }

        if (flip) {
            minDistanceLocation[0] = locGeom[1];
            minDistanceLocation[1] = locGeom[0];
        }
        else {
            minDistanceLocation[0] = locGeom[0];
            minDistanceLocation[1] = locGeom[1];
        }
    }

    private
    void computeMinDistance () {
        // only compute once!
        if (minDistanceLocation != null) {
            return;
        }

        minDistanceLocation = new GeometryLocation[2];
        computeContainmentDistance();
        if (minDistance <= terminateDistance) {
            return;
        }
        computeFacetDistance();
    }

    private
    void computeContainmentDistance () {
        GeometryLocation[] locPtPoly = new GeometryLocation[2];
        // test if either geometry has a vertex inside the other
        computeContainmentDistance(0, locPtPoly);
        if (minDistance <= terminateDistance) {
            return;
        }
        computeContainmentDistance(1, locPtPoly);
    }

    private
    void computeContainmentDistance ( int polyGeomIndex, GeometryLocation[] locPtPoly ) {
        Geometry polyGeom = geom[polyGeomIndex];
        // if no polygon then nothing to do
        if (polyGeom.getDimension() < 2) {
            return;
        }

        int locationsIndex = 1 - polyGeomIndex;
        List<Polygon <T>> polys = PolygonExtracter.getPolygons(polyGeom);
        if (polys.size() > 0) {
            List<GeometryLocation> insideLocs = ConnectedElementLocationFilter.getLocations(geom[locationsIndex]);
            computeContainmentDistance(insideLocs, polys, locPtPoly);
            if (minDistance <= terminateDistance) {
                // this assigment is determined by the order of the args in the computeInside call above
                minDistanceLocation[locationsIndex] = locPtPoly[0];
                minDistanceLocation[polyGeomIndex] = locPtPoly[1];
                return;
            }
        }
    }

    private
    void computeContainmentDistance ( List locs, List polys, org.locationtech.jts.operation.distance.GeometryLocation[] locPtPoly ) {
        for (int i = 0; i < locs.size(); i++) {
            org.locationtech.jts.operation.distance.GeometryLocation loc = (org.locationtech.jts.operation.distance.GeometryLocation) locs.get(i);
            for (int j = 0; j < polys.size(); j++) {
                computeContainmentDistance(loc, (Polygon <T>) polys.get(j), locPtPoly);
                if (minDistance <= terminateDistance) return;
            }
        }
    }

    private
    void computeContainmentDistance ( org.locationtech.jts.operation.distance.GeometryLocation ptLoc,
                                      Polygon <T> poly,
                                      org.locationtech.jts.operation.distance.GeometryLocation[] locPtPoly ) {
        Coordinate pt = ptLoc.getCoordinate();
        // if pt is not in exterior, distance to geom is 0
        if (Location.EXTERIOR != ptLocator.locate(pt, poly)) {
            minDistance = 0.0;
            locPtPoly[0] = ptLoc;
            locPtPoly[1] = new org.locationtech.jts.operation.distance.GeometryLocation(poly, pt);
            ;
            return;
        }
    }

    /**
     * Computes distance between facets (lines and points)
     * of input geometries.
     */
    private
    void computeFacetDistance () {
        org.locationtech.jts.operation.distance.GeometryLocation[] locGeom = new org.locationtech.jts.operation.distance.GeometryLocation[2];

        /**
         * Geometries are not wholely inside, so compute distance from lines and points
         * of one to lines and points of the other
         */
        List lines0 = LinearComponentExtracter.getLines(geom[0]);
        List lines1 = LinearComponentExtracter.getLines(geom[1]);

        List pts0 = PointExtracter.getPoints(geom[0]);
        List pts1 = PointExtracter.getPoints(geom[1]);

        // exit whenever minDistance goes LE than terminateDistance
        computeMinDistanceLines(lines0, lines1, locGeom);
        updateMinDistance(locGeom, false);
        if (minDistance <= terminateDistance) return;

        locGeom[0] = null;
        locGeom[1] = null;
        computeMinDistanceLinesPoints(lines0, pts1, locGeom);
        updateMinDistance(locGeom, false);
        if (minDistance <= terminateDistance) return;

        locGeom[0] = null;
        locGeom[1] = null;
        computeMinDistanceLinesPoints(lines1, pts0, locGeom);
        updateMinDistance(locGeom, true);
        if (minDistance <= terminateDistance) return;

        locGeom[0] = null;
        locGeom[1] = null;
        computeMinDistancePoints(pts0, pts1, locGeom);
        updateMinDistance(locGeom, false);
    }

    private
    void computeMinDistanceLines ( List lines0, List lines1, org.locationtech.jts.operation.distance.GeometryLocation[] locGeom ) {
        for (int i = 0; i < lines0.size(); i++) {
            LineString line0 = (LineString) lines0.get(i);
            for (int j = 0; j < lines1.size(); j++) {
                LineString line1 = (LineString) lines1.get(j);
                computeMinDistance(line0, line1, locGeom);
                if (minDistance <= terminateDistance) return;
            }
        }
    }

    private
    void computeMinDistancePoints ( List points0, List points1, org.locationtech.jts.operation.distance.GeometryLocation[] locGeom ) {
        for (int i = 0; i < points0.size(); i++) {
            Point pt0 = (Point) points0.get(i);
            for (int j = 0; j < points1.size(); j++) {
                Point pt1 = (Point) points1.get(j);
                double dist = pt0.getCoordinate().distance(pt1.getCoordinate());
                if (dist < minDistance) {
                    minDistance = dist;
                    locGeom[0] = new org.locationtech.jts.operation.distance.GeometryLocation(pt0, 0, pt0.getCoordinate());
                    locGeom[1] = new org.locationtech.jts.operation.distance.GeometryLocation(pt1, 0, pt1.getCoordinate());
                }
                if (minDistance <= terminateDistance) return;
            }
        }
    }

    private
    void computeMinDistanceLinesPoints ( List <LineString> lines, List <Point> points, GeometryLocation[] locGeom ) {
        for (LineString line : lines) {
            for (Point point : points) {
                computeMinDistance(line, point, locGeom);
                if (minDistance <= terminateDistance) {
                    return;
                }
            }
        }
    }

    private
    void computeMinDistance ( LineString line0, LineString line1, GeometryLocation[] locGeom ) {
        if (line0.getEnvelopeInternal().distance(line1.getEnvelopeInternal()) > minDistance) {
            return;
        }
        Coordinate[] coord0 = line0.getCoordinates();
        Coordinate[] coord1 = line1.getCoordinates();
        // brute force approach!
        for (int i = 0; i < coord0.length - 1; i++) {
            // short-circuit if line segment is far from line
            Envelope segEnv0 = new Envelope(coord0[i], coord0[i + 1]);
            if (segEnv0.distance(line1.getEnvelopeInternal()) > minDistance)
                continue;
            for (int j = 0; j < coord1.length - 1; j++) {
                // short-circuit if line segments are far apart
                Envelope segEnv1 = new Envelope(coord1[j], coord1[j + 1]);
                if (segEnv0.distance(segEnv1) > minDistance)
                    continue;

                double dist = Distance.segmentToSegment(
                        coord0[i], coord0[i + 1],
                        coord1[j], coord1[j + 1]);
                if (dist < minDistance) {
                    minDistance = dist;
                    LineSegment seg0 = new LineSegment(coord0[i], coord0[i + 1]);
                    LineSegment seg1 = new LineSegment(coord1[j], coord1[j + 1]);
                    Coordinate[] closestPt = seg0.closestPoints(seg1);
                    locGeom[0] = new GeometryLocation(line0, i, closestPt[0]);
                    locGeom[1] = new GeometryLocation(line1, j, closestPt[1]);
                }
                if (minDistance <= terminateDistance) return;
            }
        }
    }

    private
    void computeMinDistance ( LineString line, Point pt, GeometryLocation[] locGeom ) {
        if (line.getEnvelopeInternal().distance(pt.getEnvelopeInternal())
                > minDistance)
            return;
        Coordinate[] coord0 = line.getCoordinates();
        Coordinate coord = pt.getCoordinate();
        // brute force approach!
        for (int i = 0; i < coord0.length - 1; i++) {
            double dist = Distance.pointToSegment(coord, coord0[i], coord0[i + 1]);
            if (dist < minDistance) {
                minDistance = dist;
                LineSegment seg = new LineSegment(coord0[i], coord0[i + 1]);
                Coordinate segClosestPoint = seg.closestPoint(coord);
                locGeom[0] = new org.locationtech.jts.operation.distance.GeometryLocation(line, i, segClosestPoint);
                locGeom[1] = new GeometryLocation(pt, 0, coord);
            }
            if (minDistance <= terminateDistance) {
                return;
            }

        }
    }
}