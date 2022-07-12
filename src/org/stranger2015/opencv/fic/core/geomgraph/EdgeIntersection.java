package org.stranger2015.opencv.fic.core.geomgraph;
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

import org.jetbrains.annotations.NotNull;
import org.stranger2015.opencv.fic.core.geom.Coordinate;

import java.io.PrintStream;

/**
 * Represents a point on an edge which intersects with another edge.
 * <p>
 * The intersection may either be a single point, or a line segment
 * (in which case this point is the start of the line segment)
 * The intersection point must be precise.
 *
 * @version 1.8
 */
public
class EdgeIntersection implements Comparable <EdgeIntersection> {

    /**
     * Point of intersection
     */
    public Coordinate coord;

    /**
     * Index of the containing line segment in the parent edge
     */
    public int segmentIndex;

    /**
     * Edge distance of this point along the containing line segment
     */
    public double dist;

    /**
     * EdgeIntersection.
     *
     * @param coord        Point of intersection
     * @param segmentIndex Index of the containing line segment in the parent edge
     * @param dist         Edge distance of this point along the containing line segment
     */
    public
    EdgeIntersection ( Coordinate coord, int segmentIndex, double dist ) {
        this.coord = new Coordinate(coord);
        this.segmentIndex = segmentIndex;
        this.dist = dist;
    }

    public
    Coordinate getCoordinate () {
        return coord;
    }

    public
    int getSegmentIndex () {
        return segmentIndex;
    }

    public
    double getDistance () {
        return dist;
    }

    /**
     * Comparison with segment and distance.
     *
     * @param segmentIndex index of the containing line segment
     * @param dist         dge distance of this point along the containing line segment
     * @return {@code 1} this EdgeIntersection is located before the argument location,
     * {@code 0} this EdgeIntersection is at the argument location,
     * {@code 1} this EdgeIntersection is located after the argument location
     */
    public
    int compare ( int segmentIndex, double dist ) {
        if (this.segmentIndex < segmentIndex) {
            return -1;
        }
        if (this.segmentIndex > segmentIndex) {
            return 1;
        }
        return Double.compare(this.dist, dist);
    }

    public
    boolean isEndPoint ( int maxSegmentIndex ) {
        return segmentIndex == 0 && dist == 0.0 || segmentIndex == maxSegmentIndex;
    }

    public
    void print ( PrintStream out ) {
        out.print(coord);
        out.print(" seg # = " + segmentIndex);
        out.println(" dist = " + dist);
    }

    public
    String toString () {
        return coord + " seg # = " + segmentIndex + " dist = " + dist;
    }

    @Override
    public
    int compareTo ( @NotNull EdgeIntersection o ) {
        return compare(o.segmentIndex, o.dist);
    }
}
