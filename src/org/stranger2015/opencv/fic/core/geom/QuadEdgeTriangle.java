package org.stranger2015.opencv.fic.core.geom;

import org.stranger2015.opencv.fic.core.IAddress;
import org.stranger2015.opencv.fic.core.triangulation.quadedge.QuadEdge;

/**
 *
 */
public
class QuadEdgeTriangle extends Triangle {
    /**
     * @param factory
     * @param edges
     */
    public
    QuadEdgeTriangle (GeometryFactory factory, QuadEdge[] edges ) {
        super(
                factory,
                edges[0].dest().getCoordinate(),
                edges[1].dest().getCoordinate(),
                edges[2].dest().getCoordinate()
        );
    }

    /**
     * @param factory
     * @param p0
     * @param p1
     * @param p2
     */
    public
    QuadEdgeTriangle ( GeometryFactory factory, Coordinate p0, Coordinate p1, Coordinate p2 ) {
        super(factory, p0, p1, p2);
    }

    @Override
    public
    IAddress <?> getAddress () {
        return null;
    }

    @Override
    public
    EShape getShapeKind () {
        return EShape.TRIANGLE;
    }
}
