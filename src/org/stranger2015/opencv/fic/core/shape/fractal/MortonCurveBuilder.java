package org.stranger2015.opencv.fic.core.shape.fractal;

import org.stranger2015.opencv.fic.core.AbstractNode;
import org.stranger2015.opencv.fic.core.geom.Coordinate;
import org.stranger2015.opencv.fic.core.geom.Geometry;
import org.stranger2015.opencv.fic.core.geom.GeometryFactory;
import org.stranger2015.opencv.fic.core.geom.LineSegment;

/**
 * Generates a {@link LineString} representing the Morton Curve
 * at a given level.
 *
 * @author Martin Davis
 * @see MortonCode
 */
public
class MortonCurveBuilder extends AbstractNode.GeometricShapeBuilder {

    /**
     * Creates a new instance using the provided {@link GeometryFactory}.
     *
     * @param geomFactory the geometry factory to use
     */
    public
    MortonCurveBuilder ( GeometryFactory geomFactory, SpaceFillingCurveCode spaceFillingCurveCode ) {
        super(geomFactory, spaceFillingCurveCode);

        // use a null extent to indicate no transformation
        // (may be set by client)
        extent = null;
    }

    /**
     * Sets the level of curve to generate.
     * The level must be in the range [0 - 16].
     * This determines the
     * number of points in the generated curve.
     *
     * @param level the level of the curve
     */
    public
    void setLevel ( int level ) {
        this.numPts = spaceFillingCurveCode.size(level);
    }

    /**
     *
     * @return
     */
    @Override
    public
    Geometry getGeometry () {
        int level = spaceFillingCurveCode.level(numPts);
        int nPts = spaceFillingCurveCode.size(level);

        double scale = 1;
        double baseX = 0;
        double baseY = 0;
        if (extent != null) {
            LineSegment baseLine = getSquareBaseLine();
            baseX = baseLine.minX();
            baseY = baseLine.minY();
            double width = baseLine.getLength();
            int maxOrdinate = spaceFillingCurveCode.maxOrdinate(level);
            scale = width / maxOrdinate;
        }

        Coordinate[] pts = new Coordinate[nPts];
        for (int i = 0; i < nPts; i++) {
            Coordinate pt = spaceFillingCurveCode.decode(i);
            double x = transform(pt.getX(), scale, baseX);
            double y = transform(pt.getY(), scale, baseY);
            pts[i] = new Coordinate(x, y);
        }

        return geomFactory.createLineString(pts);
    }

    private static
    double transform ( double val, double scale, double offset ) {
        return val * scale + offset;
    }
}