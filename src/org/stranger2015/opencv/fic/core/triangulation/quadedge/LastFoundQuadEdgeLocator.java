package org.stranger2015.opencv.fic.core.triangulation.quadedge;

import java.util.Collection;

/**
 * Locates {@link QuadEdge}s in a {@link QuadEdgeSubdivision},
 * optimizing the search by starting in the
 * locality of the last edge found.
 *
 * @author Martin Davis
 */
public class LastFoundQuadEdgeLocator implements QuadEdgeLocator {
    private final QuadEdgeSubdivision subdiv;
    private QuadEdge lastEdge = null;

    public LastFoundQuadEdgeLocator( QuadEdgeSubdivision subdiv) {
        this.subdiv = subdiv;
        init();
    }

    private void init() {
        lastEdge = findEdge();
    }

    private
    QuadEdge findEdge() {
        Collection<QuadEdge> edges = subdiv.getEdges();
        // assume there is an edge - otherwise will get an exception
        return edges.iterator().next();
    }

    /**
     * Locates an edge e, such that either v is on e, or e is an edge of a triangle containing v.
     * The search starts from the last located edge and proceeds on the general direction of v.
     */
    public
    QuadEdge locate( Vertex v) {
        if (! lastEdge.isLive()) {
            init();
        }

        QuadEdge e = subdiv.locateFromEdge(v, lastEdge);
        lastEdge = e;

        return e;
    }
}
