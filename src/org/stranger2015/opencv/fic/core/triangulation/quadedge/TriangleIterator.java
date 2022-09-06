package org.stranger2015.opencv.fic.core.triangulation.quadedge;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.geom.TriangleImageBlock;

import java.util.Iterator;

/**
 *
 */
public
class TriangleIterator implements Iterator <TriangleImageBlock> {
    private final QuadEdgeSubdivision subdivision;
    private final GeometryFactory geomFact;

    @Contract(pure = true)
    public
    TriangleIterator ( QuadEdgeSubdivision subdivision, GeometryFactory geomFact, TriangleVisitor visitor ) {
        this.subdivision = subdivision;
        this.geomFact = geomFact;
        Object r = subdivision.visitTriangles(visitor, true);
    }

    /**
     * @return
     */
    @Override
    public
    boolean hasNext () {
        subdivision.getTriangles(geomFact);

        return false;
    }

    /**
     * @return
     */
    @Override
    public
    TriangleImageBlock next () {
        return null;
    }

    /**
     * @return
     */
    public
    QuadEdgeSubdivision getSubdivision () {
        return subdivision;
    }
}
