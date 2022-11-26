package org.stranger2015.opencv.fic.core.geomgraph;

import org.locationtech.jts.geom.Position;
import org.locationtech.jts.geomgraph.Depth;
import org.locationtech.jts.geomgraph.GraphComponent;
import org.locationtech.jts.geomgraph.Label;
import org.stranger2015.opencv.fic.core.algorithm.LineIntersector;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Envelope;
import org.stranger2015.opencv.fic.core.geom.IntersectionMatrix;
import org.stranger2015.opencv.fic.core.geomgraph.index.MonotoneChainEdge;

import java.io.PrintStream;


/**
 * @version 1.7
 */
public class Edge
        extends GraphComponent
{

    /**
     * Updates an IM from the label for an edge.
     * Handles edges from both L and A geometries.
     * @param label Label defining position
     * @param im intersection matrix
     */
    public static void updateIM( Label label, IntersectionMatrix im)
    {
        im.setAtLeastIfValid(label.getLocation(0, Position.ON), label.getLocation(1, Position.ON), 1);
        if (label.isArea()) {
            im.setAtLeastIfValid(label.getLocation(0, Position.LEFT),  label.getLocation(1, Position.LEFT),   2);
            im.setAtLeastIfValid(label.getLocation(0, Position.RIGHT), label.getLocation(1, Position.RIGHT),  2);
        }
    }

    Coordinate[] pts;
    private Envelope env;
    EdgeIntersectionList eiList = new EdgeIntersectionList(this);
    private String name;
    private MonotoneChainEdge mce;
    private boolean isIsolated = true;
    private Depth depth = new Depth();
    private int depthDelta = 0;   // the change in area depth from the R to L side of this edge

    public Edge(Coordinate[] pts, Label label)
    {
        this.pts = pts;
        this.label = label;
    }
    public Edge( Coordinate[] pts, Label label )
    {
        this(pts, null);
    }

    public int getNumPoints() { return pts.length; }
    public void setName(String name) { this.name = name; }
    public Coordinate[] getCoordinates()  {    return pts;  }
    public Coordinate getCoordinate(int i)
    {
        return pts[i];
    }
    public Coordinate getCoordinate() {
        Coordinate result = null;
        if (pts.length > 0) {
            result = pts[0];
        }
        return result;
    }

    /**
     * Compute the contribution to an IM for this component.
     *
     * @param im Intersection matrix
     */
    @Override
    protected
    void computeIM ( org.locationtech.jts.geom.IntersectionMatrix im ) {

    }

    public Envelope getEnvelope()
    {
        // compute envelope lazily
        if (env == null) {
            env = new Envelope();
            for (int i = 0; i < pts.length; i++) {
                env.expandToInclude(pts[i]);
            }
        }
        return env;
    }

    public Depth getDepth() { return depth; }

    /**
     * The depthDelta is the change in depth as an edge is crossed from R to L
     * @return the change in depth as the edge is crossed from R to L
     */
    public int getDepthDelta()  { return depthDelta;  }
    public void setDepthDelta(int depthDelta)  { this.depthDelta = depthDelta;  }

    public int getMaximumSegmentIndex()
    {
        return pts.length - 1;
    }
    public EdgeIntersectionList getEdgeIntersectionList() { return eiList; }

    public MonotoneChainEdge getMonotoneChainEdge()
    {
        if (mce == null) mce = new MonotoneChainEdge(this);
        return mce;
    }

    public boolean isClosed()
    {
        return pts[0].equals(pts[pts.length - 1]);
    }
    /**
     * An Edge is collapsed if it is an Area edge and it consists of
     * two segments which are equal and opposite (eg a zero-width V).
     *
     * @return zero-width V area edge, consisting of two segments which are equal and of oppose orientation
     */
    public boolean isCollapsed()
    {
        if (! label.isArea()) return false;
        if (pts.length != 3) return false;
        if (pts[0].equals(pts[2]) ) return true;
        return false;
    }
    public
    Edge getCollapsedEdge()
    {
        Coordinate newPts[] = new Coordinate[2];
        newPts[0] = pts[0];
        newPts[1] = pts[1];
        Edge newe = new Edge(newPts, Label.toLineLabel(label));
        return newe;
    }

    public void setIsolated(boolean isIsolated)
    {
        this.isIsolated = isIsolated;
    }
    public boolean isIsolated()
    {
        return isIsolated;
    }

    /**
     * Adds EdgeIntersections for one or both
     * intersections found for a segment of an edge to the edge intersection list.
     * @param li Determining number of intersections to add
     * @param segmentIndex Segment index to add
     * @param geomIndex Geometry index to add
     */
    public void addIntersections(LineIntersector li, int segmentIndex, int geomIndex)
    {
        for (int i = 0; i < li.getIntersectionNum(); i++) {
            addIntersection(li, segmentIndex, geomIndex, i);
        }
    }
    /**
     * Add an EdgeIntersection for intersection intIndex.
     * An intersection that falls exactly on a vertex of the edge is normalized
     * to use the higher of the two possible segmentIndexes
     *
     * @param li Determining number of intersections to add
     * @param segmentIndex Segment index to add
     * @param geomIndex Geometry index to add
     * @param intIndex intIndex is 0 or 1
     */
    public void addIntersection(LineIntersector li, int segmentIndex, int geomIndex, int intIndex)
    {
        Coordinate intPt = new Coordinate(li.getIntersection(intIndex));
        int normalizedSegmentIndex = segmentIndex;
        double dist = li.getEdgeDistance(geomIndex, intIndex);
//Debug.println("edge intpt: " + intPt + " dist: " + dist);
        // normalize the intersection point location
        int nextSegIndex = normalizedSegmentIndex + 1;
        if (nextSegIndex < pts.length) {
            Coordinate nextPt = pts[nextSegIndex];
//Debug.println("next pt: " + nextPt);

            // Normalize segment index if intPt falls on vertex
            // The check for point equality is 2D only - Z values are ignored
            if (intPt.equals2D(nextPt)) {
//Debug.println("normalized distance");
                normalizedSegmentIndex = nextSegIndex;
                dist = 0.0;
            }
        }
        /**
         * Add the intersection point to edge intersection list.
         */
        EdgeIntersection ei = eiList.add(intPt, normalizedSegmentIndex, dist);
//ei.print(System.out);

    }

    /**
     * Update the IM with the contribution for this component.
     * A component only contributes if it has a labelling for both parent geometries
     */
    public void computeIM(IntersectionMatrix im)
    {
        updateIM(label, im);
    }

    /**
     * equals is defined to be:
     * <p>
     * e1 equals e2
     * <b>iff</b>
     * the coordinates of e1 are the same or the reverse of the coordinates in e2
     */
    public boolean equals(Object o)
    {
        if (! (o instanceof Edge)) {
            return false;
        }
        Edge e = (Edge) o;

        if (pts.length != e.pts.length) {
            return false;
        }

        boolean isEqualForward = true;
        boolean isEqualReverse = true;
        int iRev = pts.length;
        for (int i = 0; i < pts.length; i++) {
            if (! pts[i].equals2D(e.pts[i])) {
                isEqualForward = false;
            }
            if (! pts[i].equals2D(e.pts[--iRev])) {
                isEqualReverse = false;
            }
            if (! isEqualForward && ! isEqualReverse) {
                return false;
            }
        }

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pts.length;
        if (pts.length > 0) {
            Coordinate p0 = pts[0];
            Coordinate p1 = pts[pts.length - 1];
            if (p0.compareTo(p1) == 1) {
                p0 = pts[pts.length - 1];
                p1 = pts[0];
            }
            result = prime * result + p0.hashCode();
            result = prime * result + p1.hashCode();
        }

        return result;
    }

    /**
     * Check if coordinate sequences of the Edges are identical.
     *
     * @param e Edge
     * @return true if the coordinate sequences of the Edges are identical
     */
    public boolean isPointwiseEqual( Edge e)
    {
        if (pts.length != e.pts.length) {
            return false;
        }

        for (int i = 0; i < pts.length; i++) {
            if (! pts[i].equals2D(e.pts[i])) {
                return false;
            }
        }
        return true;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("edge ").append(name).append(": ");
        builder.append("LINESTRING (");
        for (int i = 0; i < pts.length; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(pts[i].x).append(" ").append(pts[i].y);
        }
        builder.append(")  ").append(label).append(" ").append(depthDelta);
        
        return builder.toString();
    }
    public void print(PrintStream out)
    {
        out.print("edge " + name + ": ");
        out.print("LINESTRING (");
        for (int i = 0; i < pts.length; i++) {
            if (i > 0) out.print(",");
            out.print(pts[i].x + " " + pts[i].y);
        }
        out.print(")  " + label + " " + depthDelta);
    }
    public void printReverse(PrintStream out)
    {
        out.print("edge " + name + ": ");
        for (int i = pts.length - 1; i >= 0; i--) {
            out.print(pts[i] + " ");
        }
        out.println();
    }

}
