package org.stranger2015.opencv.fic.core.triangulation.quadedge;

/**
 * An interface for classes which locate an edge in a {@link QuadEdgeSubdivision}
 * which either contains a given {@link Vertex} V
 * or is an edge of a triangle which contains V.
 * Implementors may utilized different strategies for
 * optimizing locating containing edges/triangles.
 *
 * @author Martin Davis
 */
public interface QuadEdgeLocator {
    QuadEdge locate( Vertex v);
}