package org.stranger2015.opencv.fic.core.triangulation.quadedge;

/**
 * An interface for algorithms which process the triangles in a {@link QuadEdgeSubdivision}.
 *
 * @author Martin Davis
 * @version 1.0
 */
public interface TriangleVisitor {
    /**
     * Visits the {@link QuadEdge}s of a triangle.
     *
     * @param triEdges an array of the 3 quad edges in a triangle (in CCW order)
     */
    void visit( QuadEdge[] triEdges);
}