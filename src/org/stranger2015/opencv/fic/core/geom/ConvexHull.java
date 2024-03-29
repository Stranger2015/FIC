package org.stranger2015.opencv.fic.core.geom;

/*
 * Copyright (c) 2016 Vivid Solutions.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

import org.stranger2015.opencv.fic.core.algorithm.PointLocation;
import org.stranger2015.opencv.fic.utils.Assert;
import org.stranger2015.opencv.fic.utils.UniqueCoordinateArrayFilter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.stranger2015.opencv.fic.core.algorithm.Orientation.*;
import static org.stranger2015.opencv.fic.core.algorithm.Orientation.index;

/**
 * Computes the convex hull of a {@link Geometry}.
 * The convex hull is the smallest convex Geometry that contains all the
 * points in the input Geometry.
 * <p>
 * Uses the Graham Scan algorithm.
 *
 * @version 1.9
 */
public
class ConvexHull {
    private final GeometryFactory geomFactory;
    private final Coordinate[] inputPts;

    /**
     * Create a new convex hull construction for the input {@link Geometry}.
     */
    public
    ConvexHull ( Geometry<?> geometry ) {
        this(extractCoordinates(geometry), geometry.getFactory());
    }

    /**
     * Create a new convex hull construction for the input {@link Coordinate} array.
     */
    public
    ConvexHull ( Coordinate[] pts, GeometryFactory geomFactory ) {
        inputPts = UniqueCoordinateArrayFilter.filterCoordinates(pts);
        //inputPts = pts;
        this.geomFactory = geomFactory;
    }

    private static
    Coordinate[] extractCoordinates ( Geometry geom ) {
        UniqueCoordinateArrayFilter filter = new UniqueCoordinateArrayFilter();
        geom.apply(filter);

        return filter.getCoordinates();
    }

    /**
     * Returns a {@link Geometry} that represents the convex hull of the input
     * geometry.
     * The returned geometry contains the minimal number of points needed to
     * represent the convex hull.  In particular, no more than two consecutive
     * points will be collinear.
     *
     * @return if the convex hull contains 3 or more points, a {@link Polygon};
     * 2 points, a {@link LineString};
     * 1 point, a {@link Point};
     * 0 points, an empty {@link GeometryCollection}.
     */
    public
    Geometry getConvexHull () {

        if (inputPts.length == 0) {
            return geomFactory.createGeometryCollection();
        }
        if (inputPts.length == 1) {
            return geomFactory.createPoint(inputPts[0]);
        }
        if (inputPts.length == 2) {
            return geomFactory.createLineString(inputPts);
        }

        Coordinate[] reducedPts = inputPts;
        // use heuristic to reduce points, if large
        if (inputPts.length > 50) {
            reducedPts = reduce(inputPts);
        }
        // sort points for Graham scan.
        Coordinate[] sortedPts = preSort(reducedPts);

        // Use Graham scan to find convex hull.
        Deque <Coordinate> cHS = grahamScan(sortedPts);

        // Convert stack to an array.
        Coordinate[] cH = toCoordinateArray(cHS);

        // Convert array to appropriate output geometry.
        return lineOrPolygon(cH);
    }

    /**
     * An alternative to Stack.toArray, which is not present in earlier versions
     * of Java.
     */
    protected
    Coordinate[] toCoordinateArray ( Deque<Coordinate> stack ) {
        Coordinate[] coordinates = new Coordinate[stack.size()];
        IntStream.range(0, stack.size()).forEachOrdered(i -> {
            Coordinate coordinate = stack.pop();
            coordinates[i] = coordinate;
        });

        return coordinates;
    }

