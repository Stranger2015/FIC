package org.stranger2015.opencv.fic.core.triangulation;

import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdge;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.Vertex;

import java.util.Collection;

/**
 *
 */
public
interface IDelaunayTriangulator {

    /**
     * @param vertices
     */
    default
    void insertSites ( Collection <Vertex> vertices ) {
        for (Vertex v : vertices) {
            insertSite(v);
        }
    }

    /**
     * Inserts a new point into a subdivision representing a Delaunay
     * triangulation, and fixes the affected edges so that the result is still a
     * Delaunay triangulation.
     * <p>
     *
     * @return a quadedge containing the inserted vertex
     */
    QuadEdge insertSite ( Vertex v );

    /**
     *
     * @return
     */
    boolean isUsingTolerance ();
}
