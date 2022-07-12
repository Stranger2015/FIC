  package org.stranger2015.opencv.fic.core.triangulation;

import org.stranger2015.opencv.fic.core.geom.Envelope;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdgeSubdivision;

public abstract
class DelaunayTriangulation extends QuadEdgeSubdivision {
    /**
     * Creates a new instance of a quad-edge subdivision based on a frame triangle
     * that encloses a supplied bounding box. A new super-bounding box that
     * contains the triangle is computed and stored.
     *
     * @param env       the bounding box to surround
     * @param tolerance the tolerance value for determining if two sites are equal
     */
    public
    DelaunayTriangulation ( Envelope env, double tolerance ) {
        super(env, tolerance);
    }
}