    /**
     * Uses a heuristic to reduce the number of points scanned
     * to compute the hull.
     * The heuristic is to find a polygon guaranteed to
     * be in (or on) the hull, and eliminate all points inside it.
     * A quadrilateral defined by the extremal points
     * in the four orthogonal directions
     * can be used, but even more inclusive is
     * to use an octilateral defined by the points in the 8 cardinal directions.
     * <p>
     * Note that even if the method used to determine the polygon vertices
     * is not 100% robust, this does not affect the robustness of the convex hull.
     * <p>
     * To satisfy the requirements of the Graham Scan algorithm,
     * the returned array has at least 3 entries.
     *
     * @param inputPts the points to reduce
     * @return the reduced list of points (at least 3)
     */
    private
    Coordinate[] reduce ( Coordinate[] inputPts ) {
        //Coordinate[] polyPts = computeQuad(inputPts);
        Coordinate[] polyPts = computeOctRing(inputPts);
        //Coordinate[] polyPts = null;

        // unable to compute interior polygon for some reason
        if (polyPts == null) {

            return inputPts;
        }

//    LinearRing ring = geomFactory.createLinearRing(polyPts);
//    System.out.println(ring);
        // add points defining polygon
        TreeSet <Coordinate> reducedSet = Arrays.stream(polyPts)
                .collect(Collectors.toCollection(TreeSet::new));
        /**
         * Add all unique points not in the interior poly.
         * CGAlgorithms.isPointInRing is not defined for points actually on the ring,
         * but this doesn't matter since the points of the interior polygon
         * are forced to be in the reduced set.
         */
        IntStream.range(0, inputPts.length).filter(i -> !PointLocation.isInRing(inputPts[i], polyPts))
                .mapToObj(i -> inputPts[i]).forEachOrdered(reducedSet::add);

        Coordinate[] reducedPts = CoordinateArrays.toCoordinateArray(reducedSet);

        // ensure that computed array has at least 3 points (not necessarily unique)
        return reducedPts.length < 3 ? padArray3(reducedPts) : reducedPts;
    }

    private
    Coordinate[] padArray3 ( Coordinate[] pts ) {
        Coordinate[] pad = new Coordinate[3];
        for (int i = 0; i < pad.length; i++) {
            if (i < pts.length) {
                pad[i] = pts[i];
            }
            else {
                pad[i] = pts[0];
            }
        }
        return pad;
    }

    private
    Coordinate[] preSort ( Coordinate[] pts ) {
        Coordinate t;

        // find the lowest point in the set. If two or more points have
        // the same minimum y coordinate choose the one with the minimu x.
        // This focal point is put in array location pts[0].
        for (int i = 1; i < pts.length; i++) {
            if ((pts[i].y < pts[0].y) || ((pts[i].y == pts[0].y) && (pts[i].x < pts[0].x))) {
                t = pts[0];
                pts[0] = pts[i];
                pts[i] = t;
            }
        }

        // sort the points radially around the focal point.
        Arrays.sort(pts,
                1,
                pts.length
        );

        //radialSort(pts);
        return pts;
    }

    /**
     * Uses the Graham Scan algorithm to compute the convex hull vertices.
     *
     * @param c a list of points, with at least 3 entries
     * @return a Stack containing the ordered points of the convex hull ring
     */
    private
    Deque <Coordinate> grahamScan ( Coordinate[] c ) {
        Coordinate p;
        Deque <Coordinate> ps = new ArrayDeque <>();
        ps.push(c[0]);
        ps.push(c[1]);
        ps.push(c[2]);
        for (int i = 3; i < c.length; i++) {
            p = ps.pop();
            // check for empty stack to guard against robustness problems
            while (!ps.isEmpty() && index(ps.peek(), p, c[i]) > 0) {
                p = ps.pop();
            }
            ps.push(p);
            ps.push(c[i]);
        }
        ps.push(c[0]);

        return ps;
    }

    /**
     * @return whether the three coordinates are collinear and c2 lies between
     * c1 and c3 inclusive
     */
    private
    boolean isBetween ( Coordinate c1, Coordinate c2, Coordinate c3 ) {
        if (index(c1, c2, c3) != 0) {
            return false;
        }
        if (c1.x != c3.x) {
            if (c1.x <= c2.x && c2.x <= c3.x) {
                return true;
            }
            if (c3.x <= c2.x && c2.x <= c1.x) {
                return true;
            }
        }
        if (c1.y != c3.y) {
            if (c1.y <= c2.y && c2.y <= c3.y) {
                return true;
            }
            return c3.y <= c2.y && c2.y <= c1.y;
        }

        return false;
    }

