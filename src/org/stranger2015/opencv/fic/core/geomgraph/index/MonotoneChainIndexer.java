package org.stranger2015.opencv.fic.core.geomgraph.index;

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

import org.locationtech.jts.util.IntArrayList;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Quadrant;
import org.stranger2015.opencv.fic.core.geomgraph.Edge;
import org.stranger2015.opencv.fic.core.geomgraph.EdgeIntersection;
import org.stranger2015.opencv.fic.core.geomgraph.Label;

import java.io.PrintStream;
import java.util.*;

/**
 * A list of edge intersections along an {@link org.stranger2015.opencv.fic.core.geomgraph.Edge}.
 * Implements splitting an edge with intersections
 * into multiple resultant edges.
 *
 * @version 1.7
 */
public class EdgeIntersectionList
{
    // a Map <EdgeIntersection, EdgeIntersection>
    private final Map<EdgeIntersection, EdgeIntersection> nodeMap = new TreeMap<>();
    Edge edge;  // the parent edge

    public EdgeIntersectionList( org.stranger2015.opencv.fic.core.geomgraph.Edge edge)
    {
        this.edge = edge;
    }

    /**
     * Adds an intersection into the list, if it isn't already there.
     * The input segmentIndex and dist are expected to be normalized.
     *
     * @param intPt Point of intersection
     * @param segmentIndex Index of the containing line segment in the parent edge
     * @param dist Edge distance of this point along the containing line segment
     *
     * @return the EdgeIntersection found or added
     */
    public
    EdgeIntersection add( org.stranger2015.opencv.fic.core.geom.Coordinate intPt, int segmentIndex, double dist)
    {
        EdgeIntersection eiNew = new EdgeIntersection(intPt, segmentIndex, dist);
        EdgeIntersection ei = nodeMap.get(eiNew);
        if (ei != null) {
            return ei;
        }
        nodeMap.put(eiNew, eiNew);
        return eiNew;
    }

    /**
     * Returns an iterator of {@link EdgeIntersection}s
     *
     * @return an Iterator of EdgeIntersections
     */
    public Iterator iterator() { return nodeMap.values().iterator(); }

    /**
     * Tests if the given point is an edge intersection
     *
     * @param pt the point to test
     * @return true if the point is an intersection
     */
    public boolean isIntersection( org.stranger2015.opencv.fic.core.geom.Coordinate pt)
    {
        for (Iterator it = iterator(); it.hasNext(); ) {
            EdgeIntersection ei = (EdgeIntersection) it.next();
            if (ei.coord.equals(pt))
                return true;
        }
        return false;
    }

    /**
     * Adds entries for the first and last points of the edge to the list
     */
    public void addEndpoints()
    {
        int maxSegIndex = edge.pts.length - 1;
        add(edge.pts[0], 0, 0.0);
        add(edge.pts[maxSegIndex], maxSegIndex, 0.0);
    }