    private
    Coordinate[] computeOctRing ( Coordinate[] inputPts ) {
        Coordinate[] octPts = computeOctPts(inputPts);
        CoordinateList coordList = new CoordinateList();
        coordList.add(octPts, false);

        // points must all lie in a line
        if (coordList.size() < 3) {
            return null;
        }
        coordList.closeRing();

        return coordList.toCoordinateArray();
    }

    private
    Coordinate[] computeOctPts ( Coordinate[] inputPts ) {
        Coordinate[] pts = new Coordinate[8];
        Arrays.fill(pts, inputPts[0]);
        IntStream.range(1, inputPts.length).forEachOrdered(i -> {
            if (inputPts[i].x < pts[0].x) {
                pts[0] = inputPts[i];
            }
            if (inputPts[i].x - inputPts[i].y < pts[1].x - pts[1].y) {
                pts[1] = inputPts[i];
            }
            if (inputPts[i].y > pts[2].y) {
                pts[2] = inputPts[i];
            }
            if (inputPts[i].x + inputPts[i].y > pts[3].x + pts[3].y) {
                pts[3] = inputPts[i];
            }
            if (inputPts[i].x > pts[4].x) {
                pts[4] = inputPts[i];
            }
            if (inputPts[i].x - inputPts[i].y > pts[5].x - pts[5].y) {
                pts[5] = inputPts[i];
            }
            if (inputPts[i].y < pts[6].y) {
                pts[6] = inputPts[i];
            }
            if (inputPts[i].x + inputPts[i].y < pts[7].x + pts[7].y) {
                pts[7] = inputPts[i];
            }
        });

        return pts;
    }

/*
  // MD - no longer used, but keep for reference purposes
  private Coordinate[] computeQuad(Coordinate[] inputPts) {
    BigQuad bigQuad = bigQuad(inputPts);

    // Build a linear ring defining a big poly.
    ArrayList bigPoly = new ArrayList();
    bigPoly.add(bigQuad.westmost);
    if (! bigPoly.contains(bigQuad.northmost)) {
      bigPoly.add(bigQuad.northmost);
    }
    if (! bigPoly.contains(bigQuad.eastmost)) {
      bigPoly.add(bigQuad.eastmost);
    }
    if (! bigPoly.contains(bigQuad.southmost)) {
      bigPoly.add(bigQuad.southmost);
    }
    // points must all lie in a line
    if (bigPoly.size() < 3) {
      return null;
    }
    // closing point
    bigPoly.add(bigQuad.westmost);

    Coordinate[] bigPolyArray = CoordinateArrays.toCoordinateArray(bigPoly);

    return bigPolyArray;
  }

  private BigQuad bigQuad(Coordinate[] pts) {
    BigQuad bigQuad = new BigQuad();
    bigQuad.northmost = pts[0];
    bigQuad.southmost = pts[0];
    bigQuad.westmost = pts[0];
    bigQuad.eastmost = pts[0];
    for (int i = 1; i < pts.length; i++) {
      if (pts[i].x < bigQuad.westmost.x) {
        bigQuad.westmost = pts[i];
      }
      if (pts[i].x > bigQuad.eastmost.x) {
        bigQuad.eastmost = pts[i];
      }
      if (pts[i].y < bigQuad.southmost.y) {
        bigQuad.southmost = pts[i];
      }
      if (pts[i].y > bigQuad.northmost.y) {
        bigQuad.northmost = pts[i];
      }
    }
    return bigQuad;
  }

  private static class BigQuad {
    public Coordinate northmost;
    public Coordinate southmost;
    public Coordinate westmost;
    public Coordinate eastmost;
  }
  */

    /**
     * @param coordinates the vertices of a linear ring, which may or may not be
     *                    flattened (i.e. vertices collinear)
     * @return a 2-vertex <code>LineString</code> if the vertices are
     * collinear; otherwise, a <code>Polygon</code> with unnecessary
     * (collinear) vertices removed
     */
    private
    Geometry<?> lineOrPolygon ( Coordinate[] coordinates ) {

        coordinates = cleanRing(coordinates);
        if (coordinates.length == 3) {
            return geomFactory.createLineString(new Coordinate[]{coordinates[0], coordinates[1]});
//      return new LineString(new Coordinate[]{coordinates[0], coordinates[1]},
//          geometry.getPrecisionModel(), geometry.getSRID());
        }
        LinearRing linearRing = geomFactory.createLinearRing(coordinates);

        return geomFactory.createPolygon(linearRing);
    }

    /**
     * @param original the vertices of a linear ring, which may or may not be flattened (i.e. vertices collinear)
     * @return the coordinates with unnecessary (collinear) vertices removed
     */
    private
    Coordinate[] cleanRing ( Coordinate[] original ) {
        Assert.equals(original[0], original[original.length - 1]);
        List <Coordinate> cleanedRing = new ArrayList <>();
        Coordinate previousDistinctCoordinate = null;
        for (int i = 0; i <= original.length - 2; i++) {
            Coordinate currentCoordinate = original[i];
            Coordinate nextCoordinate = original[i + 1];
            if (currentCoordinate.equals(nextCoordinate)) {
                continue;
            }
            if (previousDistinctCoordinate != null
                    && isBetween(previousDistinctCoordinate, currentCoordinate, nextCoordinate)) {
                continue;
            }
            cleanedRing.add(currentCoordinate);
            previousDistinctCoordinate = currentCoordinate;
        }
        cleanedRing.add(original[original.length - 1]);
        Coordinate[] cleanedRingCoordinates = new Coordinate[cleanedRing.size()];

        return cleanedRing.toArray(cleanedRingCoordinates);
    }


    /**
     * Compares {@link Coordinate}s for their angle and distance
     * relative to an origin.
     *
     * @author Martin Davis
     * @version 1.7
     */
    private static
    class RadialComparator
            implements Comparator <RadialComparator> {
        private final Coordinate origin;

        public
        RadialComparator ( Coordinate origin ) {
            this.origin = origin;
        }

        /**
         * @param o1
         * @param o2
         * @return
         */
        public
        int compare ( Coordinate o1, Coordinate o2 ) {
            return polarCompare(origin, o1, o2);
        }

        /**
         * Given two points p and q compare them with respect to their radial
         * ordering about point o.  First checks radial ordering.
         * If points are collinear, the comparison is based
         * on their distance to the origin.
         * <p>
         * p < q iff
         * <ul>
         * <li>ang(o-p) < ang(o-q) (e.g. o-p-q is CCW)
         * <li>or ang(o-p) == ang(o-q) && dist(o,p) < dist(o,q)
         * </ul>
         *
         * @param o the origin
         * @param p a point
         * @param q another point
         * @return -1, 0 or 1 depending on whether p is less than,
         * equal to or greater than q
         */
        private static
        int polarCompare ( Coordinate o, Coordinate p, Coordinate q ) {
            double dxp = p.x - o.x;
            double dyp = p.y - o.y;
            double dxq = q.x - o.x;
            double dyq = q.y - o.y;

/*
      // MD - non-robust
      int result = 0;
      double alph = Math.atan2(dxp, dyp);
      double beta = Math.atan2(dxq, dyq);
      if (alph < beta) {
        result = -1;
      }
      if (alph > beta) {
        result = 1;
      }
      if (result !=  0) return result;
      //*/

            int orient = index(o, p, q);

            if (orient == COUNTERCLOCKWISE) return 1;
            if (orient == CLOCKWISE) return -1;

            // points are collinear - check distance
            double op = dxp * dxp + dyp * dyp;
            double oq = dxq * dxq + dyq * dyq;

            return Double.compare(op, oq);
        }

        @Override
        public
        int compare ( RadialComparator o1, RadialComparator o2 ) {
            return 0;
        }
    }
}