    /**
     * Creates new edges for all the edges that the intersections in this
     * list split the parent edge into.
     * Adds the edges to the input list (this is so a single list
     * can be used to accumulate all split edges for a Geometry).
     *
     * @param edgeList a list of EdgeIntersections
     */
    public void addSplitEdges(List edgeList)
    {
        // ensure that the list has entries for the first and last point of the edge
        addEndpoints();

        Iterator it = iterator();
        // there should always be at least two entries in the list
        EdgeIntersection eiPrev = (EdgeIntersection) it.next();
        while (it.hasNext()) {
            EdgeIntersection ei = (EdgeIntersection) it.next();
            org.stranger2015.opencv.fic.core.geomgraph.Edge newEdge = createSplitEdge(eiPrev, ei);
            edgeList.add(newEdge);

            eiPrev = ei;
        }
    }
    /**
     * Create a new "split edge" with the section of points between
     * (and including) the two intersections.
     * The label for the new edge is the same as the label for the parent edge.
     */
    org.stranger2015.opencv.fic.core.geomgraph.Edge createSplitEdge( EdgeIntersection ei0, EdgeIntersection ei1)
    {
//Debug.print("\ncreateSplitEdge"); Debug.print(ei0); Debug.print(ei1);
        int npts = ei1.segmentIndex - ei0.segmentIndex + 2;

        org.stranger2015.opencv.fic.core.geom.Coordinate lastSegStartPt = edge.pts[ei1.segmentIndex];
        // if the last intersection point is not equal to the its segment start pt,
        // add it to the points list as well.
        // (This check is needed because the distance metric is not totally reliable!)
        // The check for point equality is 2D only - Z values are ignored
        boolean useIntPt1 = ei1.dist > 0.0 || ! ei1.coord.equals2D(lastSegStartPt);
        if (! useIntPt1) {
            npts--;
        Coordinate[] pts = new Coordinate[npts];
        int ipt = 0;
        pts[ipt++] = new Coordinate(ei0.coord);
        for (int i = ei0.segmentIndex + 1; i <= ei1.segmentIndex; i++) {
            pts[ipt++] = edge.pts[i];
        }
        if (useIntPt1) pts[ipt] = ei1.coord;
        return new Edge(pts, new Label(edge.getLabel()));
    }

    public void print(PrintStream out)
    {
        out.println("Intersections:");
        for (Iterator<EdgeIntersectionList> it = iterator(); it.hasNext(); ) {
            EdgeIntersection ei = (EdgeIntersection) it.next();
            ei.print(out);
        }
    }
}

/**
 * MonotoneChains are a way of partitioning the segments of an edge to
 * allow for fast searching of intersections.
 * Specifically, a sequence of contiguous line segments
 * is a monotone chain if all the vectors defined by the oriented segments
 * lies in the same quadrant.
 * <p>
 * Monotone Chains have the following useful properties:
 * <ol>
 * <li>the segments within a monotone chain will never intersect each other
 * <li>the envelope of any contiguous subset of the segments in a monotone chain
 * is simply the envelope of the endpoints of the subset.
 * </ol>
 * Property 1 means that there is no need to test pairs of segments from within
 * the same monotone chain for intersection.
 * Property 2 allows
 * binary search to be used to find the intersection points of two monotone chains.
 * For many types of real-world data, these properties eliminate a large number of
 * segment comparisons, producing substantial speed gains.
 * <p>
 * Note that due to the efficient intersection test, there is no need to limit the size
 * of chains to obtain fast performance.
 *
 * @version 1.7
 */
public class MonotoneChainIndexer {

    public static int[] toIntArray(List<Integer> list)
    {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public MonotoneChainIndexer() {
    }

    public int[] getChainStartIndices(Coordinate[] pts)
    {
        // find the startpoint (and endpoints) of all monotone chains in this edge
        int start = 0;
        IntArrayList startIndexList = new IntArrayList(pts.length / 2);
        // use heuristic to size initial array
        //startIndexList.ensureCapacity(pts.length / 4);
        startIndexList.add(start);
        do {
            int last = findChainEnd(pts, start);
            startIndexList.add(last);
            start = last;
        } while (start < pts.length - 1);
        // copy list to an array of ints, for efficiency
        return startIndexList.toArray();
    }

    public int[] OLDgetChainStartIndices(Coordinate[] pts)
    {
        // find the startpoint (and endpoints) of all monotone chains in this edge
        int start = 0;
        List<Integer> startIndexList = new ArrayList <>();
        startIndexList.add(start);
        do {
            int last = findChainEnd(pts, start);
            startIndexList.add(last);
            start = last;
        } while (start < pts.length - 1);
        // copy list to an array of ints, for efficiency
        return toIntArray(startIndexList);
    }

    /**
     * @return the index of the last point in the monotone chain
     */
    private int findChainEnd(Coordinate[] pts, int start)
    {
        // determine quadrant for chain
        int chainQuad = Quadrant.quadrant(pts[start], pts[start + 1]);
        int last = start + 1;
        while (last < pts.length ) {
            //if (last - start > 100) break;
            // compute quadrant for next possible segment in chain
            int quad = Quadrant.quadrant(pts[last - 1], pts[last]);
            if (quad != chainQuad) break;
            last++;
        }
        return last - 1;
    }



}
